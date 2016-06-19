package crowdsound

import com.wrapper.spotify.Api
import com.wrapper.spotify.models.Playlist
import com.wrapper.spotify.models.SimplePlaylist
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

    def generateSongToPlaylist() {
        String message

        if (params.partyCode) {
            String partyCode = params.partyCode
            Auth userAuth = Auth.findByPartyCode(partyCode)
            String accessToken = userAuth.authorize()

            SpotifyWrapper wrapper = new SpotifyWrapper()
            wrapper.setAccessToken(accessToken)

            Party party = Party.findByCode(partyCode)
            String songUri = generateSong(partyCode)
            String songName = wrapper.getTrack(songUri.drop(14) as String).getName()
            pushSongToPartyPlaylist(partyCode, songUri)
            message = "$party likes ${party.genres.toString()} and ${party.artists.toString()}, so we added $songName to their playlist"
        } else {
            message = "No party code specified."
        }

        [message: message]
    }

    /**
     * Generate a song based on a party's artists and genres
     * @param partyCode
     * @return a song's uri
     */
    private String generateSong(String partyCode) {
        Party party = Party.findByCode(partyCode)
        Auth userAuth = Auth.findByPartyCode(partyCode)
        String accessToken = userAuth.authorize()

        SpotifyWrapper wrapper = new SpotifyWrapper()
        wrapper.setAccessToken(accessToken)

        // if there's nothing to see with, add a default song....
        if (!party.artists && !party.genres) {
            return "7GhIk7Il098yCjg4BQjzvb"
        }

        // if not, pick five random artists and five random genres
        Collections.shuffle(party.artists)
        Collections.shuffle(party.genres)

        List<Track> recommendations = wrapper.generateRecommendations(party.artists.take(3), party.genres.take(2))

        if (recommendations) {
            return recommendations.get(0).getUri()
        } else {
            println "no recs found!!"
            return "spotify:track:5i7fZq3chLyCHo3VeB6goD"
        }
    }

    public void pushSongToPartyPlaylist(String partyCode, String songUri) {
        Auth userAuth = Auth.findByPartyCode(partyCode)
        String accessToken = userAuth.authorize()

        SpotifyWrapper wrapper = new SpotifyWrapper()
        wrapper.setAccessToken(accessToken)

        SimplePlaylist partyPlaylist = wrapper.getPlaylistByName(userAuth.userId, partyCode)

        // create the playlist if it does not exist
        if (!partyPlaylist) {
            wrapper.createPlaylist(userAuth.userId, partyCode)
        }

        partyPlaylist = wrapper.getPlaylistByName(userAuth.userId, partyCode)

        wrapper.addTrackToPlaylist(userAuth.userId, partyPlaylist.getId(), songUri)
    }
}
