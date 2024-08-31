package com.DSTA.PJ_BE.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", length = 1000)
    private String title;

    @Column(name = "description", length = 1000)
    private String description;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "author")
    private String author;

    @Column(name = "avatar_image", length = 500)
    private String avatarImage;

    @Column(name = "publish_begin_time")
    private LocalDateTime publishBeginTime;

    @Column(name = "publish_status")
    private Boolean publishStatus;

    @Column(name = "is_feature")
    private String isFeature;

    @Column(name = "read_count")
    private Integer readCount;

    @ManyToOne
    @JoinColumn(name = "blog_topic_id")
    private BlogTopic blogTopic;

}