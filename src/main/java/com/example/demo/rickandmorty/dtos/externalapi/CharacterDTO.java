package com.example.demo.rickandmorty.dtos.externalapi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CharacterDTO {
    private String name;
    private String image;
}
