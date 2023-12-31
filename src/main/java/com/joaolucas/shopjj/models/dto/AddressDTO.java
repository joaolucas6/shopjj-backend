package com.joaolucas.shopjj.models.dto;

import com.joaolucas.shopjj.models.entities.Address;
import com.joaolucas.shopjj.models.entities.Order;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;
import java.util.Objects;

public class AddressDTO extends RepresentationModel<AddressDTO> {
    private Long id;
    private String state;
    private String city;
    private String street;
    private Integer number;
    private String complement;
    private String cep;
    private Long residentId;

    private List<Long> ordersId;
    public AddressDTO(){

    }

    public AddressDTO(Address address){
        setId(address.getId());
        setState(address.getState());
        setCity(address.getCity());
        setStreet(address.getStreet());
        setNumber(address.getNumber());
        setComplement(address.getComplement());
        setCep(address.getCep());
        if(address.getResident() != null) setResidentId(address.getResident().getId());
        setOrdersId(address.getOrders().stream().map(Order::getId).toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Long getResidentId() {
        return residentId;
    }

    public void setResidentId(Long residentId) {
        this.residentId = residentId;
    }

    public List<Long> getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(List<Long> ordersId) {
        this.ordersId = ordersId;
    }

    @Override
    public String toString() {
        return "AddressDTO{" +
                "id=" + id +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", number=" + number +
                ", complement='" + complement + '\'' +
                ", cep='" + cep + '\'' +
                ", residentId=" + residentId +
                ", ordersId=" + ordersId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressDTO that = (AddressDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(state, that.state) && Objects.equals(city, that.city) && Objects.equals(street, that.street) && Objects.equals(number, that.number) && Objects.equals(complement, that.complement) && Objects.equals(cep, that.cep) && Objects.equals(residentId, that.residentId) && Objects.equals(ordersId, that.ordersId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, state, city, street, number, complement, cep, residentId, ordersId);
    }
}
