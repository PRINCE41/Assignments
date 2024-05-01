package com.apica.JournalingService.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("journal")
public class JournalEntry {
    @Id
    private String userId;
    private String action;
    private Date timestamp;
}
