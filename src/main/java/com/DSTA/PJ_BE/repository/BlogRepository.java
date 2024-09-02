package com.DSTA.PJ_BE.repository;

import com.DSTA.PJ_BE.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    @Query(value = "SELECT b FROM Blog b JOIN BlogTopic t ON b.blogTopic.id = t.id WHERE t.topicName = :topicName order by b.publishBeginTime desc")
    Page<Blog> findByTopicName(@Param("topicName") String topicName, Pageable pageable);

    @Query(value = "SELECT b FROM Blog b order by b.publishBeginTime desc")
    Page<Blog> getNewestBlogs(Pageable pageable);

    Blog findBlogById(Long id);
}
