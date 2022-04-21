package com.example.demo.rickandmorty.dtos.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeasonResDTO {
    private int seasonNumber;
    private int episodesNumber;
}
