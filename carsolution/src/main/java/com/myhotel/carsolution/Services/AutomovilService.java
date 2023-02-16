package com.myhotel.carsolution.Services;

import com.myhotel.carsolution.Repositories.AutomovilRepository;
import com.myhotel.carsolution.Models.Automovil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AutomovilService {
    @Autowired
    private AutomovilRepository automovilRepository;

    public void addNewAutomovil(Automovil automovil){
        automovilRepository.save(automovil);
    }

    @Transactional(rollbackFor  = {IllegalStateException.class})
    public void updateAutomovil(Long id, Automovil automovil) {
        Automovil aut = automovilRepository.findById(id)
                .orElseThrow(()->new IllegalStateException(
                        "El automóvil con id "+id+" no existe"
                ));
        aut.setTipo(automovil.getTipo());
        aut.setAnno(automovil.getAnno());
        aut.setCilindrada(automovil.getCilindrada());
        aut.setNumero_puertas(automovil.getNumero_puertas());
        aut.setMarca(automovil.getMarca());
        aut.setCapacidad_maletero(automovil.getCapacidad_maletero());
        aut.setPatente(automovil.getPatente());
        aut.setCantidad_pasajeros(automovil.getCantidad_pasajeros());
        aut.setModelo(automovil.getModelo());
        aut.setKilometraje(automovil.getKilometraje());
    }
    public void deleteAutomovil(Long id) {
        boolean exists = automovilRepository.existsById(id);
        if(exists){
            automovilRepository.deleteById(id);
        }else{
            throw new IllegalStateException("Automóvil no encontrado");
        }
    }

    public Page<Automovil> getAllAutomoviles(Integer size, Integer page, String sort){
        Pageable paging = null;
        if(!sort.equals("")) {
            paging = PageRequest.of(page, size, Sort.by(sort));
        }else{
            paging = PageRequest.of(page, size);
        }
        Page<Automovil> automovils = automovilRepository.findAll(paging);
        return automovils;
    }

    public Automovil findAutomovilById(Long id) {
        Optional<Automovil> automovil = automovilRepository.findById(id);
        if(automovil.isPresent()) {
            return automovil.get();
        }else{
            throw new IllegalStateException("El Automovil con id "+id+" no existe");
        }
    }

    public Page<Automovil> getAutomovilesByMarca(Integer size, Integer page, String sort, String filter){
        Pageable paging = null;
        if(!sort.equals("")) {
            paging = PageRequest.of(page, size, Sort.by(sort));
        }else{
            paging = PageRequest.of(page, size);
        }
        if(filter.equals("") || filter == null){
            throw new IllegalStateException("El campo filtro no puede estar vacío");
        }
        Page<Automovil> automovils = automovilRepository.findByMarcaContaining(filter, paging);
        return automovils;
    }

}
