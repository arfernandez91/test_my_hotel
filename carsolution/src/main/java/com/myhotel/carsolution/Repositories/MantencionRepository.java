package com.myhotel.carsolution.Repositories;

import com.myhotel.carsolution.Models.Mantencion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MantencionRepository extends JpaRepository<Mantencion, Long> {
}
