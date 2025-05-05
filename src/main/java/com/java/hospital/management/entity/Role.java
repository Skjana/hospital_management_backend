package com.java.hospital.management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name="role")
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @Column(name = "role_id")
    private Integer id;
    @Column(name = "role_name")
    private String name;
    @Column(name = "is_active")
    private Boolean isActive;
}
