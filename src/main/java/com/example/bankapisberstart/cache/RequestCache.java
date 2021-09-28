package com.example.bankapisberstart.cache;

import org.springframework.stereotype.Component;

/**
 * Данный интерфейс предназначен для реализации иденпотентности.
 * На каждый метод каждый метод АПИ, который нуждается в реализации механизма.
 * Создается наследник с параметром ДТО и одним полем Map <LocalDAteTime, DTO>
 * В случае успешной обработки контроллером в Map кешируется ДТО.
 * При обработке запросов проверяется наличие данного запроса в кэше.
 * В методе Clear реализуется механизм очистки кэша.
 * @param <T>
 */
@Component
public interface RequestCache<T> {
    void addRequest(T t);

    boolean checkRequest(T t);

    void clear();
}
