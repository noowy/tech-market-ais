package com.technolog.techmarketais.repositories;

import com.technolog.techmarketais.domain.hr.Position;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends CrudRepository<Position, Long>
{
}
