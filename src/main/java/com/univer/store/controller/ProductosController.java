package com.univer.store.controller;

import com.univer.store.entity.ProductoEntity;
import com.univer.store.model.request.ProductoRequest;
import com.univer.store.model.response.Notificacion;
import com.univer.store.model.response.ProductoResponse;
import com.univer.store.repository.ProductoRepository;
import com.univer.store.util.StoreUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductosController {

    @Autowired
    private ProductoRepository repository;

    @GetMapping("/producto/{id}")
    public ResponseEntity productos(@PathVariable Integer id){
        Optional<ProductoEntity> entity = this.repository.findById(id);
        ProductoResponse response = new ProductoResponse();

        if (entity.isPresent()){
            response.setName(entity.get().getNombre());
            response.setSku(entity.get().getSku());
            response.setCantidad(entity.get().getCantidad());
            response.setNotificacion(StoreUtil.retrieveOK("Consulta generada de manera correcta"));
            return new ResponseEntity(response, HttpStatus.OK);
        }else {
            return ResponseEntity.noContent().build();
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
    @PostMapping("/save/product")
    public ResponseEntity save(@RequestBody ProductoRequest request){

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        //responseHeaders.setAccessControlAllowOrigin("http://localhost:5173");
        //responseHeaders.setAccessControlMaxAge(Duration.ofHours(2));

        ProductoResponse response = new ProductoResponse();

        try{
            ProductoEntity entity = new ProductoEntity();
            entity.setNombre(request.getName());
            entity.setSku(request.getSku());
            entity.setCantidad(request.getCantidad());

            repository.save(entity);

            response.setName(entity.getNombre());

            response.setNotificacion(StoreUtil.retrieveOK("El producto " + request.getName() +" se registro de manera correcta"));

            return ResponseEntity.status(HttpStatus.CREATED).headers(responseHeaders)
                    .body(response);

        } catch (DataAccessException e){
            Notificacion notificacion = new Notificacion();
            notificacion.setLevel("error");
            notificacion.setReason("Error");
            notificacion.setMessage("El producto " + request.getName() +" no se ha registrado de manera correcta");
            response.setNotificacion(notificacion);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(responseHeaders)
                    .body(response.getNotificacion());
        }

    }

}