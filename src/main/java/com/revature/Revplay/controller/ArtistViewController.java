package com.revature.Revplay.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.revature.Revplay.entity.Artist;
import com.revature.Revplay.entity.Song;
import com.revature.Revplay.entity.User;
import com.revature.Revplay.repository.SongRepository;
import com.revature.Revplay.repository.UserRepository;
import com.revature.Revplay.security.JwtUtil;
import com.revature.Revplay.service.ArtistService;
import io.jsonwebtoken.Claims;

@Controller
public class ArtistViewController {

    private final ArtistService artistService;
    private final SongRepository songRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public ArtistViewController(ArtistService artistService, SongRepository songRepository,
                                 UserRepository userRepository, JwtUtil jwtUtil) {
        this.artistService  = artistService;
        this.songRepository = songRepository;
        this.userRepository = userRepository;
        this.jwtUtil        = jwtUtil;
    }

    @GetMapping("/view/artist/{id}")
    public String artistDetail(@PathVariable Long id,
                               @CookieValue(name = "jwt", required = false) String token,
                               Model model) {
        Artist artist = artistService.findById(id);
        List<Song> songs = songRepository.findByArtist_ArtistId(id);
        model.addAttribute("artist", artist);
        model.addAttribute("songs", songs);

        if (token != null && jwtUtil.validateToken(token)) {
            Claims claims = jwtUtil.extractClaims(token);
            Long userId = claims.get("userId", Long.class);
            User user = userRepository.findById(userId).orElse(null);
            if (user != null) {
                model.addAttribute("currentUser", user);
            }
        }

        return "artist-detail";
    }
}