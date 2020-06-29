package com.shop.advance.academy.yordan.petrov.git.shop.web.controllers;


import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ContactInformationServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ContactInformationServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.CountryServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.OrderServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.ContactInformationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/contactinformation")
@Slf4j
public class ContactInformationController {

    private final ContactInformationService contactInformationService;

    @Autowired
    public ContactInformationController(ContactInformationService contactInformationService) {
        this.contactInformationService = contactInformationService;
    }


    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<ContactInformationServiceModel> createContactInformation(@RequestBody ContactInformationServiceModel contactInformationServiceModel) {
        contactInformationService.createContactInformation(contactInformationServiceModel);
        return new ResponseEntity<>(contactInformationServiceModel, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<> updateContactInformation(@PathVariable("id") Long id, @RequestBody ContactInformationServiceModel contactInformationServiceModel) {
        contactInformationService.updateContactInformation(contactInformationServiceModel);
    }


    @GetMapping("/{id}")
    public ContactInformationServiceViewModel getContactInformation(@PathVariable("id") final Long id) {
        return contactInformationService.getContactInformationById(id);
    }

    @GetMapping()
    public ResponseEntity<List<ContactInformationServiceViewModel>> getContactInformations() {

        List<ContactInformationServiceViewModel> contactInformationServiceViewModels = contactInformationService.getAllContactInformations();

        log.info("Contact Information Found: {} ", contactInformationServiceViewModels);

        return ResponseEntity.status(HttpStatus.FOUND).body(contactInformationServiceViewModels);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<> deleteContactInformation(@PathVariable("id") Long id) {
        contactInformationService.deleteContactInformationById(id);
    }

}
