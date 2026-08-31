package com.cocobar.cocoapi.repository;

import com.cocobar.cocoapi.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}