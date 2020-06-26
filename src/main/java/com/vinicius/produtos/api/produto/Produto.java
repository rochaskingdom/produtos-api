package com.vinicius.produtos.api.produto;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
public class Produto extends PanacheEntity {

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    @Column(columnDefinition = "float")
    private Double price;

}
