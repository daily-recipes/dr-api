package io.codextension.dr.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "family")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Family extends AbstractPersistable<Long> {

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "chef_id", referencedColumnName = "id")
    private Member chef;

    @ManyToMany
    @JoinTable(name = "FAMILY_MEMBER", joinColumns = @JoinColumn(name = "FAMILY_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "MEMBER_ID", referencedColumnName = "ID"))
    private List<Member> members;

    @OneToMany(mappedBy = "belongsTo")
    private List<Recipe> recipes;

    @Setter(value = AccessLevel.NONE)
    @Version
    @Column(name = "VERSIONNB")
    private int version;
}