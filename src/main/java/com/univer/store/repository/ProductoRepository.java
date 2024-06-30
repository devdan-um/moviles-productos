package com.univer.store.repository;

import com.univer.store.entity.ProductoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository
        extends CrudRepository<ProductoEntity, Integer> {
}
