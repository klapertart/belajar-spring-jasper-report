package com.klapertart.jasperreport.repositories;

import com.klapertart.jasperreport.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author tritr
 * @since 11/28/2023
 */

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
