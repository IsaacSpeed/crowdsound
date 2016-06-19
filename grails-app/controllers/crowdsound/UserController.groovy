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
        println params.partyCode
        if (party) {
            SpotifyWrapper wrapper = new SpotifyWrapper()
            wrapper.setAccessToken(Auth.findByPartyCode(party.getCode()).authorize())

            String a1Id = wrapper.getFirstArtistResultByName(params.a1)
            String a2Id = wrapper.getFirstArtistResultByName(params.a2)
            String a3Id = wrapper.getFirstArtistResultByName(params.a3)
            String a4Id = wrapper.getFirstArtistResultByName(params.a4)
            String a5Id = wrapper.getFirstArtistResultByName(params.a5)

            if (a1Id) party.addArtist(a1Id)
            if (a2Id) party.addArtist(a2Id)
            if (a3Id) party.addArtist(a3Id)
            if (a4Id) party.addArtist(a4Id)
            if (a5Id) party.addArtist(a5Id)

            if (params.g1) party.addGenre(params.g1)
            if (params.g2) party.addGenre(params.g2)
            if (params.g3) party.addGenre(params.g3)
            if (params.g4) party.addGenre(params.g4)
            if (params.g5) party.addGenre(params.g5)

            party.save()

            println party.artists

            return [partyCode: party.getCode()]
        } else {
            render "ERROR could not find party"
        }
    }
    def adminview() {}
}
