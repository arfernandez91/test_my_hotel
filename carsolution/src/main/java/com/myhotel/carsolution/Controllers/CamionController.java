package com.myhotel.carsolution.Controllers;

import com.myhotel.carsolution.Models.Camion;
import com.myhotel.carsolution.Models.Mantencion;
import com.myhotel.carsolution.Models.Vehiculo;
import com.myhotel.carsolution.Services.CamionService;
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
@RequestMapping(value = "/api/v1/camion")
public class CamionController {

    @Autowired
    private CamionService camionService;
    @Autowired
    private MantencionService mantencionService;

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> adicionarCamion(@Valid @RequestBody Camion camion){
        ApiResponse apiResponse = new ApiResponse();
        try{
            camionService.addNewCamion(camion);
        }catch (IllegalStateException e){
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus(HttpStatus.BAD_REQUEST);
            apiResponse.setErrors(Arrays.asList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
        apiResponse.setMessage("Camión addicionado correctamente");
        apiResponse.setStatus(HttpStatus.CREATED);
        return  ResponseEntity.ok(apiResponse);
    }

    @PutMapping(value = "/update/{camionId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> actualizarCamion(@PathVariable("camionId") Long id, @Valid @RequestBody Camion camion){
        ApiResponse apiResponse = new ApiResponse();
        try{
            camionService.updateCamion(id,camion);
        }catch (IllegalStateException e){
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus(HttpStatus.BAD_REQUEST);
            apiResponse.setErrors(Arrays.asList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
        apiResponse.setMessage("Camión actualizado correctamente");
        apiResponse.setStatus(HttpStatus.OK);
        return  ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping(value = "/delete/{camionId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteCamion(@PathVariable("camionId") Long id){
        ApiResponse apiResponse = new ApiResponse();
        try{
            camionService.deleteCamion(id);
        }catch (IllegalStateException e){
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus(HttpStatus.BAD_REQUEST);
            apiResponse.setErrors(Arrays.asList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
        apiResponse.setMessage("Camión eliminado correctamente");
        apiResponse.setStatus(HttpStatus.OK);
        return  ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/list", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllCamiones(@RequestParam(defaultValue = "10") Integer size,
                                             @RequestParam(defaultValue = "0") Integer page,
                                             @RequestParam(defaultValue = "") String sort){
        ApiResponse apiResponse = new ApiResponse();
        try{
            Page<Camion> camions = camionService.getAllCamiones(size,page,sort);
            apiResponse.setMessage("Listado de Camiones");
            apiResponse.setStatus(HttpStatus.OK);
            apiResponse.setContent(camions);
            return  ResponseEntity.ok(apiResponse);
        }catch (Exception e){
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus(HttpStatus.BAD_REQUEST);
            apiResponse.setErrors(Arrays.asList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
    }

    @GetMapping(value = "/find/{camionId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOneById(@PathVariable(name = "camionId") Long id){
        ApiResponse apiResponse = new ApiResponse();
        try{
            Camion camion = camionService.findCamionById(id);
            apiResponse.setMessage("Datos del camión");
            apiResponse.setStatus(HttpStatus.OK);
            apiResponse.setContent(camion);
            return  ResponseEntity.ok(apiResponse);
        }catch (Exception e){
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus(HttpStatus.BAD_REQUEST);
            apiResponse.setErrors(Arrays.asList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
    }

    @GetMapping(value = "/listByMarca", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllCamionesByMarca(@RequestParam(defaultValue = "10") Integer size,
                                                    @RequestParam(defaultValue = "0") Integer page,
                                                    @RequestParam(defaultValue = "") String sort,
                                                    @NotNull @RequestParam String filter) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            Page<Camion> camions = camionService.getAutomovilesByMarca(size,page,sort,filter);
            apiResponse.setMessage("Listado de Camiones filtrado por marca");
            apiResponse.setStatus(HttpStatus.OK);
            apiResponse.setContent(camions);
            return ResponseEntity.ok(camions);
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus(HttpStatus.BAD_REQUEST);
            apiResponse.setErrors(Arrays.asList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
    }
    @PutMapping(value = "/mantencion/{camionId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addMantencion(@PathVariable("camionId") Long id, @RequestBody Mantencion mantencion){
        ApiResponse apiResponse = new ApiResponse();
        try{
            Vehiculo camion = camionService.findCamionById(id);
            mantencion.setVehiculo(camion);
            mantencionService.loadMantencionToVehiculo(camion,mantencion);
        }catch (Exception e){
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus(HttpStatus.BAD_REQUEST);
            apiResponse.setErrors(Arrays.asList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
        apiResponse.setMessage("Se realizo la mantención al camión correctamente");
        apiResponse.setStatus(HttpStatus.OK);
        return  ResponseEntity.ok(apiResponse);
    }
    @GetMapping(value = "/mantencion/list/{camionId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMantencionList(@PathVariable("camionId") Long id){
        ApiResponse apiResponse = new ApiResponse();
        try{
            Camion camion = camionService.findCamionById(id);
            apiResponse.setMessage("Listado de mantenciones para vehículo con id: "+id);
            apiResponse.setContent(camion.getMatenciones());
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
