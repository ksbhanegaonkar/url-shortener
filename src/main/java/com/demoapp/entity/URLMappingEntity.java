package com.demoapp.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "URL_MAPPING")
@Getter
@Setter
public class URLMappingEntity {
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(
            name = "generator",
            strategy = "org.hibernate.id.IdentityGenerator"
    )
    @Column(name = "ID")
    Long id;

    @Column(name = "shortUrl")
    private String shortUrl;

    @Column(name = "LONG_URL")
    private String longUrl;

    public URLMappingEntity() {
    }

    public URLMappingEntity(String shortUrl, String longURL) {
        this.shortUrl = shortUrl;
        this.longUrl = longURL;
    }
}
