package com.joaolucas.shopjj.models.dto;

import com.joaolucas.shopjj.models.entities.Address;
import org.springframework.hateoas.RepresentationModel;

public class AddressDTO extends RepresentationModel<AddressDTO> {
    private Long id;
    private String state;
    private String city;
    private String street;
    private Integer number;
    private String complement;
    private String cep;
    private Long residentId;

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
        setResidentId(address.getResident().getId());
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
                '}';
    }
}
