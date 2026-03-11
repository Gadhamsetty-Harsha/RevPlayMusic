package com.revature.Revplay.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.revature.Revplay.entity.PodcastListeningHistory;
import com.revature.Revplay.repository.PodcastListeningHistoryRepository;
import com.revature.Revplay.service.PodcastListeningHistoryService;

@RestController
@RequestMapping("/podcast-history")
public class PodcastListeningHistoryController {

    private final PodcastListeningHistoryService service;
    private final PodcastListeningHistoryRepository repository;

    public PodcastListeningHistoryController(PodcastListeningHistoryService service,
                                              PodcastListeningHistoryRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @PostMapping
    public PodcastListeningHistory add(@RequestBody PodcastListeningHistory history) {
        return service.save(history);
    }

    @GetMapping
    public List<PodcastListeningHistory> getAll() {
        return service.findAll();
    }

    @GetMapping("/user/{userId}")
    public List<PodcastListeningHistory> getByUser(@PathVariable Long userId) {
        return repository.findByUser_UserId(userId);
    }

    @GetMapping("/artist/{artistId}")
    public List<PodcastListeningHistory> getByArtist(@PathVariable Long artistId) {
        return repository.findByArtistId(artistId);
    }
}