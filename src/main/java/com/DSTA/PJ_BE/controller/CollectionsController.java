package com.DSTA.PJ_BE.controller;

import com.DSTA.PJ_BE.service.CollectionService;
import com.DSTA.PJ_BE.utils.DataResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/collection")
public class CollectionsController {
    private final Logger log = LoggerFactory.getLogger(CollectionsController.class);

    @Autowired
    private CollectionService collectionService;

    @PostMapping("/create-collection")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public DataResponse createCollection (MultipartHttpServletRequest data){
        log.debug("Controller Request Create New Collection ");
        MultipartFile file = data.getFile("imageCollection");
        String str = data.getParameter("collection");
        DataResponse res = collectionService.addNewCollection(file, str);
        return res;
    }

    @GetMapping("/get-all-collection")
    public DataResponse getAllCollection(@PageableDefault(page = 0, size = 8) Pageable pageable){
        log.debug("Controller Request Get All Collection");
        DataResponse res = collectionService.getAllCollections(pageable);
        return res;
    }

    @DeleteMapping("/delete-collection/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public DataResponse deleteCollection(@PathVariable("id") Long id){
        log.debug("Controller Request Delete Collection");
        DataResponse res = collectionService.deleteCollections(id);
        return res;
    }

    @PutMapping("/update-collection/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public DataResponse updateCollection(MultipartHttpServletRequest data, @PathVariable("id") Long id){
        log.debug("Controller Request Update Collection");
        MultipartFile file = data.getFile("imageCollection");
        String str = data.getParameter("Collection");
        DataResponse res = collectionService.updateCollections(file, str, id);
        return res;
    }

    @GetMapping("/get-collection-detail/{id}")
    public DataResponse getCollectionDetail(@PathVariable("id") Long id) {
        log.debug("Controller Request Get Collection Detail");
        DataResponse res = collectionService.getCollectionDetails(id);
        return res;
    }
    
}
