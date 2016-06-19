package crowdsound

class Party {

    String code
    ArrayList<String> artists;
    ArrayList<String> genres;

    static hasMany = [artists: String, genres: String]

    static constraints = {
        code unique: true
    }

    public Party(String partyCode){
        code = partyCode
        artists = new ArrayList<String>()
        genres = new ArrayList<String>()
    }

    public addArtist(String artist){
        artists.add(artist)
    }

    public addGenre(String genre){
        genres.add(genre)
    }
}
