package crowdsound

import com.wrapper.spotify.Api
import com.wrapper.spotify.models.Track

class SpotifyController {

    def index() { render "this is an index fam" }

    def addCoolSongsToNewPlaylist() {
        Auth userAuth = Auth.findByUserId("12182647490")
        String accessToken = userAuth.authorize()

        SpotifyWrapper wrapper = new SpotifyWrapper()
        wrapper.setAccessToken(accessToken)

        wrapper.createPlaylist(userAuth.userId, "My cool playlist yo")

        String messages = "$userAuth.userId and $accessToken"

        return [playlistName: wrapper.getPlaylistByName(userAuth.userId, "My cool playlist yo").getName(), messages: messages]
    }

    def showRecs() {
        Auth userAuth = Auth.findByUserId("12182647490")
        String accessToken = userAuth.authorize()

        SpotifyWrapper wrapper = new SpotifyWrapper()
        wrapper.setAccessToken(accessToken)

        List<Track> tracks = wrapper.generateRecommendations(["3GJhraAgCA8s8XHENnDsXq"], ["Rock"])

        [tracks: tracks]
    }

    def findSongs() {
        if (params.trackName) {
            Auth userAuth = Auth.findByUserId("12182647490")
            String accessToken = userAuth.authorize()

            SpotifyWrapper wrapper = new SpotifyWrapper()
            wrapper.setAccessToken(accessToken)

            String trackName = wrapper.getFirstTrackResultByName(params.trackName).getName()

            [trackName: trackName]
        }
    }
}
