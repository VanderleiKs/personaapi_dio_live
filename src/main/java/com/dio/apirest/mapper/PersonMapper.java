package com.dio.apirest.mapper;

import com.dio.apirest.dto.request.PersonDTO;
import com.dio.apirest.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd/MM/yyyy")
    Person toModel(PersonDTO person);

    PersonDTO toDTO(Person personDTO);
}
