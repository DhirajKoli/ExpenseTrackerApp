package com.example.Tracker.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountDto {
    private Long balance;

    private String name;
}
