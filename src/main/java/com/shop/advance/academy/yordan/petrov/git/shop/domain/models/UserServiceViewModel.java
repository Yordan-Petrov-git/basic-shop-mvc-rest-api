package com.shop.advance.academy.yordan.petrov.git.shop.domain.models;

import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Address;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Card;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.ContactInformation;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.enums.UserType;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class UserServiceViewModel {

    private String username;
    private String password;
    private UserType userType;
    private Instant dateRegistered;
    private Date dateOfBirth;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private Set<Address> addresses = new HashSet<>();
    private Set<ContactInformation> contactInformation = new HashSet<>();

    public UserServiceViewModel(String username, String password, UserType userType, Instant dateRegistered, Date dateOfBirth, String firstName, String lastName, String phone, String email, Set<Address> addresses, Set<ContactInformation> contactInformation) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.dateRegistered = dateRegistered;
        this.dateOfBirth = dateOfBirth;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.addresses = addresses;
        this.contactInformation = contactInformation;
    }

    public UserServiceViewModel() {
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return this.userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Instant getDateRegistered() {
        return this.dateRegistered;
    }

    public void setDateRegistered(Instant dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Address> getAddresses() {
        return this.addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<ContactInformation> getContactInformation() {
        return this.contactInformation;
    }

    public void setContactInformation(Set<ContactInformation> contactInformation) {
        this.contactInformation = contactInformation;
    }
}
