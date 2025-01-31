package com.example.edu.service.impl;

import com.example.edu.dto.PersonDTO;
import com.example.edu.entity.Department;
import com.example.edu.entity.Person;
import com.example.edu.exception.DepartmentNotFoundException;
import com.example.edu.exception.PersonNotFoundException;
import com.example.edu.repository.DepartmentRepository;
import com.example.edu.repository.PersonRepository;
import com.example.edu.service.PersonService;
import com.example.edu.utils.PersonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public List<PersonDTO> getAllPersons() {
        return personRepository.findAll().stream()
                .map(PersonMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PersonDTO getPersonById(Long id) {
        return personRepository.findById(id)
                .map(PersonMapper::convertToDTO)
                .orElseThrow(() -> new PersonNotFoundException("Person not found!"));
    }

    @Override
    public PersonDTO createPerson(PersonDTO dto) {
        Optional<Department> department = departmentRepository.findByName(dto.getDepartmentName());
        if (department.isEmpty()) {
            throw new DepartmentNotFoundException("Department not found");
        }

        Person person = new Person();
        person.setName(dto.getName());
        person.setEmail(dto.getEmail());
        person.setDepartment(department.get());

        return PersonMapper.convertToDTO(personRepository.save(person));
    }

    @Override
    public PersonDTO updatePerson(Long id, PersonDTO dto) {
        Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException("Person not found!"));

        person.setName(dto.getName());
        person.setEmail(dto.getEmail());
        person.setPhotoUrl(dto.getPhotoUrl());

        Optional<Department> department = departmentRepository.findByName(dto.getDepartmentName());
        department.ifPresent(person::setDepartment);

        return PersonMapper.convertToDTO(personRepository.save(person));
    }

    @Override
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }
}
