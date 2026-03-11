package com.revature.Revplay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.revature.Revplay.entity.PodcastFavorite;
import com.revature.Revplay.entity.compositekey.PodcastFavoriteId;
import java.util.List;

public interface PodcastFavoriteRepository
        extends JpaRepository<PodcastFavorite, PodcastFavoriteId> {

    List<PodcastFavorite> findByUser_UserId(Long userId);

    boolean existsByUser_UserIdAndEpisode_EpisodeId(Long userId, Long episodeId);

    @Transactional
    void deleteByUser_UserIdAndEpisode_EpisodeId(Long userId, Long episodeId);

    @Query("SELECT COUNT(pf) FROM PodcastFavorite pf WHERE pf.episode.podcast.podcastId = :podcastId")
    Long countByPodcastId(@Param("podcastId") Long podcastId);

    @Query("SELECT COUNT(pf) FROM PodcastFavorite pf WHERE pf.episode.podcast.artist.artistId = :artistId")
    Long countByArtistId(@Param("artistId") Long artistId);
}