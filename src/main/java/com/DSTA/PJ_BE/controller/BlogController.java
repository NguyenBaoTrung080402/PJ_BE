package com.DSTA.PJ_BE.controller;

import com.DSTA.PJ_BE.service.BlogService;
import com.DSTA.PJ_BE.utils.DataResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/blog")
public class BlogController {
    private final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private BlogService blogService;

    @GetMapping("get-by-id/{id}")
    public DataResponse getBlogById(@PathVariable("id") Long id){
        log.debug("Controller Get Blog By Id");
        DataResponse response = blogService.getBlogById(id);
        return response;
    }

    @GetMapping("get-newest-blogs")
    public DataResponse getNewestBlogs(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        log.debug("Controller Get Newest Blogs");
        DataResponse dataResponse = blogService.getNewestBlogs(pageable);
        return dataResponse;
    }

    @GetMapping("get-blogs-by-topic")
    public DataResponse getBlogsByTopic(@Param("topic") String topic,@PageableDefault(page = 0, size = 10) Pageable pageable) {
        log.debug("Controller Get Blogs By Topic");
        DataResponse dataResponse = blogService.getBlogsByTopic(pageable, topic);
        return dataResponse;
    }
}
