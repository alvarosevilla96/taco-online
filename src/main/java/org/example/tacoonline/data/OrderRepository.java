package org.example.tacoonline.data;

import org.example.tacoonline.model.Order;

public interface OrderRepository {
    Order save(Order order);
}
