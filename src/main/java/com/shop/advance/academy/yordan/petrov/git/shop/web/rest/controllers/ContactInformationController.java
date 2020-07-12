package com.shop.advance.academy.yordan.petrov.git.shop.web.rest.controllers;


import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ContactInformationServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ContactInformationServiceViewModel;
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


    @PostMapping()
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<ContactInformationServiceViewModel> createContactInformation(@RequestBody ContactInformationServiceModel contactInformationServiceModel) {
        ContactInformationServiceViewModel contactInformationServiceViewModel = contactInformationService.createContactInformation(contactInformationServiceModel);
        log.info("Contact Information  created : {}", contactInformationServiceViewModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(contactInformationServiceViewModel);
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<ContactInformationServiceViewModel> updateContactInformation(@PathVariable("id") Long id, @RequestBody ContactInformationServiceModel contactInformationServiceModel) {
        ContactInformationServiceViewModel contactInformationServiceViewModel = contactInformationService.updateContactInformation(contactInformationServiceModel);
        log.info("Contact Information  updated : {}", contactInformationServiceViewModel);
        return ResponseEntity.status(HttpStatus.OK).body(contactInformationServiceViewModel);
    }


    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<ContactInformationServiceViewModel> getContactInformation(@PathVariable("id") final Long id) {
        ContactInformationServiceViewModel contactInformationServiceViewModel = contactInformationService.getContactInformationById(id);
        log.info("Contact Information  found : {}", contactInformationServiceViewModel);
        return ResponseEntity.status(HttpStatus.FOUND).body(contactInformationServiceViewModel);
    }

    @GetMapping()
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<List<ContactInformationServiceViewModel>> getContactInformation() {
        List<ContactInformationServiceViewModel> contactInformationServiceViewModels = contactInformationService.getAllContactInformations();
        log.info("Contact Information Found: {} ", contactInformationServiceViewModels);
        return ResponseEntity.status(HttpStatus.FOUND).body(contactInformationServiceViewModels);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<ContactInformationServiceViewModel> deleteContactInformation(@PathVariable("id") Long id) {
        ContactInformationServiceViewModel contactInformationServiceViewModel = contactInformationService.deleteContactInformationById(id);
        log.info("Contact Information deleted : {}", contactInformationServiceViewModel);
        return ResponseEntity.status(HttpStatus.OK).body(contactInformationServiceViewModel);
    }

}
