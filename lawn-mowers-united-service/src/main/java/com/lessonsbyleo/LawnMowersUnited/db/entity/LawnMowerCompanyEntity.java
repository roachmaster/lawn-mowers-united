package com.lessonsbyleo.LawnMowersUnited.db.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "LAWN_MOWER_COMPANY_DATA")
public class LawnMowerCompanyEntity implements Serializable {
    @EmbeddedId
    private LawnMowerCompanyKey lawnMowerCompanyKey;

    public LawnMowerCompanyEntity(LawnMowerCompanyKey lawnMowerCompanyKey) {
        this.lawnMowerCompanyKey = lawnMowerCompanyKey;
    }

    public LawnMowerCompanyEntity() {

    }

    public LawnMowerCompanyKey getLawnMowerCompanyKey() {
        return lawnMowerCompanyKey;
    }

    public void setLawnMowerCompanyKey(LawnMowerCompanyKey lawnMowerCompanyKey) {
        this.lawnMowerCompanyKey = lawnMowerCompanyKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LawnMowerCompanyEntity that = (LawnMowerCompanyEntity) o;
        return Objects.equals(lawnMowerCompanyKey, that.lawnMowerCompanyKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lawnMowerCompanyKey);
    }

    @Override
    public String toString() {
        return "LawnMowerCompanyEntity{" +
                "lawnMowerCompany=" + lawnMowerCompanyKey +
                '}';
    }
}
