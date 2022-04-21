package com.example.demo.rickandmorty.dtos.externalapi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EpisodeDTO {
    private String name;
    private String air_date;
    private String episode;
    private List<String> characters;
}
