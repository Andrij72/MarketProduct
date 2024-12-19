package com.akul.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.akul.market.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
