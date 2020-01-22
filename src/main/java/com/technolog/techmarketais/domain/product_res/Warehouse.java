package com.technolog.techmarketais.domain.product_res;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class Warehouse
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private Long capacity;

    @NotNull
    @NotBlank
    private String address;

    @OneToMany(mappedBy = "warehouse")
    private List<ProductItem> products;
}
