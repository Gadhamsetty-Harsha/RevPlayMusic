package com.revature.Revplay.service;

import java.util.List;
import com.revature.Revplay.entity.PodcastFavorite;

public interface PodcastFavoriteService {
    PodcastFavorite save(PodcastFavorite favorite);
    List<PodcastFavorite> findAll();
    boolean toggle(Long userId, Long episodeId);
    boolean isFavorited(Long userId, Long episodeId);
    List<PodcastFavorite> findByUserId(Long userId);
}