package com.hafidtech.gunungcondongcom.repository;

import com.hafidtech.gunungcondongcom.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
