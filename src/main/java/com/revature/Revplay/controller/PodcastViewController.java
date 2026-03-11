package com.revature.Revplay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.revature.Revplay.entity.Podcast;
import com.revature.Revplay.entity.PodcastEpisode;
import com.revature.Revplay.security.JwtUtil;
import com.revature.Revplay.service.PodcastFavoriteService;
import com.revature.Revplay.service.PodcastService;
import com.revature.Revplay.repository.PodcastEpisodeRepository;
import io.jsonwebtoken.Claims;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class PodcastViewController {

    private final PodcastService podcastService;
    private final PodcastEpisodeRepository episodeRepository;
    private final PodcastFavoriteService podcastFavoriteService;
    private final JwtUtil jwtUtil;

    public PodcastViewController(PodcastService podcastService,
                                  PodcastEpisodeRepository episodeRepository,
                                  PodcastFavoriteService podcastFavoriteService,
                                  JwtUtil jwtUtil) {
        this.podcastService = podcastService;
        this.episodeRepository = episodeRepository;
        this.podcastFavoriteService = podcastFavoriteService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/view/podcast/{id}")
    public String podcastDetail(@PathVariable Long id,
                                @CookieValue(name = "jwt", required = false) String token,
                                Model model) {
        Podcast podcast = podcastService.findById(id);
        List<PodcastEpisode> episodes = episodeRepository
                .findByPodcast_PodcastIdOrderByReleaseDateAscEpisodeIdAsc(id);
        model.addAttribute("podcast", podcast);
        model.addAttribute("episodes", episodes);

        Set<Long> favoritedEpisodeIds = new HashSet<>();
        boolean loggedIn = false;
        if (token != null && jwtUtil.validateToken(token)) {
            loggedIn = true;
            Claims claims = jwtUtil.extractClaims(token);
            Long userId = claims.get("userId", Long.class);
            podcastFavoriteService.findByUserId(userId).forEach(fav ->
                favoritedEpisodeIds.add(fav.getEpisode().getEpisodeId())
            );
        }
        model.addAttribute("favoritedEpisodeIds", favoritedEpisodeIds);
        model.addAttribute("loggedIn", loggedIn);

        return "podcast-detail";
    }
}