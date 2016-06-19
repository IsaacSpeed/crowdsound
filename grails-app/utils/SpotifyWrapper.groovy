package crowdsound

import com.wrapper.spotify.Api
import com.wrapper.spotify.models.*
import com.wrapper.spotify.methods.*

class SpotifyWrapper {

    /**
     * Create a playlist-style listening experience based on seed artists, tracks and genres.
     *
     * Recommendations are generated based on the available information for a given seed entity
     * and matched against similar artists and tracks. If there is sufficient information about
     * the provided seeds, a list of tracks will be returned together with pool size details.
     */
    public void generateRecommendations(artistSeed, genreSeed) {
        pass
    }

    /**
     * returns an Api set up with our crowdsound application
     */
    public Api getApi() {
        final String clientId = "cfa363d44d2743ab8f6dd82a6e7eaca8"
        final String clientSecret = "6738da3251614694a8d477b886066a67"
        final String redirectURI = "http://crowdsound.us/auth/save"

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

        try {
            final Track track = request.get();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return track;
    }

    /**
     * Create a playlist for a Spotify user. (The playlist will be empty until you add tracks.)
     */
    public boolean createPlaylist(String userID, String partyCode) {
        final PlaylistCreationRequest request = api.createPlaylist(userID, partyCode)
                .publicAccess(true)
                .build();

        try {
            final Playlist playlist = request.get();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e)
            return false;
        }
    }

    /**
     * Get a playlist owned by a Spotify user.
     */
    public Playlist getPlaylist(String userID, String playlistID) {
        final PlaylistRequest request = api.getPlaylist(userID, playlistID).build();
        final Playlist playlist = null;

        try {
            playlist = request.get();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return playlist;
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
    public Playlist getPlaylistByName(String userId, String playlistName) {
        final UserPlaylistsRequest userPlaylistsRequest = api.getPlaylistsForUser(userId).build()
        final List<SimplePlaylist> playlists

        try {
            playlists = userPlaylistsRequest.get().getItems()
        } catch (Exception e) {
            return null
        }

        Playlist playlist = playlists.find() { it.name.equals(playlistName) }

        return playlist
    }

    /**
     * Add one or more tracks to a user’s playlist.
     */
    public void addTrackToPlaylist(String userID, String playlistID, String trackURI) {
        final List<String> trackToAdd = trackURI;

        final AddTrackToPlaylistRequest request = api.addTracksToPlaylist(userID, playlistID, trackToAdd)
                .position(-1)
                .build();

        try {
            request.get();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Remove one or more tracks from a user’s playlist.
     */
    public void removeTrackFromPlaylist(String userID, String playlistID, String trackURI) {

    }
}