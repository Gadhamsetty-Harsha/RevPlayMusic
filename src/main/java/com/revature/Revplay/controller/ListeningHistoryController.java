package com.revature.Revplay.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.revature.Revplay.dto.PlayRequestDTO;
import com.revature.Revplay.entity.ListeningHistory;
import com.revature.Revplay.service.ListeningHistoryService;

@RestController
@RequestMapping("/history")
public class ListeningHistoryController {

    private final ListeningHistoryService service;

    public ListeningHistoryController(ListeningHistoryService service) {
        this.service = service;
    }

    @PostMapping("/play")
    public ListeningHistory play(@RequestBody PlayRequestDTO request) {
        return service.recordPlay(request.getUserId(), request.getSongId());
    }

    @GetMapping("/user/{userId}/recent")
    public List<ListeningHistory> recent(@PathVariable Long userId) {
        return service.getRecentHistory(userId);
    }

    @GetMapping("/user/{userId}")
    public List<ListeningHistory> fullHistory(@PathVariable Long userId) {
        return service.getFullHistory(userId);
    }

    @GetMapping("/artist/{artistId}")
    public List<ListeningHistory> artistHistory(@PathVariable Long artistId) {
        return service.getArtistHistory(artistId);
    }
}