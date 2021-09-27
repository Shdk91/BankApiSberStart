package com.example.bankapisberstart.cache;

import org.springframework.stereotype.Component;

@Component
public interface RequestCache<T>{
    void addRequest(T t);
    boolean checkRequest(T t);
    void clear();
}
