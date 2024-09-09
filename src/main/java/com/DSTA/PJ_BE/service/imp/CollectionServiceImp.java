package com.DSTA.PJ_BE.service.imp;

import com.DSTA.PJ_BE.dto.Collections.CollectionsAddDto;
import com.DSTA.PJ_BE.dto.Collections.CollectionsGetAllDto;
import com.DSTA.PJ_BE.dto.Collections.CollectionsViewAllDtoInf;
import com.DSTA.PJ_BE.dto.Collections.CollectionsViewDetailsDtoInf;
import com.DSTA.PJ_BE.entity.Account;
import com.DSTA.PJ_BE.entity.Collection;
import com.DSTA.PJ_BE.repository.CollectionRepository;
import com.DSTA.PJ_BE.service.CollectionService;
import com.DSTA.PJ_BE.utils.Common;
import com.DSTA.PJ_BE.utils.Constants;
import com.DSTA.PJ_BE.utils.DataResponse;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Service
public class CollectionServiceImp implements CollectionService {
    private final Logger log = LoggerFactory.getLogger(CollectionServiceImp.class);

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    @Transactional
    public DataResponse addNewCollection(MultipartFile file, String str) {
        log.debug("Request Add New Collection");
        DataResponse res = new DataResponse();
        Account account = Common.getCurrentUserLogin();
        Collection collection = new Collection();
        try {
            CollectionsAddDto collectionADto = Common.convertStringToObject(str, CollectionsAddDto.class);
            collection = mapper.map(collectionADto, Collection.class);

            if (collectionADto.getName().length() < 5 || collectionADto.getSlug().length() < 5) {
                res.setStatus(Constants.ERROR);
                res.setMessage(Constants.ERROR_ADD_NEW_COLLECTIONS);
                return res;
            }
            String imageUrl = Constants.IMG_COLLECTION_SAVE + account.getId() + "/" + Common.currentDate() + "/";
            String img = Common.saveFile(file, imageUrl, account.getId(), collection.getName());
            if (img != null) {
                collection.setImageCollection(img);
            }
            collection.setName(collectionADto.getName());
            collection.setSlug(collectionADto.getSlug());
            collection.setDescription(collectionADto.getDescription());
            collection.setCategoryId(collectionADto.getCategoryId());
            collection.setProductIds(collectionADto.getProductId());
            collection.setActive(Boolean.parseBoolean(collectionADto.getIsActive()));
            collectionRepository.save(collection);
            res.setStatus(Constants.SUCCESS);
            res.setMessage(Constants.ADD_COLLECTION_SUCCESS);
            res.setResult(collection);
            return res;
        } catch (Exception ex) {
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }
    }

    @Override
    public DataResponse getAllCollections(Pageable pageable) {
        log.debug("Requtest Get All Collection");
        DataResponse res = new DataResponse();
        try {
            Page<CollectionsViewAllDtoInf> listCollection = collectionRepository.getALlCollec(pageable);
            if (listCollection == null || listCollection.isEmpty()) {
                res.setStatus(Constants.NOT_FOUND);
                res.setMessage(Constants.COLLECTION_NOT_FOUND);
                return res;
            }
            Page<CollectionsGetAllDto> collection = Common.mapPage(listCollection, CollectionsGetAllDto.class);
            res.setStatus(Constants.SUCCESS);
            res.setResult(collection);
            return res;
        } catch (Exception ex) {
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }
    }

    @Override
    public DataResponse deleteCollections(Long id) {
        log.debug("Request Delete Collection");
        DataResponse res = new DataResponse();
        Account account = Common.getCurrentUserLogin();
        try {
            Collection collections = collectionRepository.getCollectionByID(id);
            if (collections == null) {
                res.setStatus(Constants.NOT_FOUND);
                res.setMessage(Constants.COLLECTION_NOT_FOUND);
                return res;
            }
            String imgPath = Constants.IMG_COLLECTION_SAVE + account.getId();
            collectionRepository.delete(collections);
            Common.deleteImageFolder(imgPath);

            res.setStatus(Constants.SUCCESS);
            res.setMessage(Constants.DELETE_SUCCESS);
            return res;
        } catch (Exception ex) {
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }
    }

    @Override
    public DataResponse updateCollections(MultipartFile file, String str, Long id) {
        log.debug("Request Update Collection");
        DataResponse res = new DataResponse();
        Account account = Common.getCurrentUserLogin();
        try {
            CollectionsAddDto collectionAddDto = Common.convertStringToObject(str, CollectionsAddDto.class);
            Collection collections = collectionRepository.getCollectionByID(id);
            if (collectionAddDto.getName().length() < 5 || collectionAddDto.getSlug().length() < 5) {
                res.setStatus(Constants.ERROR);
                res.setMessage(Constants.ERROR_ADD_NEW_COLLECTIONS);
                return res;
            }
            if (file != null && !file.isEmpty()) {
                String imageUrl = Constants.IMG_COLLECTION_SAVE + account.getId() + "/" + Common.currentDate() + "/";
                String img = Common.saveFile(file, imageUrl, collections.getId(), collections.getName());
                if (img != null) {
                    collections.setImageCollection(img);
                }
            } else {
                collections.setImageCollection(collections.getImageCollection());
            }
            
            collections.setName(collectionAddDto.getName());
            collections.setSlug(collectionAddDto.getSlug());
            collections.setDescription(collectionAddDto.getDescription());
            collections.setCategoryId(collectionAddDto.getCategoryId());
            collections.setProductIds(collectionAddDto.getProductId());
            collections.setActive(Boolean.parseBoolean(collectionAddDto.getIsActive()));
            collectionRepository.save(collections);

            res.setStatus(Constants.SUCCESS);
            res.setMessage(Constants.UPDATE_SUCCESS);
            res.setResult(collections);
            return res;
        } catch (Exception ex) {
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }
    }

    @Override
    public DataResponse getCollectionDetails(Long id) {
        log.debug("Request Get Category Detail");
        DataResponse res = new DataResponse();
        try {
            CollectionsViewDetailsDtoInf collections = collectionRepository.getCategoryByIDByADmin(id);
            if (collections == null) {
                res.setStatus(Constants.NOT_FOUND);
                res.setMessage(Constants.COLLECTION_NOT_FOUND);
                return res;
            }
            CollectionsAddDto categoriesGetAllDto = mapper.map(collections, CollectionsAddDto.class);
            res.setStatus(Constants.SUCCESS);
            res.setResult(categoriesGetAllDto);
            return res;
        } catch (Exception ex) {
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }
    }
}
