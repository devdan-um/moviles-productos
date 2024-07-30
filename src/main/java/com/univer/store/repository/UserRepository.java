package com.univer.store.repository;

import com.univer.store.entity.UserEntity;
import com.univer.store.model.request.UsuarioRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u WHERE u.usuario = :usuario AND u.password = :password")
    public UserEntity checkLogin(String usuario, String password);
}
