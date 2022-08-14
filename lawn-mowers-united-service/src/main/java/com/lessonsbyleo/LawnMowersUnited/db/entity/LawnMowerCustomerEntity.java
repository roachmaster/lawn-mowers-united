package com.lessonsbyleo.LawnMowersUnited.db.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "LAWN_MOWER_CUSTOMER_DATA")
public class LawnMowerCustomerEntity implements Serializable {

    @EmbeddedId
    private LawnMowerCustomerKey lawnMowerCustomerKey;

    public LawnMowerCustomerEntity() {
    }

    public LawnMowerCustomerEntity(LawnMowerCustomerKey lawnMowerCustomerKey) {
        this.lawnMowerCustomerKey = lawnMowerCustomerKey;
    }

    public LawnMowerCustomerKey getLawnMowerCustomerKey() {
        return lawnMowerCustomerKey;
    }

    public void setLawnMowerCustomerKey(LawnMowerCustomerKey lawnMowerCustomerKey) {
        this.lawnMowerCustomerKey = lawnMowerCustomerKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LawnMowerCustomerEntity that = (LawnMowerCustomerEntity) o;
        return Objects.equals(lawnMowerCustomerKey, that.lawnMowerCustomerKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lawnMowerCustomerKey);
    }

    @Override
    public String toString() {
        return "LawnMowerCustomerEntity{" +
                "lawnMowerCustomerKey=" + lawnMowerCustomerKey +
                '}';
    }
}
