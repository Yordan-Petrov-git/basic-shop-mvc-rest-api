package com.shop.advance.academy.yordan.petrov.git.shop.domain.models;

import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Address;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.ContactInformation;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Item;

import java.util.HashSet;
import java.util.Set;

public class SellerServiceModel {

    private Long id;
    private String name;
    private Set<ItemServiceModel> stock = new HashSet<>();
    private ContactInformationServiceModel contactInformation;
    private Set<AddressServiceModel> addresses = new HashSet<>();


    public SellerServiceModel() {
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

    public Set<ItemServiceModel> getStock() {
        return this.stock;
    }

    public void setStock(Set<ItemServiceModel> stock) {
        this.stock = stock;
    }

    public ContactInformationServiceModel getContactInformation() {
        return this.contactInformation;
    }

    public void setContactInformation(ContactInformationServiceModel contactInformation) {
        this.contactInformation = contactInformation;
    }

    public Set<AddressServiceModel> getAddresses() {
        return this.addresses;
    }

    public void setAddresses(Set<AddressServiceModel> addresses) {
        this.addresses = addresses;
    }
}
