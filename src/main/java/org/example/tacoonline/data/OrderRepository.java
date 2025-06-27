package org.example.tacoonline.data;

import org.example.tacoonline.model.Order;
import org.springframework.data.repository.CrudRepository;


public interface OrderRepository extends CrudRepository<Order, Long> {
}
