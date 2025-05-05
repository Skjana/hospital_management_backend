package com.java.hospital.management.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Table(name = "faqs")
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class FAQQuestion {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2",strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column(name = "category")
    private String category;
    @Column(name = "question")
    private String question;
    @Column(name = "answer")
    private String answer;
    @Column(name = "is_deleted")
    private Boolean isDeleted;
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;
}
