package crowdsound

class Party {

    String code
    ArrayList<String> artists;
    ArrayList<String> genres;

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
    static constraints = {
        code unique: true
    }
}
