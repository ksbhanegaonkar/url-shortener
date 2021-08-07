package com.demoapp.repositories;

import com.demoapp.entity.URLMappingEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface URLMappingRepository extends CrudRepository<URLMappingEntity, Long> {
    Optional<URLMappingEntity> findByShortUrl(String shortUrl);
    Optional<URLMappingEntity> findByLongUrl(String longUrl);

}
