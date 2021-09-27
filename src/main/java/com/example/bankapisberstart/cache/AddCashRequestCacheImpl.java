package com.example.bankapisberstart.cache;

import com.example.bankapisberstart.dto.input_dto.AddCashDto;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AddCashRequestCacheImpl implements RequestCache<AddCashDto> {

    private Map<AddCashDto, LocalDateTime> map = new ConcurrentHashMap<>();

    @Override
    public void addRequest(AddCashDto addCashDto) {
        map.put(addCashDto, LocalDateTime.now());
    }

    @Override
    public boolean checkRequest(AddCashDto addCashDto) {
        return map.containsKey(addCashDto);
    }

    @Override
    @Scheduled(fixedRate = 60000)
    public void clear() {
        if (!(map.isEmpty())) {
            map.forEach(((addCashDto, localDateTime) -> {
                LocalDateTime dateTime = LocalDateTime.now().minusSeconds(60);
                if (localDateTime.compareTo(dateTime) > 0) {
                    map.remove(addCashDto);
                }
            }));
        }
    }
}
