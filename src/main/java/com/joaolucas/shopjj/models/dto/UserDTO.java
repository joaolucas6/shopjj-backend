package com.joaolucas.shopjj.models.dto;

import com.joaolucas.shopjj.models.entities.Address;
import com.joaolucas.shopjj.models.entities.Order;
import com.joaolucas.shopjj.models.entities.Review;
import com.joaolucas.shopjj.models.entities.User;
import com.joaolucas.shopjj.models.enums.Gender;

import java.util.List;

public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String cpf;
    private Gender gender;
    private Long shoppingCartId;
    private List<Long> ordersId;
    private List<Long> addressesId;
    private List<Long> reviewsId;

    public UserDTO(){

    }

    public UserDTO(User user){
        setId(user.getId());
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
        setEmail(user.getEmail());
        setCpf(user.getCpf());
        setGender(user.getGender());
        setShoppingCartId(user.getShoppingCart().getId());
        setOrdersId(user.getOrders().stream().map(Order::getId).toList());
        setAddressesId(user.getAddresses().stream().map(Address::getId).toList());
        setReviewsId(user.getReviews().stream().map(Review::getId).toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Long getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public List<Long> getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(List<Long> ordersId) {
        this.ordersId = ordersId;
    }

    public List<Long> getAddressesId() {
        return addressesId;
    }

    public void setAddressesId(List<Long> addressesId) {
        this.addressesId = addressesId;
    }

    public List<Long> getReviewsId() {
        return reviewsId;
    }

    public void setReviewsId(List<Long> reviewsId) {
        this.reviewsId = reviewsId;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", gender=" + gender +
                ", shoppingCartId=" + shoppingCartId +
                ", ordersId=" + ordersId +
                ", addressesId=" + addressesId +
                ", reviewsId=" + reviewsId +
                '}';
    }
}
