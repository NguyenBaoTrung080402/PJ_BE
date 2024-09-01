package com.DSTA.PJ_BE.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "blog_topic")
public class BlogTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "topic_name")
    private String topicName;

}