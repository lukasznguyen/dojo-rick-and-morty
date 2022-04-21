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
public class SeasonDTO {
    private InfoDTO info;
    private List<EpisodeDTO> results;
}
