package io.codextension.dr.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Entity
@Table(name = "ingredient")
@Data
@EqualsAndHashCode(callSuper = true)
public class Ingredient extends AbstractPersistable<Long> {

    private String name;

    @ManyToOne
    @JoinColumn(name = "unit_id", referencedColumnName = "id")
    private MeasurementUnit unit;

    private String notes;

    @JsonIgnore
    @ManyToMany(mappedBy = "ingredients")
    private List<Recipe> ingredientOf;

    @Setter(value = AccessLevel.NONE)
    @Version
    @Column(name = "VERSIONNB")
    private int version;
}