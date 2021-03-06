package crowdsound

import com.wrapper.spotify.Api
import com.wrapper.spotify.JsonUtil
import com.wrapper.spotify.models.*
import com.wrapper.spotify.methods.*
import groovy.json.JsonSlurper
import wslite.rest.ContentType
import wslite.rest.RESTClient
import wslite.rest.RESTClientException
import wslite.rest.Response

class SpotifyWrapper {
    Api api
    String accessToken

    public SpotifyWrapper() {
        api = getApi()
    }

    /**
     * Create a playlist-style listening experience based on seed artists, tracks and genres.
     *
     * Recommendations are generated based on the available information for a given seed entity
     * and matched against similar artists and tracks. If there is sufficient information about
     * the provided seeds, a list of tracks will be returned together with pool size details.
     */
    public List<Track> generateRecommendations(artistSeedsArray, genreSeedsArray) {
        RESTClient client = new RESTClient("https://api.spotify.com/v1/")
        String endpoint = "recommendations"

        String artistSeeds = artistSeedsArray.take(5).join(',')
        String genreSeeds = genreSeedsArray.take(5).join(',')
        Response response

        def queryParams = "?"
        if (artistSeeds) {
            queryParams = "${queryParams}seed_artists=$artistSeeds"
            if (genreSeeds) {
                queryParams = "${queryParams}&"
            }
        }
        if (genreSeeds) {
            queryParams = "${queryParams}seed_genres=$genreSeeds"
        }
        try {
            println "$endpoint$queryParams"
            response = client.get(path: "$endpoint$queryParams", headers: ['Authorization': "Bearer $accessToken"])
        } catch (RESTClientException e) {
            println e.message
            println e.response
            return null
        }

        return JsonUtil.createTracks(response.contentAsString)
    }

    public List<String> getAvailableGenres() {
        RESTClient client = new RESTClient("https://api.spotify.com/v1/")
        String endpoint = "recommendations/available-genre-seeds"
        String authorization = "Bearer $accessToken"
        JsonSlurper slurper = new JsonSlurper()
        Response response
        List<String> genres

        try {
            response = client.get(path: endpoint, headers: ['Authorization': authorization])
            genres = slurper.parseText(response.contentAsString).genres as List
        } catch (RESTClientException e) {
            println "Error getting genres! $e.message"
            genres = ["No available genres"]
        }

        return genres
    }

    /**
     * Search for a track by name. It will return the first result
     * @param name
     * @return
     */
    public Track getFirstTrackResultByName(String name) {
        return api.searchTracks(name).build().get().getItems().get(0)
    }

    public Artist getFirstArtistResultByName(String name) {
        List items = api.searchArtists(name).build().get().getItems()
        if (items.size() > 0) return items.get(0)
        else return null
    }

    public void setAccessToken(String accessToken) {
        api.setAccessToken(accessToken)
        this.accessToken = accessToken
    }

    /**
     * returns an Api set up with our crowdsound application
     */
    public Api getApi() {
        final String clientId = "cfa363d44d2743ab8f6dd82a6e7eaca8"
        final String clientSecret = "6738da3251614694a8d477b886066a67"
        final String redirectURI = "http://crowdsound.us/auth/authHost"

        final Api api = Api.builder()
            .clientId(clientId)
            .clientSecret(clientSecret)
            .redirectURI(redirectURI)
            .build();

        return api
    }

    /**
     * Get Spotify catalog information for a single track identified by its unique Spotify ID.
     */
    public Track getTrack(String trackID) {
        final TrackRequest request = api.getTrack(trackID).build();
        Track track

        try {
            track = request.get();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return track;
    }

    /**
     * Get Spotify catalog information for a single artist identified by its unique Spotify ID.
     */
    public Artist getArtist(String artistId) {
        final ArtistRequest request = api.getArtist(artistId).build()
        Artist artist

        try {
            artist = request.get();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return artist;
    }

    /**
     * Create a playlist for a Spotify user. (The playlist will be empty until you add tracks.)
     * returns playlist id
     */
    public String createPlaylist(String userID, String partyCode) {
        final PlaylistCreationRequest request = api.createPlaylist(userID, partyCode)
                .publicAccess(true)
                .build();

        try {
            final Playlist playlist = request.get();
            return playlist.getId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e)
            return null;
        }
    }

    /**
     * Get a playlist owned by a Spotify user.
     */
    public Playlist getPlaylist(String userID, String playlistID) {
        final PlaylistRequest request = api.getPlaylist(userID, playlistID).build()
        Playlist playlist

        try {
            playlist = request.get()
        } catch (Exception e) {
            System.out.println("Error finding playlist: " + e.getMessage())
        }

        return playlist
    }

    /**
     * Get full details of the tracks of a playlist owned by a Spotify user.
     */
    public Page<PlaylistTrack> getPlaylistTracks(userID, playlistID) {
        final PlaylistRequest request = api.getPlaylist(userID, playlistID).build();
        final Playlist playlist = null;

        try {
            playlist = request.get();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return playlist?.getTracks();
    }

    /**
     * Get a user's playlist by name. Must be exact match, probablyl
     */
    public SimplePlaylist getPlaylistByName(String userId, String playlistName) {
        final UserPlaylistsRequest userPlaylistsRequest = api.getPlaylistsForUser(userId).build()
        final List<SimplePlaylist> playlists

        try {
            playlists = userPlaylistsRequest.get().getItems()
        } catch (Exception e) {
            return null
        }

        SimplePlaylist playlist = playlists.find() { it.name.equals(playlistName) }

        return playlist
    }

    /**
     * Add one or more tracks to a user’s playlist.
     */
    public void addTrackToPlaylist(String userID, String playlistID, String trackURI) {
        final List<String> trackToAdd = [trackURI];

        final AddTrackToPlaylistRequest request = api.addTracksToPlaylist(userID, playlistID, trackToAdd)
                .position(-1)
                .build();

        try {
            request.get();
        } catch (Exception e) {
            System.out.println("Adding track returned" + e.getMessage());
        }
    }

    /**
     * Remove one or more tracks from a user’s playlist.
     */
    public void removeTrackFromPlaylist(String userID, String playlistID, String trackURI) {

    }
}