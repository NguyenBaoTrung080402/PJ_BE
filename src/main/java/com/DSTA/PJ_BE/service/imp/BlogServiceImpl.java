package com.DSTA.PJ_BE.service.imp;

import com.DSTA.PJ_BE.entity.Blog;
import com.DSTA.PJ_BE.repository.BlogRepository;
import com.DSTA.PJ_BE.service.BlogService;
import com.DSTA.PJ_BE.utils.Constants;
import com.DSTA.PJ_BE.utils.DataResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    private final Logger log = LoggerFactory.getLogger(BrandServiceImp.class);

    @Override
    public DataResponse getBlogById(Long id) {
        log.debug("Request getBlogById");
        DataResponse response = new DataResponse();
        try {
            Blog blog = blogRepository.findBlogById(id);
            if (blog.toString()==null) {
                response.setStatus(Constants.NOT_FOUND);
                response.setMessage(Constants.LIST_NOT_FOUND);
                return response;
            }
            response.setStatus(Constants.SUCCESS);
            response.setResult(blog);
            return response;
        }catch (Exception e){
            response.setStatus(Constants.ERROR);
            response.setMessage(Constants.SYSTEM_ERROR);
            return response;
        }
    }

    @Override
    public DataResponse getNewestBlogs(Pageable pageable) {
        log.debug("Request getNewestBlogs");
        DataResponse response = new DataResponse();
        try {
            Page<Blog> blogs = blogRepository.getNewestBlogs(pageable);
            if (!blogs.hasContent()){
                response.setStatus(Constants.NOT_FOUND);
                response.setMessage(Constants.LIST_NOT_FOUND);
                return response;
            }
            response.setStatus(Constants.SUCCESS);
            response.setResult(blogs);
            return response;
        }catch (Exception e) {
            response.setStatus(Constants.ERROR);
            response.setMessage(Constants.SYSTEM_ERROR);
            return response;
        }
    }

    @Override
    public DataResponse getBlogsByTopic(Pageable pageable, String topicName) {
        log.debug("Request getBlogsByTopic");
        DataResponse response = new DataResponse();
        try {
            if (topicName==null || topicName.isEmpty()) {
                response.setStatus(Constants.ERROR);
                response.setMessage(Constants.ERROR_GET_BLOG);
                return response;
            }
            Page<Blog> blogs = blogRepository.findByTopicName(topicName, pageable);
            if (!blogs.hasContent()){
                response.setStatus(Constants.NOT_FOUND);
                response.setMessage(Constants.LIST_NOT_FOUND);
                return response;
            }
            response.setStatus(Constants.SUCCESS);
            response.setResult(blogs);
            return response;

        }catch (Exception e) {
            response.setStatus(Constants.ERROR);
            response.setMessage(Constants.SYSTEM_ERROR);
            return response;
        }
    }
}
