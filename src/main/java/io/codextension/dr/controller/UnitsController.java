package io.codextension.dr.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.codextension.dr.model.MeasurementUnit;
import io.codextension.dr.repository.UnitsRepository;

@RestController
@RequestMapping("/config")
@CrossOrigin
public class UnitsController {

    @Autowired
    private UnitsRepository unitsRepository;

    @GetMapping(value = "/unit", produces = "application/json")
    public List<MeasurementUnit> getUnits() throws NotFoundException {
        if (unitsRepository.count() == 0) {
            throw new NotFoundException();
        }
        Iterable<MeasurementUnit> results = unitsRepository.findAll();
        return StreamSupport.stream(results.spliterator(), false).collect(Collectors.toList());
    }

    @PostMapping(value = "/unit", produces = "application/json", consumes = "application/json")
    public Long saveUnit(@Valid @RequestBody MeasurementUnit unit) {
        MeasurementUnit results = unitsRepository.save(unit);
        return results.getId();
    }

    @PatchMapping(value = "/unit", produces = "application/json", consumes = "application/json")
    public Long updateUnit(@Valid @NotNull @RequestBody MeasurementUnit unit) throws NotFoundException {
        if (unit.getId() == null || unit.getId() == 0) {
            throw new NotFoundException();
        }
        MeasurementUnit results = unitsRepository.save(unit);
        return results.getId();
    }

    @DeleteMapping(value = "/unit/{unitId}", produces = "application/json", consumes = "application/json")
    public Boolean deleteUnit(@PathVariable(required = true) Long unitId) throws NotFoundException {
        if (unitId == 0) {
            throw new NotFoundException();
        }
        unitsRepository.deleteById(unitId);
        return true;
    }

    @GetMapping(value = "/unit/{unitId}", produces = "application/json", consumes = "application/json")
    public MeasurementUnit getUnit(@PathVariable(required = true) Long unitId) {
        return unitsRepository.findById(unitId).orElseThrow();
    }
}