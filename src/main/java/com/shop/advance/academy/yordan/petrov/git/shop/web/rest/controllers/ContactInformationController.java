package com.shop.advance.academy.yordan.petrov.git.shop.web.rest.controllers;


import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.ContactInformationServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.ContactInformationServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.ContactInformationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.util.List;
/**
 * Class controller for the contact information .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@RestController
@RequestMapping("api/contactinformation")
@Slf4j
public class ContactInformationController {

    private final ContactInformationService contactInformationService;

    /**
     * Constructor
     */
    @Autowired
    public ContactInformationController(ContactInformationService contactInformationService) {
        this.contactInformationService = contactInformationService;
    }


    @PostMapping()
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<ContactInformationServiceViewModel> createContactInformation(@RequestBody ContactInformationServiceModel contactInformationServiceModel) {
        ContactInformationServiceViewModel contactInformationServiceViewModel = contactInformationService.createContactInformation(contactInformationServiceModel);
        URI location = MvcUriComponentsBuilder.fromMethodName(ContactInformationController.class, "createContactInformation", ContactInformationServiceViewModel.class)
                .pathSegment("{id}").buildAndExpand(contactInformationServiceViewModel.getId()).toUri();
        log.info("Contact Information  created : {} {}", contactInformationServiceViewModel, location);
        return ResponseEntity.created(location).body(contactInformationServiceViewModel);
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<ContactInformationServiceViewModel> updateContactInformation(@RequestBody ContactInformationServiceModel contactInformationServiceModel) {
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
