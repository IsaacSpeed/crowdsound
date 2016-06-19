package crowdsound

class UserController {

    def scaffold = crowdsound.User
    def index() { }
    def partyview() {
        String code = params.partyCode
        String nickname = params.nickname
        String artists = params.a1 + ", " + params.a2 + ", " + params.a3 + ", " + params.a4 + ", " + params.a5
        String genres = params.g1 + ", " + params.g2 + ", " + params.g3 + ", " + params.g4 + ", " + params.g5
        int admin = 0
        User u = new User(code, nickname, artists, genres, admin)
        u.save()

        Party party = Party.findByCode(params.partyCode)
        if (party) {
            artists.split(',').each { party.addArtist(it) }
            genres.split(',').each { party.addGenre(it) }
            party.save()
        }
    }
    def adminview() {}
}
