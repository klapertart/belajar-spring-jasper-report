package com.klapertart.jasperreport.repositories;

import com.klapertart.jasperreport.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author tritr
 * @since 12/5/2023
 */
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByCategory_Name(String name, Sort sort);

    Page<Product> findAllByCategory_Name(String name, Pageable pageable);

}
