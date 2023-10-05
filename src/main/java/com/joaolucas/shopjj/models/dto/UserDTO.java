package com.joaolucas.shopjj.models.dto;

import com.joaolucas.shopjj.models.entities.Address;
import com.joaolucas.shopjj.models.entities.Order;
import com.joaolucas.shopjj.models.entities.Review;
import com.joaolucas.shopjj.models.entities.User;
import com.joaolucas.shopjj.models.enums.Gender;
import com.joaolucas.shopjj.models.enums.Role;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;
import java.util.Objects;

public class UserDTO extends RepresentationModel<UserDTO> {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String cpf;
    private Gender gender;

    private Role role;

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
        if(user.getShoppingCart() != null) setShoppingCartId(user.getShoppingCart().getId());
        setRole(user.getRole());
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
                ", role=" + role +
                ", shoppingCartId=" + shoppingCartId +
                ", ordersId=" + ordersId +
                ", addressesId=" + addressesId +
                ", reviewsId=" + reviewsId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id) && Objects.equals(firstName, userDTO.firstName) && Objects.equals(lastName, userDTO.lastName) && Objects.equals(email, userDTO.email) && Objects.equals(cpf, userDTO.cpf) && gender == userDTO.gender && role == userDTO.role && Objects.equals(shoppingCartId, userDTO.shoppingCartId) && Objects.equals(ordersId, userDTO.ordersId) && Objects.equals(addressesId, userDTO.addressesId) && Objects.equals(reviewsId, userDTO.reviewsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, firstName, lastName, email, cpf, gender, role, shoppingCartId, ordersId, addressesId, reviewsId);
    }
}
