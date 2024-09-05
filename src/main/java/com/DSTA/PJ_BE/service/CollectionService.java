package com.DSTA.PJ_BE.service;

import com.DSTA.PJ_BE.utils.DataResponse;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface CollectionService {

    DataResponse addNewCollection(MultipartFile file, String str);

    DataResponse getAllCollections(Pageable pageable);

    DataResponse deleteCollections(Long id);

    DataResponse updateCollections(MultipartFile file, String str, Long id);

    DataResponse getCollectionDetails(Long id);
}
