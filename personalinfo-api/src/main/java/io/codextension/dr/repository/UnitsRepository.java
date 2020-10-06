package io.codextension.dr.repository;

import io.codextension.dr.model.MeasurementUnit;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitsRepository extends CrudRepository<MeasurementUnit, Long> {

}
