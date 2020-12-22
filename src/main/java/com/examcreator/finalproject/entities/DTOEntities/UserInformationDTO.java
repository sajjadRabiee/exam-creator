package com.examcreator.finalproject.entities.DTOEntities;

import com.examcreator.finalproject.entities.classEntities.Users.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class UserInformationDTO {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String country;
    private String city;
    private String restAddress;
    private String zipCode;
    private String roleName;

    public UserInformationDTO() {
    }

    public UserInformationDTO(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.firstName = user.getName().getFirstName();
        this.lastName = user.getName().getLastName();
        this.password = user.getPassword();
        this.country = user.getAddress().getCountry();
        this.city = user.getAddress().getCity();
        this.restAddress = user.getAddress().getRestAddress();
        this.zipCode = user.getAddress().getZipCode();
        this.roleName = user.getRole().name();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRestAddress() {
        return restAddress;
    }

    public void setRestAddress(String restAddress) {
        this.restAddress = restAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
