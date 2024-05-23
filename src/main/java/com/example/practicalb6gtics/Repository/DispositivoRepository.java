package com.example.practicalb6gtics.Repository;

import com.example.practicalb6gtics.Entity.Dispositivo;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DispositivoRepository extends JpaRepository<Dispositivo, Integer> {
}
