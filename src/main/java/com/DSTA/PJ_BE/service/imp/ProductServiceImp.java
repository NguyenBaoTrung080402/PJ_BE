package com.DSTA.PJ_BE.service.imp;

import com.DSTA.PJ_BE.dto.Product.ProductCreateDto;
import com.DSTA.PJ_BE.entity.Account;
import com.DSTA.PJ_BE.entity.Product;
import com.DSTA.PJ_BE.entity.WishList;
import com.DSTA.PJ_BE.repository.ProductRepository;
import com.DSTA.PJ_BE.repository.WishListRepository;
import com.DSTA.PJ_BE.service.ProductService;
import com.DSTA.PJ_BE.utils.Common;
import com.DSTA.PJ_BE.utils.Constants;
import com.DSTA.PJ_BE.utils.DataResponse;
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

    @Override
    public DataResponse getAllProducts(Pageable pageable) {
        log.debug("Request Get All Product");
        DataResponse res = new DataResponse();
        try {
            Page<Product> listProduct = productRepository.getAllProduct(pageable);
            if (!listProduct.hasContent()) {
                res.setStatus(Constants.NOT_FOUND);
                res.setMessage(Constants.LIST_NOT_FOUND);
                return res;
            }

            res.setResult(Constants.SUCCESS);
            res.setResult(listProduct);
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
            Product product = productRepository.getProductByID(id);
            if (product == null) {
                res.setStatus(Constants.NOT_FOUND);
                res.setMessage(Constants.LIST_NOT_FOUND);
                return res;
            }
            res.setStatus(Constants.SUCCESS);
            res.setResult(product);
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
        try {
            ProductCreateDto productCreateDto = Common.convertStringToObject(str, ProductCreateDto.class);
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
            wishListRepository.delete(wishList);
            res.setStatus(Constants.SUCCESS);
            res.setMessage(Constants.ADD_SUCCESS);
            res.setResult(product);
            return res;

        }catch (Exception ex){
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
            if(product == null){
                res.setStatus(Constants.NOT_FOUND);
                res.setMessage(Constants.LIST_NOT_FOUND);
                return res;
            }
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
