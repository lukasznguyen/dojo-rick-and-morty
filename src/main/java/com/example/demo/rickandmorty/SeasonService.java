package com.example.demo.rickandmorty;

import com.example.demo.rickandmorty.dtos.externalapi.CharacterDTO;
import com.example.demo.rickandmorty.dtos.externalapi.EpisodeDTO;
import com.example.demo.rickandmorty.dtos.externalapi.SeasonDTO;
import com.example.demo.rickandmorty.dtos.res.CharacterResDTO;
import com.example.demo.rickandmorty.dtos.res.EpisodeResDTO;
import com.example.demo.rickandmorty.dtos.res.SeasonResDTO;
import com.example.demo.rickandmorty.exceptions.InvalidSeasonNumberException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class SeasonService {

    private static final String EXTERNAL_URL = "https://rickandmortyapi.com/api";
    private final RestTemplate restTemplate;

    public List<SeasonResDTO> getNumberOfEpisodesForEachSeason() {
        List<SeasonResDTO> result = new ArrayList<>();
        Map<Integer, Integer> episodesPerSeason = new HashMap<>();

        SeasonDTO data = restTemplate.getForObject(EXTERNAL_URL + "/episode", SeasonDTO.class);
        while (data.getInfo().getNext() != null) {
            calculateForSinglePage(episodesPerSeason, data);
            data = restTemplate.getForObject(data.getInfo().getNext(), SeasonDTO.class);
        }
        calculateForSinglePage(episodesPerSeason, data);
        episodesPerSeason.forEach((seasonNumber, count) -> {
            result.add(SeasonResDTO.builder()
                    .seasonNumber(seasonNumber)
                    .episodesNumber(count)
                    .build());
        });
        return result;
    }

    private void calculateForSinglePage(Map<Integer, Integer> episodesPerSeason, SeasonDTO data) {
        for (EpisodeDTO episode : data.getResults()) {
            Integer seasonNumber = Integer.parseInt(String.valueOf(episode.getEpisode().charAt(2)));
            if (episodesPerSeason.containsKey(seasonNumber)) {
                episodesPerSeason.put(seasonNumber, episodesPerSeason.get(seasonNumber) + 1);
            } else {
                episodesPerSeason.put(seasonNumber, 1);
            }
        }
    }

    public List<EpisodeResDTO> getSeasonDetails(int seasonNumber) throws InvalidSeasonNumberException {
        if (seasonNumber > 5) {
            throw new InvalidSeasonNumberException("Invalid season number");
        }

        List<EpisodeResDTO> result = new ArrayList<>();
        SeasonDTO data = restTemplate.getForObject(EXTERNAL_URL + "/episode?episode=S0" + seasonNumber, SeasonDTO.class);

        do {
            for (EpisodeDTO episode : data.getResults()) {
                List<CharacterResDTO> charactersRes = new ArrayList<>();

                for (String characterUrl : episode.getCharacters()) {
                    CharacterDTO character = restTemplate.getForObject(characterUrl, CharacterDTO.class);
                    CharacterResDTO characterResDTO = CharacterResDTO.builder()
                            .name(character.getName())
                            .image(character.getImage())
                            .build();
                    charactersRes.add(characterResDTO);
                }

                EpisodeResDTO episodeRes = EpisodeResDTO.builder()
                        .episode(episode.getEpisode())
                        .name(episode.getName())
                        .air_date(episode.getAir_date())
                        .characters(charactersRes)
                        .build();

                result.add(episodeRes);
            }
        } while (data.getInfo().getNext() != null);

        return result;
    }
}
