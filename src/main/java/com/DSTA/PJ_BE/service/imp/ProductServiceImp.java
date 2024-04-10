package com.DSTA.PJ_BE.service.imp;

import com.DSTA.PJ_BE.dto.Product.*;
import com.DSTA.PJ_BE.entity.*;
import com.DSTA.PJ_BE.repository.ProductColorRepository;
import com.DSTA.PJ_BE.repository.ProductRepository;
import com.DSTA.PJ_BE.repository.ProductSizeRepository;
import com.DSTA.PJ_BE.repository.WishListRepository;
import com.DSTA.PJ_BE.service.ProductService;
import com.DSTA.PJ_BE.utils.Common;
import com.DSTA.PJ_BE.utils.Constants;
import com.DSTA.PJ_BE.utils.DataResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductServiceImp implements ProductService {
    private final Logger log = LoggerFactory.getLogger(ProductServiceImp.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WishListRepository wishListRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProductSizeRepository productSizeRepository;

    @Autowired
    private ProductColorRepository productColorRepository;

    @Override
    public DataResponse getAllProducts(Pageable pageable) {
        log.debug("Request Get All Product");
        DataResponse res = new DataResponse();
        try {
            Page<ProductGetAllInfDto> listProduct = productRepository.getAllProductInf(pageable);
            if (!listProduct.hasContent()) {
                res.setStatus(Constants.NOT_FOUND);
                res.setMessage(Constants.LIST_NOT_FOUND);
                return res;
            }
            Page<ProductGetAllDto> productGetAll = Common.mapPage(listProduct, ProductGetAllDto.class);
            res.setStatus(Constants.SUCCESS);
            res.setResult(productGetAll);
            return res;
        } catch (Exception ex) {
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }
    }

    @Override
    public DataResponse getProductId(Long id) {
        log.debug("Request Get Product By Id");
        DataResponse res = new DataResponse();
        try {
            ProductGetByIdInfDto productInf = productRepository.getProductByIDInf(id);
            if (productInf == null) {
                res.setStatus(Constants.NOT_FOUND);
                res.setMessage(Constants.LIST_NOT_FOUND);
                return res;
            }
            ProductGetByIdDto productGetAll = mapper.map(productInf, ProductGetByIdDto.class);
            res.setStatus(Constants.SUCCESS);
            res.setResult(productGetAll);
            return res;
        } catch (Exception ex) {
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }
    }

    @Override
    public DataResponse createProducts(MultipartFile file, String str) {
        log.debug("Request Create Product");
        DataResponse res = new DataResponse();
        Account account = Common.getCurrentUserLogin();
        Product product = new Product();

        ProductColor productColor = new ProductColor();
        ProductSize productSize = new ProductSize();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ProductCreateDto productCreateDto = objectMapper.readValue(str, ProductCreateDto.class);
            product = mapper.map(productCreateDto, Product.class);

            if (
                    productCreateDto.getName().length() < 5 ||
                    productCreateDto.getSlug().length() < 5 ||
                    productCreateDto.getDescription().length() < 10 ||
                    productCreateDto.getInformation().length() < 10 ||
                    productCreateDto.getSummary().length() < 10
            ) {
                res.setStatus(Constants.ERROR);
                res.setMessage(Constants.ERROR_ADD_NEW_PRODUCT);
                return res;
            }
            if (file != null || !file.isEmpty()) {
                String imgUrl = Constants.IMG_PRODUCT_SAVE + account.getId() + "/" + Common.currentDate() + "/";
                String img = Common.saveFile(file, imgUrl, account.getId(), product.getName());
                if (img != null) {
                    product.setImage(img);
                }
            } else {
                product.setImage(product.getImage());
            }

            product.setName(productCreateDto.getName());
            product.setSlug(productCreateDto.getSlug());
            product.setDescription(productCreateDto.getDescription());
            product.setInformation(productCreateDto.getInformation());
            product.setSummary(productCreateDto.getSummary());
            product.setStock(productCreateDto.getStock());
            product.setPrice(productCreateDto.getPrice());
            product.setDiscountedPrice(productCreateDto.getDiscountedPrice());
            product.setStatus(productCreateDto.getStatus());
            product.setCategoriesId(productCreateDto.getCategoriesId());
            product.setBrandsId(productCreateDto.getBrandsId());
            productRepository.save(product);

            for (Long sizeId : productCreateDto.getSizeId()) {
                productSize.setSizeId(sizeId);
                productSize.setProductId(product.getId());
                productSizeRepository.save(productSize);
            }
            for (Long colorId : productCreateDto.getColorId()) {
                productColor.setColorId(colorId);
                productColor.setProductId(product.getId());
                productColorRepository.save(productColor);
            }

            res.setStatus(Constants.SUCCESS);
            res.setMessage(Constants.ADD_SUCCESS);
            res.setResult(product);
            return res;

        } catch (Exception ex) {
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }
    }

    @Override
    public DataResponse updateProducts(MultipartFile file, String str, Long id) {
        log.debug("Request Update Product");
        Account account = Common.getCurrentUserLogin();
        DataResponse res = new DataResponse();
        try {
            ProductCreateDto productCreateDto = Common.convertStringToObject(str, ProductCreateDto.class);

            Product product = productRepository.getProductByID(id);
            WishList wishList = wishListRepository.getWLbyProdyctID(product.getId());
            ProductColor productColor = productColorRepository.getProductColorByProductId(product.getId());
            ProductSize productSize = productSizeRepository.getProductSizeByProductId(product.getId());
            if (
                    productCreateDto.getName().length() < 5 ||
                            productCreateDto.getSlug().length() < 5 ||
                            productCreateDto.getDescription().length() < 10 ||
                            productCreateDto.getInformation().length() < 10 ||
                            productCreateDto.getSummary().length() < 10
            ) {
                res.setStatus(Constants.ERROR);
                res.setMessage(Constants.ERROR_ADD_NEW_PRODUCT);
                return res;
            }
            if (file != null || !file.isEmpty()) {
                String imgUrl = Constants.IMG_PRODUCT_SAVE + account.getId() + "/" + Common.currentDate() + "/";
                String img = Common.saveFile(file, imgUrl, account.getId(), product.getName());
                if (img != null) {
                    product.setImage(img);
                }
            } else {
                product.setImage(product.getImage());
            }

            product.setName(productCreateDto.getName());
            product.setSlug(productCreateDto.getSlug());
            product.setDescription(productCreateDto.getDescription());
            product.setInformation(productCreateDto.getInformation());
            product.setSummary(productCreateDto.getSummary());
            product.setStock(productCreateDto.getStock());
            product.setPrice(productCreateDto.getPrice());
            product.setDiscountedPrice(productCreateDto.getDiscountedPrice());
            product.setStatus(productCreateDto.getStatus());
            product.setCategoriesId(productCreateDto.getCategoriesId());
            product.setBrandsId(productCreateDto.getBrandsId());
            productRepository.save(product);

            for (Long colorId : productCreateDto.getColorId()) {
                productColor.setColorId(colorId);
                productColorRepository.save(productColor);
            }

            for (Long sizeId : productCreateDto.getSizeId()) {
                productSize.setSizeId(sizeId);
                productSizeRepository.save(productSize);
            }
            
            
            if(wishList != null && wishList.getProductId() != null){
                wishListRepository.delete(wishList);
            }else {
                res.setStatus(Constants.SUCCESS);
                res.setMessage(Constants.ADD_SUCCESS);
                res.setResult(product);
            }
            return res;

        }catch (Exception ex){
            System.out.println(ex.getMessage());
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }
    }

    @Override
    public DataResponse deleteProducts(Long id) {
        log.debug("Request Delete Product ");
        DataResponse res = new DataResponse();
        try {
            Product product = productRepository.getProductByID(id);
            ProductColor productColor = productColorRepository.getProductColorByProductId(product.getId());
            ProductSize productSize = productSizeRepository.getProductSizeByProductId(product.getId());

            if(product == null){
                res.setStatus(Constants.NOT_FOUND);
                res.setMessage(Constants.LIST_NOT_FOUND);
                return res;
            }
            productColorRepository.delete(productColor);
            productSizeRepository.delete(productSize);
            productRepository.delete(product);
            res.setStatus(Constants.SUCCESS);
            res.setMessage(Constants.DELETE_SUCCESS);
            return res;
        }catch (Exception ex){
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }
    }
}
