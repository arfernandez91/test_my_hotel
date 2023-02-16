package com.myhotel.carsolution.Services;

import com.myhotel.carsolution.Models.Mantencion;
import com.myhotel.carsolution.Models.Vehiculo;
import com.myhotel.carsolution.Repositories.MantencionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MantencionService {
    @Autowired
    private MantencionRepository mantencionRepository;

    public void loadMantencionToVehiculo(Vehiculo vehiculo, Mantencion mantencion){
        mantencionRepository.save(mantencion);
    }

}
