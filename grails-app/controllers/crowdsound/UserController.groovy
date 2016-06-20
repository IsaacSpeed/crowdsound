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

            String a1Id, a2Id, a3Id, a4Id, a5Id
            if (params.a1) a1Id = wrapper.getFirstArtistResultByName(params.a1).getId()
            if (params.a2) a2Id = wrapper.getFirstArtistResultByName(params.a2).getId()
            if (params.a3) a3Id = wrapper.getFirstArtistResultByName(params.a3).getId()
            if (params.a4) a4Id = wrapper.getFirstArtistResultByName(params.a4).getId()
            if (params.a5) a5Id = wrapper.getFirstArtistResultByName(params.a5).getId()

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
    def adminview() {
        // copied and pasted from partyview
        Party party = Party.findByCode(params.partyCode)
        println params.partyCode
        if (party) {
            SpotifyWrapper wrapper = new SpotifyWrapper()
            wrapper.setAccessToken(Auth.findByPartyCode(party.getCode()).authorize())

            String a1Id, a2Id, a3Id, a4Id, a5Id
            if (params.artist1) a1Id = wrapper.getFirstArtistResultByName(params.artist1).getId()
            if (params.artist2) a2Id = wrapper.getFirstArtistResultByName(params.artist2).getId()
            if (params.artist3) a3Id = wrapper.getFirstArtistResultByName(params.artist3).getId()
            if (params.artist4) a4Id = wrapper.getFirstArtistResultByName(params.artist4).getId()
            if (params.artist5) a5Id = wrapper.getFirstArtistResultByName(params.artist5).getId()

            if (a1Id) party.addArtist(a1Id)
            if (a2Id) party.addArtist(a2Id)
            if (a3Id) party.addArtist(a3Id)
            if (a4Id) party.addArtist(a4Id)
            if (a5Id) party.addArtist(a5Id)

            if (params.genre1) party.addGenre(params.genre1)
            if (params.genre2) party.addGenre(params.genre2)
            if (params.genre3) party.addGenre(params.genre3)
            if (params.genre4) party.addGenre(params.genre4)
            if (params.genre5) party.addGenre(params.genre5)

            party.save()

            println party.artists
            return [partyCode: party.getCode(), playlistID: party.playlistId]
        } else {
            render "ERROR could not find party"
        }
    }
}
