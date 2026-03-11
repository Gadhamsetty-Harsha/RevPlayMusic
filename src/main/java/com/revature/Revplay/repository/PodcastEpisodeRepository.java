package com.revature.Revplay.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.revature.Revplay.entity.PodcastEpisode;

public interface PodcastEpisodeRepository extends JpaRepository<PodcastEpisode, Long> {

    @Query("SELECT e FROM PodcastEpisode e JOIN FETCH e.podcast WHERE e.podcast.artist.artistId = :artistId ORDER BY e.createdAt DESC")
    List<PodcastEpisode> findByPodcast_Artist_ArtistIdOrderByCreatedAtDesc(@Param("artistId") Long artistId);

    List<PodcastEpisode> findByPodcast_PodcastIdOrderByReleaseDateAscEpisodeIdAsc(Long podcastId);
}