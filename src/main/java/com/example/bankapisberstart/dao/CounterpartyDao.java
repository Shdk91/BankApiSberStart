package com.example.bankapisberstart.dao;

import com.example.bankapisberstart.entity.Counterparty;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterpartyDao {
    void saveCounterparty(Counterparty counterparty);
}
