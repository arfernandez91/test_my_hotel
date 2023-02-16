package com.myhotel.carsolution.Repositories;

import com.myhotel.carsolution.Models.Automovil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AutomovilRepository extends JpaRepository<Automovil, Long> {

    Page<Automovil> findByMarcaContaining(String marca, Pageable pageable);
}
