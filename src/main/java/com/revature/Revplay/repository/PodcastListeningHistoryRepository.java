package com.revature.Revplay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.revature.Revplay.entity.PodcastListeningHistory;
import java.util.List;

public interface PodcastListeningHistoryRepository
        extends JpaRepository<PodcastListeningHistory, Long> {

    @Query("SELECT h FROM PodcastListeningHistory h JOIN FETCH h.episode e JOIN FETCH e.podcast p LEFT JOIN FETCH p.artist WHERE h.user.userId = :userId ORDER BY h.playedAt DESC")
    List<PodcastListeningHistory> findByUser_UserId(@Param("userId") Long userId);

    @Query("SELECT h FROM PodcastListeningHistory h JOIN FETCH h.episode e JOIN FETCH e.podcast p LEFT JOIN FETCH p.artist LEFT JOIN FETCH h.user WHERE p.artist.artistId = :artistId ORDER BY h.playedAt DESC")
    List<PodcastListeningHistory> findByArtistId(@Param("artistId") Long artistId);
}