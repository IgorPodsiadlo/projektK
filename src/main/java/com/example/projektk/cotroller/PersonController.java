package com.example.projektk.cotroller;

import com.example.projektk.contract.AddressDto;
import com.example.projektk.contract.PersonDto;
import com.example.projektk.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/person/")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping
    public ResponseEntity getAll(){
        return ResponseEntity.ok(personService.getAll());
    }

    @PostMapping
    public ResponseEntity addPerson(@RequestBody PersonDto personDto){
        return ResponseEntity.ok(personService.save(personDto));

    }

    @GetMapping("/{id}")
    public ResponseEntity getPersonById(@PathVariable Integer id){
        return ResponseEntity.ok(personService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity updatePerson(@PathVariable Integer id, @RequestBody PersonDto personDto){
        return ResponseEntity.ok(personService.update(id, personDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePerson(@PathVariable Integer id){
        return ResponseEntity.ok(personService.delete(id));
    }

    @GetMapping("/{id}/addresses")
    public ResponseEntity getAddresses(@PathVariable Integer id){
        return ResponseEntity.ok(personService.getAllAddresses(id));
    }

    @PostMapping("/{id}/addresses")
    public ResponseEntity saveAddress(@PathVariable Integer id, @RequestBody AddressDto addressDto){
        return ResponseEntity.ok(personService.addAddress(id, addressDto));
    }
}