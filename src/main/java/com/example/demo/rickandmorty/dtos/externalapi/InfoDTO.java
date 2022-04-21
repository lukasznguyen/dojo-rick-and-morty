package com.example.demo.rickandmorty.dtos.externalapi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InfoDTO {
    private int count;
    private int pages;
    private String next;
    private String prev;
}
