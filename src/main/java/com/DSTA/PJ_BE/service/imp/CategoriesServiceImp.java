package com.DSTA.PJ_BE.service.imp;

import com.DSTA.PJ_BE.dto.Categories.CategoriesADto;
import com.DSTA.PJ_BE.entity.Account;
import com.DSTA.PJ_BE.entity.Categories;
import com.DSTA.PJ_BE.repository.CategoryRepository;
import com.DSTA.PJ_BE.service.CategoriesService;
import com.DSTA.PJ_BE.utils.Common;
import com.DSTA.PJ_BE.utils.Constants;
import com.DSTA.PJ_BE.utils.DataResponse;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoriesServiceImp implements CategoriesService {
    private final Logger log = LoggerFactory.getLogger(CategoriesServiceImp.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    @Transactional
    public DataResponse addNewCategories(MultipartFile file, String str) {
        log.debug("Request Add New Categories");
        DataResponse res = new DataResponse();
        Account account = Common.getCurrentUserLogin();
        Categories categories =  new Categories();
        try {
            CategoriesADto categoriesADto = Common.convertStringToObject(str, CategoriesADto.class);
            categories = mapper.map(categoriesADto, Categories.class);

            if(categoriesADto.getName().length() < 5 || categoriesADto.getSlug().length()<5){
                res.setStatus(Constants.ERROR);
                res.setMessage(Constants.ERROR_ADD_NEW_CATEGORIES);
                return res;
            }
            if(file != null || !file.isEmpty()){
                String imageUrl = Constants.IMG_CATEGORY_SAVE + account.getId() + "/" + Common.currentDate()+ "/";
                String img = Common.saveFile(file, imageUrl, account.getId(), categories.getName());
                if (img != null){
                    categories.setImageCategory(img);
                }
            }else {
                categories.setImageCategory(categories.getImageCategory());
            }
            categories.setName(categoriesADto.getName());
            categories.setSlug(categoriesADto.getSlug());
            categoryRepository.save(categories);
            res.setStatus(Constants.SUCCESS);
            res.setMessage(Constants.ADD_CATEGORIES_SUCCESS);
            res.setResult(categories);
            return res;
        }catch (Exception ex){
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }
    }

    @Override
    public DataResponse getAllCategories() {
        log.debug("Requtest Get All Categories");
        DataResponse res = new DataResponse();
        try {
            List<Categories> listCate = categoryRepository.getALlCate();
            if(listCate == null || listCate.isEmpty()){
                res.setStatus(Constants.NOT_FOUND);
                res.setMessage(Constants.CATEGORIES_NOT_FOUND);
                return res;
            }
            res.setStatus(Constants.SUCCESS);
            res.setResult(listCate);
            return res;
        }catch (Exception ex){
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }
    }

    @Override
    public DataResponse deleteCategories(Long id) {
        log.debug("Request Delete Categories");
        DataResponse res = new DataResponse();
        Account account = Common.getCurrentUserLogin();
        try {
            Categories categories = categoryRepository.getCategoryByID(id);
            if(categories == null){
                res.setStatus(Constants.NOT_FOUND);
                res.setMessage(Constants.CATEGORIES_NOT_FOUND);
                return res;
            }
            String imgPath = Constants.IMG_CATEGORY_SAVE + account.getId();
            categoryRepository.delete(categories);
            Common.deleteImageFolder(imgPath);

            res.setStatus(Constants.SUCCESS);
            res.setMessage(Constants.DELETE_SUCCESS);
            return res;
        }catch (Exception ex){
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }
    }

    @Override
    public DataResponse updateCategory(MultipartFile file, String str, Long id) {
        log.debug("Request Update Categories");
        DataResponse res = new DataResponse();
        Account account = Common.getCurrentUserLogin();
        try {
           CategoriesADto categoriesADto = Common.convertStringToObject(str, CategoriesADto.class);
           Categories categories = categoryRepository.getCategoryByID(id);
           if(categoriesADto.getName().length() < 5 || categoriesADto.getSlug().length()<5){
               res.setStatus(Constants.ERROR);
               res.setMessage(Constants.ERROR_ADD_NEW_CATEGORIES);
               return res;
           }
           if(file != null || !file.isEmpty()){
               String imageUrl = Constants.IMG_CATEGORY_SAVE + account.getId() + "/" + Common.currentDate()+ "/";
               String img = Common.saveFile(file, imageUrl, categories.getId(), categories.getName());
               if(img != null){
                   categories.setImageCategory(img);
               }
           }else{
               categories.setImageCategory(categories.getImageCategory());
           }
           categories.setSlug(categoriesADto.getSlug());
           categories.setName(categoriesADto.getName());
           categoryRepository.save(categories);

           res.setStatus(Constants.SUCCESS);
           res.setMessage(Constants.UPDATE_SUCCESS);
           res.setResult(categories);
           return res;
        }catch (Exception ex ){
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }
    }

}
