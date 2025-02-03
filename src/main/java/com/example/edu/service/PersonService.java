package com.example.edu.service;

import com.example.edu.dto.PersonDTO;
import com.example.edu.dto.PersonRegisterDto;

import java.util.List;

public interface PersonService {
    List<PersonDTO> getAllPersons();

    PersonDTO getPersonById(Long id);

    PersonDTO getPersonByUsername(String username);

    PersonDTO createPerson(PersonRegisterDto dto);

    PersonDTO updatePerson(Long id, PersonDTO dto);

    void deletePerson(Long id);
}
