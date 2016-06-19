package crowdsound

class User {
    String code
    String nickname
    String artists
    String genres
    int admin

    public User(String c,
                String nn,
                String ar,
                String ge,
                int ad
                ){
        code = c
        nickname = nn
        artists = ar
        genres = ge
        admin = ad
    }

    static constraints = {
        code unique: true
    }
}
