package com.alta.e_commerce.repositories;

import java.util.Optional;

import com.alta.e_commerce.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    Optional<Order> findByCart_CartId(String cartId);
}
