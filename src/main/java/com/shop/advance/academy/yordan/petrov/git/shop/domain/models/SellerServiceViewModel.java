package com.shop.advance.academy.yordan.petrov.git.shop.domain.models;

import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Address;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.ContactInformation;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Item;

import java.util.HashSet;
import java.util.Set;

public class SellerServiceViewModel {

    private Long id;
    private String name;
    private Set<ItemServiceViewModel> stock = new HashSet<>();
    private ContactInformationServiceViewModel contactInformation;
    private Set<AddressServiceViewModel> addresses = new HashSet<>();


    public SellerServiceViewModel() {
    }



    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ItemServiceViewModel> getStock() {
        return this.stock;
    }

    public void setStock(Set<ItemServiceViewModel> stock) {
        this.stock = stock;
    }

    public ContactInformationServiceViewModel getContactInformation() {
        return this.contactInformation;
    }

    public void setContactInformation(ContactInformationServiceViewModel contactInformation) {
        this.contactInformation = contactInformation;
    }

    public Set<AddressServiceViewModel> getAddresses() {
        return this.addresses;
    }

    public void setAddresses(Set<AddressServiceViewModel> addresses) {
        this.addresses = addresses;
    }
}
