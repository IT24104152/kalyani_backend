package com.Kalyani.jewellers.CODEXA_backend.repository;

//import com.Kalyani.jewellers.CODEXA_backend.model.Category;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.Optional;
//
//public interface CategoryRepository extends JpaRepository<Category, Integer> {
//    Optional<Category> findByCategoryName(String categoryName);
//}






import com.Kalyani.jewellers.CODEXA_backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByCategoryNameIgnoreCase(String name);
}

