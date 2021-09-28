package com.example.bankapisberstart.cache;

import com.example.bankapisberstart.dto.inputdto.CreateCardDto;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CreateCardRequestCacheImpl implements RequestCache<CreateCardDto> {

    private Map<CreateCardDto, LocalDateTime> map = new ConcurrentHashMap<>();

    @Override
    public void addRequest(CreateCardDto createCardDto) {
        map.put(createCardDto, LocalDateTime.now());
    }

    @Override
    public boolean checkRequest(CreateCardDto createCardDto) {
        return map.containsKey(createCardDto);
    }

    @Override
    @Scheduled(fixedRate = 600000)
    public void clear() {
        if (!(map.isEmpty())) {
            map.forEach(((cardDto, localDateTime) -> {
                LocalDateTime dateTime = LocalDateTime.now().minusSeconds(6000);
                if (localDateTime.compareTo(dateTime) > 0) {
                    map.remove(cardDto);
                }
            }));
        }
    }
}
