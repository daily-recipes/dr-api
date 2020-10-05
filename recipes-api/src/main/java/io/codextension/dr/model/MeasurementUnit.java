package io.codextension.dr.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "unit")
@Data
@EqualsAndHashCode(callSuper = true)
public class MeasurementUnit  extends AbstractPersistable<Long> {
    @NotNull
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "unit")
    private List<Ingredient> ingredients;

    private String description;
}