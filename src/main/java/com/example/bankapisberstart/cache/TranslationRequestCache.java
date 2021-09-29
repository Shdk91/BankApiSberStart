package com.example.bankapisberstart.cache;

import com.example.bankapisberstart.dto.inputdto.TranslationDto;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TranslationRequestCache implements RequestCache<TranslationDto>{
    Map<TranslationDto, LocalDateTime> map = new ConcurrentHashMap<>();

    @Override
    public void addRequest(TranslationDto translationDto) {
        map.put(translationDto, LocalDateTime.now());
    }

    @Override
    public boolean checkRequest(TranslationDto translationDto) {
        return map.containsKey(translationDto);
    }

    @Override
    @Scheduled(fixedRate = 60000)
    public void clear() {
        if (!(map.isEmpty())) {
            map.forEach(((translationDto, localDateTime) -> {
                LocalDateTime dateTime = LocalDateTime.now().minusSeconds(60);
                if (localDateTime.compareTo(dateTime) > 0) {
                    map.remove(translationDto);
                }
            }));
        }
    }
}
