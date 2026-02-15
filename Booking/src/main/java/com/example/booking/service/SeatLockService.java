package com.example.booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class SeatLockService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String SEAT_LOCK_PREFIX = "LOCKED_SEAT_";

    public boolean lockSeats(Long showId, List<Long> seatIds, Long userId) {

        for (Long seatId : seatIds) {
            String key = SEAT_LOCK_PREFIX + showId + "_" + seatId;

            Boolean isLocked = redisTemplate.hasKey(key);
            if (Boolean.TRUE.equals(isLocked)) {
                return false;
            }
        }

        for (Long seatId : seatIds) {
            String key = SEAT_LOCK_PREFIX + showId + "_" + seatId;
            redisTemplate.opsForValue().set(key, userId, Duration.ofMinutes(5)); // TTL 5 min
        }

        return true;
    }

    public void releaseSeats(Long showId, List<Long> seatIds) {
        for (Long seatId : seatIds) {
            String key = SEAT_LOCK_PREFIX + showId + "_" + seatId;
            redisTemplate.delete(key);
        }
    }
}

