package com.lessonsbyleo.LawnMowersUnited.data;

import java.util.Objects;

public class ServiceRequest {
    private Customer customer;
    private LawnMowerCompany lawnMowerCompany;

    public ServiceRequest() {
    }

    public ServiceRequest(Customer customer, LawnMowerCompany lawnMowerCompany) {
        this.customer = customer;
        this.lawnMowerCompany = lawnMowerCompany;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LawnMowerCompany getLawnMowerCompany() {
        return lawnMowerCompany;
    }

    public void setLawnMowerCompany(LawnMowerCompany lawnMowerCompany) {
        this.lawnMowerCompany = lawnMowerCompany;
    }

    @Override
    public String toString() {
        return "ServiceRequest{" +
                "customer=" + customer +
                ", lawnMowerCompany=" + lawnMowerCompany +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceRequest that = (ServiceRequest) o;
        return Objects.equals(customer, that.customer) && Objects.equals(lawnMowerCompany, that.lawnMowerCompany);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, lawnMowerCompany);
    }
}
