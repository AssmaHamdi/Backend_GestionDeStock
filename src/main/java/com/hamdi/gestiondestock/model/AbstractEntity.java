package com.hamdi.gestiondestock.model;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.Instant;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity implements Serializable{

    @Id
    @GeneratedValue
    private Integer Id;

    @CreatedDate
    @Column(name ="CreationDate", nullable = false, updatable = false)
    private Instant CreationDate;

    @LastModifiedDate
    @Column(name ="LastModifiedDate")
    private Instant LastModifiedDate;






}