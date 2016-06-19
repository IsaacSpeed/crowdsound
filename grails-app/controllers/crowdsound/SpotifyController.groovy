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

    def findSongs() {
        if (params.trackName) {
            Auth userAuth = Auth.findByUserId("12182647490")
            String accessToken = userAuth.authorize()

            SpotifyWrapper wrapper = new SpotifyWrapper()
            wrapper.setAccessToken(accessToken)

            String trackName = wrapper.getFirstArtistResultByName(params.trackName).getName()

            /*Party party1 = new Party(code: "PJI8LE", artists: ["6yhD1KjhLxIETFF7vIRf8B", "6R1T5YklzC9OFugGt8RwOD", "5xKp3UyavIBUsGy3DQdXeF", "6Shas1ACrMl25XHgTrzxeo"],
                        genres: ["Rock", "Country", "Rap"])
            party1.save()
            //render "Party saved"*/

            Party party = Party.findByCode("PJI8LE")
            pushSongToPartyPlaylist("PJI8LE", generateSong("PJI8LE"))
            render "$party likes ${party.genres.toString()}"

            [trackName: trackName]
        }
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
        println "Bearer $accessToken"

        // if there's nothing to see with, add a default song....
        if (!party.artists && !party.genres) {
            return "7GhIk7Il098yCjg4BQjzvb"
        }

        // if not, pick five random artists and five random genres
        Collections.shuffle(party.artists)
        Collections.shuffle(party.genres)

        println party.artists
        println party.genres

        println wrapper.generateRecommendations(party.artists.take(5), party.genres.take(5))

        return wrapper.generateRecommendations(party.artists.take(5), party.genres.take(5)).get(0).getUri()
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

        wrapper.addTrackToPlaylist(userAuth.userId, partyCode, songUri)
    }
}
