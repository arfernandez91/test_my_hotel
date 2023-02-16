package com.myhotel.carsolution.Repositories;

import com.myhotel.carsolution.Models.Camion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CamionRepository extends JpaRepository<Camion,Long> {
    Page<Camion> findByMarcaContaining(String marca, Pageable pageable);
}
