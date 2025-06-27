package org.example.tacoonline.data;

import org.example.tacoonline.model.Taco;
import org.springframework.data.repository.CrudRepository;


public interface TacoRepository extends CrudRepository<Taco, Long> {
}
