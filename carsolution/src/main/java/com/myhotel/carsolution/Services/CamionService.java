package com.myhotel.carsolution.Services;

import com.myhotel.carsolution.Repositories.CamionRepository;
import com.myhotel.carsolution.Models.Camion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CamionService {
    @Autowired
    private CamionRepository camionRepository;

    public void addNewCamion(Camion camion){
        camionRepository.save(camion);
    }

    @Transactional(rollbackFor  = {IllegalStateException.class})
    public void updateCamion(Long id, Camion camion) {
        Camion cam = camionRepository.findById(id)
                .orElseThrow(()->new IllegalStateException("Camión ncon id: "+id+" encontrado"));
        cam.setTipo(camion.getTipo());
        cam.setAnno(camion.getAnno());
        cam.setCilindrada(camion.getCilindrada());
        cam.setCantidad_ejes(camion.getCantidad_ejes());
        cam.setMarca(camion.getMarca());
        cam.setCapacidad(camion.getCapacidad());
        cam.setPatente(camion.getPatente());
        cam.setModelo(camion.getModelo());
        cam.setKilometraje(camion.getKilometraje());
    }
    public void deleteCamion(Long id) {
        boolean exists = camionRepository.existsById(id);
        if(exists){
            camionRepository.deleteById(id);
        }else{
            throw new IllegalStateException("Camión no encontrado");
        }
    }

    public Page<Camion> getAllCamiones(Integer size, Integer page, String sort){
        Pageable paging = null;
        if(!sort.equals("")) {
            paging = PageRequest.of(page, size, Sort.by(sort));
        }else{
            paging = PageRequest.of(page, size);
        }
        Page<Camion> camions = camionRepository.findAll(paging);
        return camions;
    }

    public Camion findCamionById(Long id) {
        Optional<Camion> camion = camionRepository.findById(id);
        if(camion.isPresent()) {
            return camion.get();
        }else{
            throw new IllegalStateException("El camión con id "+id+" no existe");
        }
    }

    public Page<Camion> getAutomovilesByMarca(Integer size, Integer page, String sort, String filter){
        Pageable paging = null;
        if(!sort.equals("")) {
            paging = PageRequest.of(page, size, Sort.by(sort));
        }else{
            paging = PageRequest.of(page, size);
        }
        if(filter.equals("") || filter == null){
            throw new IllegalStateException("El campo filtro no puede estar vacío");
        }
        Page<Camion> camions = camionRepository.findByMarcaContaining(filter, paging);
        return camions;
    }

}
