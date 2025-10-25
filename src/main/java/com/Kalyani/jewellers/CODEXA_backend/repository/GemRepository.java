package com.Kalyani.jewellers.CODEXA_backend.repository;



import com.Kalyani.jewellers.CODEXA_backend.model.Gem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GemRepository extends JpaRepository<Gem, Integer> { }

