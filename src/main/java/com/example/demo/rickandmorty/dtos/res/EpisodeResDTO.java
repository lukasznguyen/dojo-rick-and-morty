package com.example.demo.rickandmorty.dtos.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EpisodeResDTO {
    private String episode;
    private String name;
    private String air_date;
    private List<CharacterResDTO> characters;
}
