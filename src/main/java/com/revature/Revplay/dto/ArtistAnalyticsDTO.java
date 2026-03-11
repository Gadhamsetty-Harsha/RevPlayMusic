package com.revature.Revplay.dto;

import java.util.List;

public class ArtistAnalyticsDTO {

    private Long artistId;
    private String artistName;
    private Long followerCount;
    private Long totalSongs;
    private Long totalPodcasts;
    private Long totalPlays;
    private Long totalLikes;
    private Long playsLast7Days;
    private Long playsLast30Days;
    private List<TopSongDTO> topSongs;
    private List<PodcastSummaryDTO> podcasts;

    public static class PodcastSummaryDTO {
        private Long podcastId;
        private String title;
        private String category;
        private String coverImage;
        private Long likeCount;
        public PodcastSummaryDTO(Long podcastId, String title, String category, String coverImage, Long likeCount) {
            this.podcastId = podcastId; this.title = title;
            this.category = category;  this.coverImage = coverImage;
            this.likeCount = likeCount;
        }
        public Long getPodcastId()   { return podcastId; }
        public String getTitle()     { return title; }
        public String getCategory()  { return category; }
        public String getCoverImage(){ return coverImage; }
        public Long getLikeCount()   { return likeCount; }
    }

    public ArtistAnalyticsDTO(Long artistId, String artistName, Long followerCount,
                               Long totalSongs, Long totalPodcasts, Long totalPlays, Long totalLikes,
                               Long playsLast7Days, Long playsLast30Days,
                               List<TopSongDTO> topSongs, List<PodcastSummaryDTO> podcasts) {
        this.artistId = artistId;
        this.artistName = artistName;
        this.followerCount = followerCount;
        this.totalSongs = totalSongs;
        this.totalPodcasts = totalPodcasts;
        this.totalPlays = totalPlays;
        this.totalLikes = totalLikes;
        this.playsLast7Days = playsLast7Days;
        this.playsLast30Days = playsLast30Days;
        this.topSongs = topSongs;
        this.podcasts = podcasts;
    }

    public Long getArtistId() { return artistId; }
    public String getArtistName() { return artistName; }
    public Long getFollowerCount() { return followerCount; }
    public Long getTotalSongs() { return totalSongs; }
    public Long getTotalPodcasts() { return totalPodcasts; }
    public Long getTotalPlays() { return totalPlays; }
    public Long getTotalLikes() { return totalLikes; }
    public Long getPlaysLast7Days() { return playsLast7Days; }
    public Long getPlaysLast30Days() { return playsLast30Days; }
    public List<TopSongDTO> getTopSongs() { return topSongs; }
    public List<PodcastSummaryDTO> getPodcasts() { return podcasts; }
}