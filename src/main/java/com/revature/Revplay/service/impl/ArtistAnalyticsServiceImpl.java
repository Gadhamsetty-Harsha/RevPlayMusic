package com.revature.Revplay.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.revature.Revplay.dto.*;
import com.revature.Revplay.entity.*;
import com.revature.Revplay.repository.*;
import com.revature.Revplay.service.ArtistAnalyticsService;

@Service
public class ArtistAnalyticsServiceImpl implements ArtistAnalyticsService {

	private static final Logger logger = LogManager.getLogger(ArtistAnalyticsServiceImpl.class);
    private final ArtistRepository artistRepository;
    private final SongRepository songRepository;
    private final ListeningHistoryRepository listeningRepository;
    private final PodcastRepository podcastRepository;
    private final PodcastFavoriteRepository podcastFavoriteRepository;

    public ArtistAnalyticsServiceImpl(ArtistRepository artistRepository,
                                      SongRepository songRepository,
                                      ListeningHistoryRepository listeningRepository,
                                      PodcastRepository podcastRepository,
                                      PodcastFavoriteRepository podcastFavoriteRepository) {
        this.artistRepository = artistRepository;
        this.songRepository = songRepository;
        this.listeningRepository = listeningRepository;
        this.podcastRepository = podcastRepository;
        this.podcastFavoriteRepository = podcastFavoriteRepository;
    }

    @Override
    public ArtistAnalyticsDTO getAnalytics(Long artistId) {

    	logger.info("Fetching analytics for artistId={}", artistId);
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new RuntimeException("Artist not found"));

        Long totalSongs    = songRepository.countByArtist_ArtistId(artistId);
        Long totalPodcasts = podcastRepository.countByArtist_ArtistId(artistId);
        Long songLikes     = songRepository.getTotalLikes(artistId);
        Long podcastLikes  = podcastFavoriteRepository.countByArtistId(artistId);
        Long totalLikes    = (songLikes != null ? songLikes : 0L) + (podcastLikes != null ? podcastLikes : 0L);
        Long totalPlays    = listeningRepository.getTotalPlays(artistId);

        LocalDateTime now = LocalDateTime.now();
        Long plays7  = listeningRepository.getPlaysAfter(artistId, now.minusDays(7));
        Long plays30 = listeningRepository.getPlaysAfter(artistId, now.minusDays(30));

        List<TopSongDTO> topSongs = songRepository
                .findTop5ByArtist_ArtistIdOrderByPlayCountDesc(artistId)
                .stream()
                .map(s -> new TopSongDTO(
                        s.getSongId(), s.getTitle(), s.getPlayCount(),
                        s.getAlbum() != null ? s.getAlbum().getCoverImage() : null,
                        s.getLikeCount()))
                .collect(Collectors.toList());

        List<ArtistAnalyticsDTO.PodcastSummaryDTO> podcasts = podcastRepository
                .findByArtist_ArtistId(artistId)
                .stream()
                .map(p -> new ArtistAnalyticsDTO.PodcastSummaryDTO(
                        p.getPodcastId(), p.getTitle(), p.getCategory(), p.getCoverImage(),
                        podcastFavoriteRepository.countByPodcastId(p.getPodcastId())))
                .collect(Collectors.toList());

        return new ArtistAnalyticsDTO(
                artist.getArtistId(),
                artist.getArtistName(),
                artist.getFollowerCount(),
                totalSongs,
                totalPodcasts,
                totalPlays,
                totalLikes,
                plays7,
                plays30,
                topSongs,
                podcasts
        );
    }
}