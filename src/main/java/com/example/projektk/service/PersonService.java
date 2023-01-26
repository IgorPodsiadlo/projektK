package com.example.projektk.service;

import com.example.projektk.contract.AddressDto;
import com.example.projektk.contract.PersonDto;
import com.example.projektk.model.Address;
import com.example.projektk.model.Person;
import com.example.projektk.repository.AddressRepository;
import com.example.projektk.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    PersonRepository personRepository;
    AddressRepository addressRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, AddressRepository addressRepository) {
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
    }

    public List<PersonDto> getAll() {
        List<PersonDto> personDtos = new ArrayList<>();
        personRepository.findAll().forEach(person -> {
            PersonDto personDto = new PersonDto();
            personDto.setId(person.getId());
            personDto.setFirstName(person.getFirstName());
            personDto.setLastName(person.getLastName());
            personDtos.add(personDto);
        });
        return personDtos;
    }

    public void save(PersonDto personDto) {
        Person person = new Person();
        person.setFirstName(personDto.getFirstName());
        person.setLastName(personDto.getLastName());
        personRepository.save(person);
    }
    public void delete(Integer id) {
        Person personexists = personRepository.getById(id);
        if (personexists != null) {
            personRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found");
        }
    }

    public PersonDto update(Integer id, PersonDto personDto) {
        Person person = personRepository.getById(personDto.getId());
        if (person != null) {
            person.setFirstName(personDto.getFirstName());
            person.setLastName(personDto.getLastName());
            personRepository.save(person);
            return personDto;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found");
        }
    }

    public void addAddress(Integer id, AddressDto addressDto) {
        Person existingPerson = personRepository.getById(id);
        List<Address> existingAddresses = existingPerson.getAddresses();
        existingAddresses.add(addressDto);

        personRepository.save(existingPerson);
    }

    public List<Address> getAllAddresses(Integer personid) {
        return personRepository.getById(personid).getAddresses();

    }
    public Person findById(Integer id){
        Person existingPerson = personRepository.getById(id);
        if(existingPerson!=null)
            return existingPerson;
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

}