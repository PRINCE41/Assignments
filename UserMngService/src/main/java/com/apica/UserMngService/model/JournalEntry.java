package com.apica.UserMngService.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JournalEntry {
    private String userId;
    private String action;
    private Date timestamp;
}
