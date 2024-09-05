package com.DSTA.PJ_BE.service.imp;

import com.DSTA.PJ_BE.entity.Category;
import com.DSTA.PJ_BE.repository.CategoryRepository;
import com.DSTA.PJ_BE.service.CategoryService;
import com.DSTA.PJ_BE.utils.Constants;
import com.DSTA.PJ_BE.utils.DataResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {
    private final Logger log = LoggerFactory.getLogger(CategoryServiceImp.class);

    @Autowired
    private CategoryRepository brandRepository;

    @Override
    public DataResponse createCategory(Category category) {
        log.debug("Request Create Brands");
        DataResponse res = new DataResponse();
        try {
            if(category.getNameCategory().length()<5 || category.getSlugCategory().length()<5){
                res.setStatus(Constants.ERROR);
                res.setMessage(Constants.ERROR_ADD_NEW_BRANDS);
                return res;
            }
            category.setNameCategory(category.getNameCategory());
            category.setSlugCategory(category.getSlugCategory());
            category.setStatus(category.getStatus());
            brandRepository.save(category);

            res.setStatus(Constants.SUCCESS);
            res.setMessage(Constants.ADD_SUCCESS);
            res.setResult(category);
            return res;
        }catch (Exception ex){
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }

    }

    @Override
    public DataResponse getAllCategories() {
        log.debug("Request Get All Brands");
        DataResponse res = new DataResponse();
        try {
            List<Category> listBrand = brandRepository.getAllBrand();
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
    public DataResponse updateCategory(Long id, Category category) {
        log.debug("Request update Brands");
        DataResponse res = new DataResponse();
        try {
            Category brandUpdate = brandRepository.getBrandById(id);
            if(category == null){
                res.setStatus(Constants.NOT_FOUND);
                res.setMessage(Constants.LIST_NOT_FOUND);
                return res;
            }
            brandUpdate.setNameCategory(category.getNameCategory());
            brandUpdate.setSlugCategory(category.getSlugCategory());
            brandUpdate.setStatus(category.getStatus());
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
    public DataResponse deleteCategory(Long id) {
        log.debug("Request Delete Brands");
        DataResponse res = new DataResponse();
        try {
            Category brand = brandRepository.getBrandById(id);
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
