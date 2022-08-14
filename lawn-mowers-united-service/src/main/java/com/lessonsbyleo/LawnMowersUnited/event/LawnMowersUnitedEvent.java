package com.lessonsbyleo.LawnMowersUnited.event;

import java.util.Objects;

public class LawnMowersUnitedEvent {
    private String type;
    private String email;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LawnMowersUnitedEvent that = (LawnMowersUnitedEvent) o;
        return Objects.equals(type, that.type) && Objects.equals(email, that.email) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, email, description);
    }

    @Override
    public String toString() {
        return "LawnMowersUnitedEvent{" +
                "type='" + type + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
