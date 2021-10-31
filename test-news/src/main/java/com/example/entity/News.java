package com.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "news")
public class News {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;


    @ManyToOne
    private User user;

    @Column(nullable = false)
    @Size(max = 100)
    private String newsTitle;


    @Size(max = 500)
    private String newsText;

    private boolean approvedByAdmin;

    @CreationTimestamp
    private Timestamp createdAt;


    private Timestamp approvedTime;

}
