package com.revature.Revplay.dto;

public class TopSongDTO {

    private Long songId;
    private String title;
    private Long playCount;
    private String coverImage;
    private Long likeCount;

    public TopSongDTO(Long songId, String title, Long playCount) {
        this.songId = songId;
        this.title = title;
        this.playCount = playCount;
    }

    public TopSongDTO(Long songId, String title, Long playCount, String coverImage) {
        this.songId = songId;
        this.title = title;
        this.playCount = playCount;
        this.coverImage = coverImage;
    }

    public TopSongDTO(Long songId, String title, Long playCount, String coverImage, Long likeCount) {
        this.songId = songId;
        this.title = title;
        this.playCount = playCount;
        this.coverImage = coverImage;
        this.likeCount = likeCount;
    }

    public Long getSongId() { return songId; }
    public String getTitle() { return title; }
    public Long getPlayCount() { return playCount; }
    public String getCoverImage() { return coverImage; }
    public Long getLikeCount() { return likeCount; }
}