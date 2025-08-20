package com.example.czelaya.api_rest.repository;

import com.example.czelaya.api_rest.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository <Category, Long>{

    Optional<Category> findByName(String nameCategory);
    boolean existsByName(String nameCategory);
}
