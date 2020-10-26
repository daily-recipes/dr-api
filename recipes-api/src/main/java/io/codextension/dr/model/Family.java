package io.codextension.dr.model;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@EqualsAndHashCode()
public class Family {

    private String name;
    private String description;
    private Member chef;
    private List<Member> members;
    private List<Recipe> recipes;
}