package com.happyfleet.app.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<T> {

    @Column(length = 10)
    @JsonIgnore @CreatedBy protected T createdBy;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    protected Date createdAt;

    @Column(length = 10)
    @JsonIgnore @LastModifiedBy protected T lastModifiedBy;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    protected Date lastUpdatedAt;
}