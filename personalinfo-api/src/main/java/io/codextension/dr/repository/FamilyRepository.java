package io.codextension.dr.repository;

import io.codextension.dr.model.Family;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyRepository extends CrudRepository<Family, Long> {
    
}