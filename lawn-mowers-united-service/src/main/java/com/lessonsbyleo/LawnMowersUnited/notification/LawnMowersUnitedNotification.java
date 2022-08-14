package com.lessonsbyleo.LawnMowersUnited.notification;

import java.util.Objects;

public class LawnMowersUnitedNotification {
    private String email;
    private String description;

    public LawnMowersUnitedNotification() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "LawnMowersUnitedNotification{" +
                "email='" + email + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LawnMowersUnitedNotification that = (LawnMowersUnitedNotification) o;
        return Objects.equals(email, that.email) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, description);
    }
}
