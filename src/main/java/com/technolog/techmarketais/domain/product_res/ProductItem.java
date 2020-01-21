package com.technolog.techmarketais.domain.product_res;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class ProductItem
{

    @Id
    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Item item;

    @Id
    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Warehouse warehouse;

    @NotNull
    private Long amount;
}
