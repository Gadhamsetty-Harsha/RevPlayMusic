package com.revature.Revplay.controller;

import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.revature.Revplay.entity.PodcastFavorite;
import com.revature.Revplay.security.JwtUtil;
import com.revature.Revplay.service.PodcastFavoriteService;
import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/podcast-favorites")
public class PodcastFavoriteController {

    private final PodcastFavoriteService service;
    private final JwtUtil jwtUtil;

    public PodcastFavoriteController(PodcastFavoriteService service, JwtUtil jwtUtil) {
        this.service = service;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/toggle/{episodeId}")
    public ResponseEntity<Map<String, Object>> toggle(
            @PathVariable Long episodeId,
            @CookieValue(name = "jwt", required = false) String token) {

        if (token == null || !jwtUtil.validateToken(token))
            return ResponseEntity.status(401).build();

        Claims claims = jwtUtil.extractClaims(token);
        Long userId = claims.get("userId", Long.class);
        boolean nowFavorited = service.toggle(userId, episodeId);
        return ResponseEntity.ok(Map.of("favorited", nowFavorited));
    }

    @GetMapping("/check/{episodeId}")
    public ResponseEntity<Map<String, Object>> check(
            @PathVariable Long episodeId,
            @CookieValue(name = "jwt", required = false) String token) {

        if (token == null || !jwtUtil.validateToken(token))
            return ResponseEntity.ok(Map.of("favorited", false));

        Claims claims = jwtUtil.extractClaims(token);
        Long userId = claims.get("userId", Long.class);
        return ResponseEntity.ok(Map.of("favorited", service.isFavorited(userId, episodeId)));
    }

    @GetMapping("/mine")
    public ResponseEntity<List<PodcastFavorite>> mine(
            @CookieValue(name = "jwt", required = false) String token) {

        if (token == null || !jwtUtil.validateToken(token))
            return ResponseEntity.status(401).build();

        Claims claims = jwtUtil.extractClaims(token);
        Long userId = claims.get("userId", Long.class);
        return ResponseEntity.ok(service.findByUserId(userId));
    }

    @PostMapping
    public PodcastFavorite add(@RequestBody PodcastFavorite favorite) {
        return service.save(favorite);
    }

    @GetMapping
    public List<PodcastFavorite> getAll() {
        return service.findAll();
    }
}