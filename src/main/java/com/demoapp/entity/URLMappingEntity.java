package com.demoapp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "URL_MAPPING")
@Getter
@Setter
public class URLMappingEntity {
    @Id
    @GeneratedValue
    Long id;

}
