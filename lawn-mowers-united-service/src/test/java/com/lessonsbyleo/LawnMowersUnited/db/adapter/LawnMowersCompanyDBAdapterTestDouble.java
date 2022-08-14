package com.lessonsbyleo.LawnMowersUnited.db.adapter;

import com.lessonsbyleo.LawnMowersUnited.bdd.company.LawnMowerCompanyTestFactory;
import com.lessonsbyleo.LawnMowersUnited.data.LawnMowerCompany;
import com.lessonsbyleo.LawnMowersUnited.db.exception.InvalidLawnMowerCompany;
import com.lessonsbyleo.LawnMowersUnited.service.LawnMowersUnitedServiceInf;
import org.mockito.Mockito;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Primary
@Component
public class LawnMowersCompanyDBAdapterTestDouble implements LawnMowersCompanyDbAdapterInf {
    private final LawnMowersCompanyDbAdapter lawnMowersCompanyDbAdapter;

    private final LawnMowersCompanyDbAdapterInf mock;

    public LawnMowersCompanyDBAdapterTestDouble(LawnMowersCompanyDbAdapter lawnMowersCompanyDbAdapter) {
        this.lawnMowersCompanyDbAdapter = lawnMowersCompanyDbAdapter;
        this.mock = Mockito.mock(LawnMowersCompanyDbAdapterInf.class);
    }

    @Override
    public void delete(LawnMowerCompany lawnMowerCompany) {
        lawnMowersCompanyDbAdapter.delete(lawnMowerCompany);
    }

    @Override
    public boolean doesAccountExist(LawnMowerCompany lawnMowerCompany) {
        boolean response = lawnMowersCompanyDbAdapter.doesAccountExist(lawnMowerCompany);
        when(mock.doesAccountExist(any())).thenReturn(response);
        return mock.doesAccountExist(lawnMowerCompany);
    }

    public void verifyDoesAccountExistWasCalled(int numOfTimes){
        verify(mock, times(numOfTimes)).doesAccountExist(any());
    }

    @Override
    public void createAccount(LawnMowerCompany lawnMowerCompany) {
        lawnMowersCompanyDbAdapter.createAccount(lawnMowerCompany);
    }

    @Override
    public List<LawnMowerCompany> getLawnMowerAccounts() {
        return lawnMowersCompanyDbAdapter.getLawnMowerAccounts();
    }

    @Override
    public void deleteAll() {
        lawnMowersCompanyDbAdapter.deleteAll();
    }

    @Override
    public LawnMowerCompany getAccount(String email) throws InvalidLawnMowerCompany {
        return lawnMowersCompanyDbAdapter.getAccount(email);
    }

    public void setTestDataEntities(){
        List<LawnMowerCompany> lawnMowerCompanyList = LawnMowerCompanyTestFactory.create(3);
        lawnMowerCompanyList.forEach(this::createAccount);
    }

    public void resetMock(){
        reset(mock);
    }
}
