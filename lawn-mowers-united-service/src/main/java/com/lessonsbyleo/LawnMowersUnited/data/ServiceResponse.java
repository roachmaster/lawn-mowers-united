package com.lessonsbyleo.LawnMowersUnited.data;

import java.util.Objects;

public class ServiceResponse {
    private ServiceRequestState serviceRequestState;

    public ServiceResponse() {
    }

    public ServiceResponse(ServiceRequestState serviceRequestState) {
        this.serviceRequestState = serviceRequestState;
    }

    public ServiceRequestState getServiceRequestState() {
        return serviceRequestState;
    }

    public void setServiceRequestState(ServiceRequestState serviceRequestState) {
        this.serviceRequestState = serviceRequestState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceResponse that = (ServiceResponse) o;
        return serviceRequestState == that.serviceRequestState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceRequestState);
    }

    @Override
    public String toString() {
        return "LawnMowerUnitedServiceResponse{" +
                "serviceRequestState=" + serviceRequestState +
                '}';
    }
}
