package com.example.demo.rickandmorty;

import com.example.demo.rickandmorty.dtos.res.EpisodeResDTO;
import com.example.demo.rickandmorty.dtos.res.SeasonResDTO;
import com.example.demo.rickandmorty.exceptions.InvalidSeasonNumberException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/seasons")
@AllArgsConstructor
@CrossOrigin(origins = "https://symphonious-kleicha-1c71d3.netlify.app/")
public class SeasonController {

    private final SeasonService seasonService;

    @GetMapping
    public ResponseEntity<List<SeasonResDTO>> getNumberOfEpisodesForEachSeason() {
        List<SeasonResDTO> result = seasonService.getNumberOfEpisodesForEachSeason();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{seasonNumber}")
    public ResponseEntity<List<EpisodeResDTO>> getSeasonDetails(@PathVariable int seasonNumber) {
        ResponseEntity<List<EpisodeResDTO>> result;
        try {
            result = ResponseEntity.ok(seasonService.getSeasonDetails(seasonNumber));
        } catch (InvalidSeasonNumberException e) {
            result = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return result;
    }
}
