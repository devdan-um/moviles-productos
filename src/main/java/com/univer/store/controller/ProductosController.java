package com.univer.store.controller;

import com.univer.store.entity.ProductoEntity;
import com.univer.store.model.request.ProductoRequest;
import com.univer.store.model.response.Notificacion;
import com.univer.store.model.response.ProductoResponse;
import com.univer.store.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductosController {

    @Autowired
    private ProductoRepository repository;

    @GetMapping("/producto/{id}")
    public ProductoEntity productos(@PathVariable Integer id){
        return this.repository.findById(id).get();
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/save/product")
    public ResponseEntity save(@RequestBody ProductoRequest request){

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);

        ProductoEntity entity = new ProductoEntity();
        entity.setNombre(request.getName());
        entity.setSku(request.getSku());
        entity.setCantidad(request.getCantidad());

        ProductoResponse response = new ProductoResponse();
        response.setName(entity.getNombre());
        Notificacion notificacion = new Notificacion();
        notificacion.setLevel("success");
        notificacion.setReason("Exitoso");
        notificacion.setMessage("El producto " + entity.getNombre() +" se registro de manera correcta");
        response.setNotificacion(notificacion);

        try{
            repository.save(entity);
        } catch (DataAccessException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(responseHeaders)
                    .body(response.getNotificacion());
        }

        return ResponseEntity.status(HttpStatus.CREATED).headers(responseHeaders)
                .body(response);
    }

}