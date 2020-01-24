package com.technolog.techmarketais.repositories;

import com.technolog.techmarketais.domain.product_res.Item;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends PagingAndSortingRepository<Item, Long>
{
    List<Item> findItemsByTitle(String title);
}
