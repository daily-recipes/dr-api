package io.codextension.dr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@EqualsAndHashCode()
public class MeasurementUnit {
    @NotNull
    private String name;
    @JsonIgnore
    private List<Ingredient> ingredients;
    private String description;
}