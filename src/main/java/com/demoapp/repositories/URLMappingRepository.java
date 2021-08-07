package com.demoapp.repositories;

import com.demoapp.entity.URLMappingEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface URLMappingRepository extends CrudRepository<URLMappingEntity, Long> {

}
