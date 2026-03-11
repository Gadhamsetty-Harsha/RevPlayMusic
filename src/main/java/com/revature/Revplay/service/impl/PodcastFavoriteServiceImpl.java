package com.revature.Revplay.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.revature.Revplay.entity.PodcastEpisode;
import com.revature.Revplay.entity.PodcastFavorite;
import com.revature.Revplay.entity.User;
import com.revature.Revplay.entity.compositekey.PodcastFavoriteId;
import com.revature.Revplay.repository.PodcastEpisodeRepository;
import com.revature.Revplay.repository.PodcastFavoriteRepository;
import com.revature.Revplay.repository.UserRepository;
import com.revature.Revplay.service.PodcastFavoriteService;

@Service
public class PodcastFavoriteServiceImpl implements PodcastFavoriteService {

    private final PodcastFavoriteRepository repository;
    private final UserRepository userRepository;
    private final PodcastEpisodeRepository episodeRepository;

    public PodcastFavoriteServiceImpl(PodcastFavoriteRepository repository,
                                      UserRepository userRepository,
                                      PodcastEpisodeRepository episodeRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.episodeRepository = episodeRepository;
    }

    @Override
    public PodcastFavorite save(PodcastFavorite favorite) {
        return repository.save(favorite);
    }

    @Override
    public List<PodcastFavorite> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean toggle(Long userId, Long episodeId) {
        if (repository.existsByUser_UserIdAndEpisode_EpisodeId(userId, episodeId)) {
            repository.deleteByUser_UserIdAndEpisode_EpisodeId(userId, episodeId);
            return false;
        } else {
            User user = userRepository.findById(userId).orElseThrow();
            PodcastEpisode episode = episodeRepository.findById(episodeId).orElseThrow();
            PodcastFavorite fav = new PodcastFavorite();
            fav.setId(new PodcastFavoriteId(userId, episodeId));
            fav.setUser(user);
            fav.setEpisode(episode);
            repository.save(fav);
            return true;
        }
    }

    @Override
    public boolean isFavorited(Long userId, Long episodeId) {
        return repository.existsByUser_UserIdAndEpisode_EpisodeId(userId, episodeId);
    }

    @Override
    public List<PodcastFavorite> findByUserId(Long userId) {
        return repository.findByUser_UserId(userId);
    }
}