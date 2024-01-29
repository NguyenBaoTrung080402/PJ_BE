package com.DSTA.PJ_BE.service.imp;

import com.DSTA.PJ_BE.entity.Brand;
import com.DSTA.PJ_BE.repository.BrandRepository;
import com.DSTA.PJ_BE.service.BrandsService;
import com.DSTA.PJ_BE.utils.Constants;
import com.DSTA.PJ_BE.utils.DataResponse;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImp implements BrandsService {
    private final Logger log = LoggerFactory.getLogger(BrandServiceImp.class);

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ModelMapper mapper;
    @Override
    public DataResponse createBrand(Brand brands) {
        log.debug("Request Create Brands");
        DataResponse res = new DataResponse();
        try {
            if(brands.getNameBrand().length()<5 || brands.getSlugBrand().length()<5){
                res.setStatus(Constants.ERROR);
                res.setMessage(Constants.ERROR_ADD_NEW_BRANDS);
                return res;
            }
//            brands.setNameBrand(brands.getNameBrand());
//            brands.setSlugBrand(brands.getSlugBrand());
//            brands.setStatus(brands.getStatus());
            brandRepository.save(brands);

            res.setStatus(Constants.SUCCESS);
            res.setMessage(Constants.ADD_SUCCESS);
            res.setResult(brands);
            return res;
        }catch (Exception ex){
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }

    }

    @Override
    public DataResponse getAllBrands() {
        log.debug("Request Get All Brands");
        DataResponse res = new DataResponse();
        try {
            List<Brand> listBrand = brandRepository.getAllBrand();
            if(listBrand == null || listBrand.isEmpty()){
                res.setStatus(Constants.NOT_FOUND);
                res.setMessage(Constants.LIST_NOT_FOUND);
                return res;
            }

            res.setStatus(Constants.SUCCESS);
            res.setResult(listBrand);
            return res;
        }catch (Exception ex){
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }

    }

    @Override
    public DataResponse updateBrand(Long id, Brand brand) {
        log.debug("Request update Brands");
        DataResponse res = new DataResponse();
        try {
            Brand brandUpdate = brandRepository.getBrandById(id);
            if(brand == null){
                res.setStatus(Constants.NOT_FOUND);
                res.setMessage(Constants.LIST_NOT_FOUND);
                return res;
            }
            brandUpdate.setNameBrand(brand.getNameBrand());
            brandUpdate.setSlugBrand(brand.getSlugBrand());
            brandUpdate.setStatus(brand.getStatus());
            brandRepository.save(brandUpdate);

            res.setStatus(Constants.SUCCESS);
            res.setMessage(Constants.UPDATE_SUCCESS);
            res.setResult(brandUpdate);
            return res;
        }catch (Exception ex){
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }
    }

    @Override
    public DataResponse deleteBrand(Long id) {
        log.debug("Request Delete Brands");
        DataResponse res = new DataResponse();
        try {
            Brand brand = brandRepository.getBrandById(id);
            if(brand == null){
                res.setStatus(Constants.NOT_FOUND);
                res.setMessage(Constants.LIST_NOT_FOUND);
                return res;
            }
            brandRepository.delete(brand);
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
