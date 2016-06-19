package crowdsound

import com.wrapper.spotify.models.SimplePlaylist
import com.wrapper.spotify.models.Track

class Party {

    String code
    boolean isStarted

    static hasMany = [artists: String, genres: String]
    List artists
    List genres

    static constraints = {
        code unique: true
    }

    public Party(String partyCode){
        code = partyCode
        artists = new ArrayList<String>()
        genres = new ArrayList<String>()
        isStarted = false
    }

    public void start() {
        isStarted = true
        println "Party started!"
    }

    public void end() {
        isStarted = false
    }

    public void addSong() {
        println "Adding song..."
        String songUri = generateSong()
        pushSongToPartyPlaylist(songUri)

        if (isStarted) {
            SpotifyWrapper wrapper = new SpotifyWrapper()
            int duration = wrapper.getTrack(songUri.drop(14)).getDuration()
            println "Song's length is $duration"
            Timer timer = new Timer()
            def task = timer.runAfter(duration - 200) {
                println "Executing addSong again!"
                addSong()
            }
        }
    }

    public addArtist(String artist){
        artists.add(artist)
    }

    public addGenre(String genre){
        genres.add(genre)
    }

    // copied & pasted from wrapper then modified..
    /**
     * Generate a song based on a party's artists and genres
     * @param partyCode
     * @return a song's uri
     */
    private String generateSong() {
        Auth userAuth = Auth.findByPartyCode(code)
        String accessToken = userAuth.authorize()

        SpotifyWrapper wrapper = new SpotifyWrapper()
        wrapper.setAccessToken(accessToken)

        // if there's nothing to see with, add a default song....
        if (!artists && !genres) {
            return "7GhIk7Il098yCjg4BQjzvb"
        }

        // if not, pick five random artists and five random genres
        Collections.shuffle(artists)
        Collections.shuffle(genres)

        List<Track> recommendations = wrapper.generateRecommendations(artists.take(3), genres.take(2))

        if (recommendations) {
            return recommendations.get(0).getUri()
        } else {
            println "no recs found!!"
            return "spotify:track:5i7fZq3chLyCHo3VeB6goD"
        }
    }

    public void pushSongToPartyPlaylist(String songUri) {
        Auth userAuth = Auth.findByPartyCode(code)
        String accessToken = userAuth.authorize()

        SpotifyWrapper wrapper = new SpotifyWrapper()
        wrapper.setAccessToken(accessToken)

        SimplePlaylist partyPlaylist = wrapper.getPlaylistByName(userAuth.userId, code)

        // create the playlist if it does not exist
        if (!partyPlaylist) {
            wrapper.createPlaylist(userAuth.userId, code)
        }

        partyPlaylist = wrapper.getPlaylistByName(userAuth.userId, code)

        wrapper.addTrackToPlaylist(userAuth.userId, partyPlaylist.getId(), songUri)
    }
}
