package io.codextension.dr.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Entity
@Table(name = "member")
@Data
@EqualsAndHashCode(callSuper = true)
public class Member extends AbstractPersistable<String> {

    private String email;

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "members")
    private List<Family> memberOf;

    @JsonIgnore
    @OneToMany(mappedBy = "chef")
    private List<Family> chefOf;

    @Setter(value = AccessLevel.NONE)
    @Version
    @Column(name = "VERSIONNB")
    private int version;
}