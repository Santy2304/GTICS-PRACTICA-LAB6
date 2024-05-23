package com.example.practicalb6gtics.Repository;

import com.example.practicalb6gtics.Entity.Dispositivo;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DispositivoRepository extends JpaRepository<Dispositivo, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM dispositivos WHERE habilitado=1")
    List<Dispositivo> listarDispositivosValidos();

    @Transactional
    @Modifying
    @Query(value = "update dispositivos set habilitado = 0  where idDispositivos =?1" , nativeQuery = true)
    void eliminarDispositivoPorId(int idDispositivo);


}
