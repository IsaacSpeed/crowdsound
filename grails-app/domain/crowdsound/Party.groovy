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

    public int addSong() {
        println "Adding song..."
        String songUri = generateSong()
        println songUri
        pushSongToPartyPlaylist(songUri)

        SpotifyWrapper wrapper = new SpotifyWrapper()
        return wrapper.getTrack(songUri.drop(14) as String).getDuration()
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
            return "spotify:track:7GhIk7Il098yCjg4BQjzvb"
        }

        // if not, pick five random artists and five random genres
        Collections.shuffle(artists)
        Collections.shuffle(genres)

        List artistsToSeed = artists.take(3)
        List genresToSeed = genres.take(2)

        artistsToSeed.each { println wrapper.getArtist(it) }
        genresToSeed.each { println it }

        List<Track> recommendations = wrapper.generateRecommendations(artistsToSeed, genresToSeed)

        if (recommendations) {
            println "Generated recommendations for $code!"
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
            println "Creating playlist"
            wrapper.createPlaylist(userAuth.userId, code)
        }

        partyPlaylist = wrapper.getPlaylistByName(userAuth.userId, code)

        println "Found playlist ${partyPlaylist.getName()}"

        wrapper.addTrackToPlaylist(userAuth.userId, partyPlaylist.getId(), songUri)
        println "added song to playlist!"
    }
}
