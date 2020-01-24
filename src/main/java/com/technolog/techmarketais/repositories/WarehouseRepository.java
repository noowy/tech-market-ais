package com.technolog.techmarketais.repositories;

import com.technolog.techmarketais.domain.product_res.Warehouse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends CrudRepository<Warehouse, Long>
{
}
