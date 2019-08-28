package com.dto;

import com.model.user.User;

public class UserDTO {

    private Long id;
    private int points;
    private String brojPasosa;
    private String city;
    private String phone;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private boolean enabled;

    public UserDTO(User user){
        this(user.getId(),user.getPoints(),user.getBrojPasosa(),user.getCity(),user.getPhone(),user.getUsername(),user.getFirstName(),user.getLastName(),user.getEmail(),user.isEnabled());
    }


    public UserDTO(Long id, int points, String brojPasosa, String city, String phone, String username,String firstName, String lastName, String email, boolean enabled) {
        this.id = id;
        this.points = points;
        this.brojPasosa = brojPasosa;
        this.city = city;
        this.phone = phone;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getBrojPasosa() {
        return brojPasosa;
    }

    public void setBrojPasosa(String brojPasosa) {
        this.brojPasosa = brojPasosa;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
