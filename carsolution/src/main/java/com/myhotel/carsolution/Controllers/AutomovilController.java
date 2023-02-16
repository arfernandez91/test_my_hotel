package com.myhotel.carsolution.Controllers;

import com.myhotel.carsolution.Models.Automovil;
import com.myhotel.carsolution.Models.Mantencion;
import com.myhotel.carsolution.Models.Vehiculo;
import com.myhotel.carsolution.Services.AutomovilService;
import com.myhotel.carsolution.Services.MantencionService;
import com.myhotel.carsolution.Util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

@RestController
@RequestMapping(value = "/api/v1/automovil")
public class AutomovilController {

    @Autowired
    private AutomovilService automovilService;
    @Autowired
    private MantencionService mantencionService;

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addAutomovil(@Valid @RequestBody Automovil automovil){
        ApiResponse apiResponse = new ApiResponse();
        try{
            automovilService.addNewAutomovil(automovil);
        }catch (IllegalStateException e){
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus(HttpStatus.BAD_REQUEST);
            apiResponse.setErrors(Arrays.asList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
        apiResponse.setMessage("Automóvil addicionado correctamente");
        apiResponse.setStatus(HttpStatus.CREATED);
        return  ResponseEntity.ok(apiResponse);
    }

    @PutMapping(path = "/update/{autoId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateAutomovil(@PathVariable("autoId") Long id, @Valid @RequestBody Automovil automovil){
        ApiResponse apiResponse = new ApiResponse();
        try{
            automovilService.updateAutomovil(id,automovil);
        }catch (IllegalStateException e){
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus(HttpStatus.BAD_REQUEST);
            apiResponse.setErrors(Arrays.asList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
        apiResponse.setMessage("Automóvil actualizado correctamente");
        apiResponse.setStatus(HttpStatus.OK);
        return  ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping(path = "/delete/{autoId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteAutomovil(@PathVariable("autoId") Long id){
        ApiResponse apiResponse = new ApiResponse();
        try{
            automovilService.deleteAutomovil(id);
        }catch (IllegalStateException e){
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus(HttpStatus.BAD_REQUEST);
            apiResponse.setErrors(Arrays.asList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
        apiResponse.setMessage("Automóvil eliminado correctamente");
        apiResponse.setStatus(HttpStatus.OK);
        return  ResponseEntity.ok(apiResponse);
    }

    @GetMapping(path = "/list", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllAutomovil(@RequestParam(defaultValue = "10") Integer size,
                                             @RequestParam(defaultValue = "0") Integer page,
                                             @RequestParam(defaultValue = "") String sort){
        ApiResponse apiResponse = new ApiResponse();
        try{
            Page<Automovil> automovils = automovilService.getAllAutomoviles(size,page,sort);
            apiResponse.setMessage("Listado de Automóviles");
            apiResponse.setStatus(HttpStatus.OK);
            apiResponse.setContent(automovils);
            return  ResponseEntity.ok(apiResponse);
        }catch (Exception e){
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus(HttpStatus.BAD_REQUEST);
            apiResponse.setErrors(Arrays.asList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
    }

    @GetMapping(path = "/listByMarca", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllAutomovilByMarca(@RequestParam(defaultValue = "10") Integer size,
                                                    @RequestParam(defaultValue = "0") Integer page,
                                                    @RequestParam(defaultValue = "") String sort,
                                                    @NotNull @RequestParam String filter) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            Page<Automovil> automovils = automovilService.getAutomovilesByMarca(size,page,sort,filter);
            apiResponse.setMessage("Listado de Automóviles filtrado por marca");
            apiResponse.setStatus(HttpStatus.OK);
            apiResponse.setContent(automovils);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus(HttpStatus.BAD_REQUEST);
            apiResponse.setErrors(Arrays.asList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
    }

    @PutMapping(path = "/mantencion/{autoId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addMantencion(@PathVariable("autoId") Long id, @Valid @RequestBody Mantencion mantencion){
        ApiResponse apiResponse = new ApiResponse();
        try{
            Vehiculo automovil = automovilService.findAutomovilById(id);
            mantencion.setVehiculo(automovil);
            mantencionService.loadMantencionToVehiculo(automovil,mantencion);
        }catch (Exception e){
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus(HttpStatus.BAD_REQUEST);
            apiResponse.setErrors(Arrays.asList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
        apiResponse.setMessage("Se realizo la mantención al automóvil correctamente");
        apiResponse.setStatus(HttpStatus.OK);
        return  ResponseEntity.ok(apiResponse);
    }
    @GetMapping(path = "/mantencion/list/{autoId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllMantencion(@PathVariable("autoId") Long id){
        ApiResponse apiResponse = new ApiResponse();
        try{
            Automovil automovil = automovilService.findAutomovilById(id);
            apiResponse.setMessage("Listado de mantenciones para vehículo con id: "+id);
            apiResponse.setContent(automovil.getMatenciones());
            apiResponse.setStatus(HttpStatus.OK);
            return  ResponseEntity.ok(apiResponse);
        }catch (Exception e){
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus(HttpStatus.BAD_REQUEST);
            apiResponse.setErrors(Arrays.asList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
    }


}
