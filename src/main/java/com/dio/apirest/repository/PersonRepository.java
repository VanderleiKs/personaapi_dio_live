package com.dio.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dio.apirest.entity.Person;

@Repository
public interface PersonRepository  extends JpaRepository<Person, Long> {

    //find x where em primary attribute
    @Query("select x from Person x where x.cpf = ?1")
    Person findByCPF(String cpf);
}
