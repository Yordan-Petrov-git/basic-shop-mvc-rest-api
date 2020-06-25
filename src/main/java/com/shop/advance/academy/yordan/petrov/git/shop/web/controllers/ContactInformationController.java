package com.shop.advance.academy.yordan.petrov.git.shop.web.controllers;


import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ContactInformationServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ContactInformationServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.ContactInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/contactinformation")
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
        public void updateContactInformation(@PathVariable("id") Long id,@RequestBody ContactInformationServiceModel contactInformationServiceModel) {
        contactInformationService.updateContactInformation(contactInformationServiceModel);
    }


    @GetMapping("/{id}")
    public ContactInformationServiceViewModel getContactInformation(@PathVariable("id")final Long id) {
        return contactInformationService.getContactInformationById(id);
    }

    @GetMapping()
    public List<ContactInformationServiceViewModel> getContactInformations() {
        return contactInformationService.getAllContactInformations();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteContactInformation(@PathVariable("id") Long id) {
        contactInformationService.deleteContactInformationById(id);
    }

}
