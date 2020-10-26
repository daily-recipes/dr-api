package io.codextension.dr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode()
public class Member {

    @JsonIgnore
    private List<Family> memberOf;
    @JsonIgnore
    private List<Family> chefOf;
}