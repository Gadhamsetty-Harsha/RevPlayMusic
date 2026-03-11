# 🎧 P2 – RevPlay

## 📌 Overview
RevPlayMusic is a full-stack monolithic music streaming web application. Users explore songs, artists, playlists, albums, and podcasts through a responsive web interface. Users search music, mark favorites, manage playlists, and play songs using an integrated web music player. Artists create profiles, upload music, manage albums, and track engagement analytics. The system supports role-based access, responsive UI, and scalable backend architecture.

## 🎯 Core Features

## 👤 Listener (User) Features

### Authentication & Profile
- 📝 Register account using email, username, and password
- 🔐 Secure login using email or username
- 👤 View and edit profile information
- 🖼️ Upload profile picture
- 📊 View personal account statistics

### Music Discovery
- 🔍 Search songs, artists, albums, and playlists
- 📂 Browse music by genre, artist, or album
- 🎧 Browse complete music library
- 📅 Filter songs by release year
- 📄 View song details including title, artist, album, genre, and duration
- 👨‍🎤 View artist profiles with songs and albums
- 💿 View album details with track list

### Music Player & Playback
- ▶️ Play songs using integrated web player
- ⏸️ Pause music playback
- ⏭️ Skip forward and backward
- ⏩ Seek through song timeline
- 📊 View progress bar of current song
- 📑 Manage playback queue
- 🔁 Enable repeat modes (off, repeat one, repeat all)
- 🔀 Enable shuffle playback
- 🔊 Adjust volume level

### Favorites & Collections
- ❤️ Mark songs as favorites
- ❌ Remove songs from favorites
- 📃 View favorite songs list
- ⚡ Quick access to favorites from music player

### Playlist Management
- 📀 Create playlists with name and description
- 🔐 Set playlist privacy (public or private)
- ➕ Add songs to playlists
- ➖ Remove songs from playlists
- 🔀 Reorder songs in playlists
- 📑 View all playlists
- ✏️ Update playlist details
- 🗑️ Delete playlists created by user
- 🌍 View public playlists
- ⭐ Follow or unfollow playlists

### Listening History
- 🕒 View recently played songs (last 50 tracks)
- 📊 View complete listening history with timestamps
- 🧹 Clear listening history

## 🎤 Artist Features
Artists include all listener features plus the following capabilities.

### Account
- 🧑‍🎤 Register as an artist
- 🔐 Login securely

### Artist Profile Management
- 📝 Create and manage artist profile
- 🎼 Update bio and genre
- 🖼️ Upload profile picture and banner image
- 🔗 Add social media links (Instagram, Twitter, Spotify, Website)
- 👀 Preview public artist profile

### Music Upload & Management
- 🎶 Upload songs with metadata
- 💿 Upload album artwork
- 📀 Create albums with description and release date
- ➕ Add songs to albums
- ➖ Remove songs from albums
- 📂 View uploaded songs and albums
- ✏️ Update song and album information
- 🗑️ Delete songs
- 🗑️ Delete albums when empty
- 🔓 Set song visibility (public or unlisted)

### Analytics & Insights
- 📊 Artist dashboard with key metrics
- 🎶 View total songs uploaded
- ▶️ Track play count for songs
- ❤️ View number of favorites
- 🔝 View most popular songs
- 📈 Analyze listening trends
- 👥 Identify top listeners

## 🔐 Standard Functional Scope
- Secure authentication system
- Role-based access control
- Music player integration
- File management for audio and images
- Data storage and retrieval
- Responsive user interface

## 🧑‍💻 Roles and Responsibilities
- Designed full-stack application architecture
- Implemented backend using Spring Boot
- Developed REST APIs for music and user management
- Implemented database schema and queries
- Integrated web music player functionality
- Implemented logging and error handling
- Performed unit testing
- Managed source code using Git

## 🛠️ Tech Stack
- ☕ Java
- 🌱 Spring Boot
- 🗄️ SQL Database
- 📦 Maven
- 🧪 JUnit4
- 🪵 Log4J
- 🌿 Git

---

# 📊 Database ER Diagram

The database schema models users, artists, songs, albums, playlists, podcasts, favorites, and listening history.

```mermaid
erDiagram

    USERS {
        BIGINT user_id PK
        VARCHAR user_name
        VARCHAR email
        VARCHAR user_password
        ENUM role
        TEXT bio
        VARCHAR profile_image
        VARCHAR security_question
        VARCHAR security_answer
        TIMESTAMP created_at
    }

    ARTISTS {
        BIGINT artist_id PK
        BIGINT user_id FK
        VARCHAR artist_name
        TEXT bio
        VARCHAR genre
        VARCHAR banner_image
        VARCHAR instagram_link
        VARCHAR twitter_link
        VARCHAR spotify_link
        VARCHAR website_link
    }

    ALBUMS {
        BIGINT album_id PK
        BIGINT artist_id FK
        VARCHAR title
        TEXT description
        DATE release_date
        VARCHAR cover_image
        TIMESTAMP created_at
    }

    SONGS {
        BIGINT song_id PK
        BIGINT artist_id FK
        BIGINT album_id FK
        VARCHAR title
        VARCHAR genre
        INT duration
        VARCHAR audio_file_path
        BIGINT play_count
        BIGINT like_count
        DATE release_date
        TIMESTAMP created_at
    }

    PODCASTS {
        BIGINT podcast_id PK
        BIGINT artist_id FK
        VARCHAR title
        TEXT description
        VARCHAR category
        VARCHAR cover_image
        TIMESTAMP created_at
    }

    PODCAST_EPISODES {
        BIGINT episode_id PK
        BIGINT podcast_id FK
        VARCHAR title
        TEXT description
        INT duration
        VARCHAR audio_file_path
        DATE release_date
        BIGINT play_count
        TIMESTAMP created_at
    }

    FAVORITES {
        BIGINT user_id FK
        BIGINT song_id FK
        TIMESTAMP added_at
    }

    PLAYLISTS {
        BIGINT playlist_id PK
        BIGINT user_id FK
        VARCHAR name
        TEXT description
        ENUM privacy
        TIMESTAMP created_at
    }

    PLAYLIST_SONGS {
        BIGINT playlist_id FK
        BIGINT song_id FK
        INT position
    }

    LISTENING_HISTORY {
        BIGINT history_id PK
        BIGINT user_id FK
        BIGINT song_id FK
        TIMESTAMP played_at
    }

    USERS ||--|| ARTISTS : "artist profile"
    ARTISTS ||--o{ ALBUMS : creates
    ARTISTS ||--o{ SONGS : uploads
    ALBUMS ||--o{ SONGS : contains

    USERS ||--o{ FAVORITES : likes
    SONGS ||--o{ FAVORITES : favorited

    USERS ||--o{ PLAYLISTS : creates
    PLAYLISTS ||--o{ PLAYLIST_SONGS : contains
    SONGS ||--o{ PLAYLIST_SONGS : included_in

    USERS ||--o{ LISTENING_HISTORY : listens
    SONGS ||--o{ LISTENING_HISTORY : played
```
### ER Diagram

![RevPlay ER Diagram](RevPlay_ERD.jpeg)
---

# 🏗️ Spring Boot Architecture

RevPlay follows a layered architecture using Spring Boot.

### Layers

Client Layer  
Web browser interface and music player.

Controller Layer  
Handles HTTP requests.

Examples:
- UserController
- SongController
- PlaylistController
- ArtistController

Service Layer  
Contains business logic.

Examples:
- UserService
- SongService
- PlaylistService
- ArtistService

Repository Layer  
Handles database operations.

Examples:
- UserRepository
- SongRepository
- PlaylistRepository
- AlbumRepository

Database Layer  
MySQL database storing all application data.

### Architecture Diagram

![RevPlay Architecture](RevPlay_Architecture.jpeg)
---

## 🚀 Key Highlights
- Full-stack music streaming platform
- Integrated web music player
- Role-based user and artist system
- Playlist and favorite management
- Artist analytics dashboard
- Scalable layered architecture

---
RevPlay = Revature + MusicPlayer 🎧🎶
