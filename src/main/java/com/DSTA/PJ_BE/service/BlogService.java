package com.DSTA.PJ_BE.service;

import com.DSTA.PJ_BE.entity.BlogTopic;
import com.DSTA.PJ_BE.utils.DataResponse;
import org.springframework.data.domain.Pageable;

public interface BlogService {
    DataResponse getBlogsByTopic(Pageable pageable, String topicName);
    DataResponse getNewestBlogs(Pageable pageable);
    DataResponse getBlogById(Long id);
}
