package crowdsound

class UserController {

    def scaffold = crowdsound.User
    def index() { }

    def partyview() {
        println params.genres
        String code = params.partyCode
        String nickname = params.nickname
        String artists = params.a1 + ", " + params.a2 + ", " + params.a3 + ", " + params.a4 + ", " + params.a5
        List genres = params.genres
        int admin = 0
        User u = new User(code, nickname, artists, genres, admin)
        u.save()

        Party party = Party.findByCode(params.partyCode)
        if (party) {
            SpotifyWrapper wrapper = new SpotifyWrapper()
            Auth auth = Auth.findByPartyCode(party.code)
            wrapper.setAccessToken(auth.authorize())

            String a1Id, a2Id, a3Id, a4Id, a5Id
            if (params.artist1) a1Id = wrapper.getFirstArtistResultByName(params.artist1)?.getId()
            if (params.artist2) a2Id = wrapper.getFirstArtistResultByName(params.artist2)?.getId()
            if (params.artist3) a3Id = wrapper.getFirstArtistResultByName(params.artist3)?.getId()
            if (params.artist4) a4Id = wrapper.getFirstArtistResultByName(params.artist4)?.getId()
            if (params.artist5) a5Id = wrapper.getFirstArtistResultByName(params.artist5)?.getId()

            if (a1Id) party.addArtist(a1Id)
            if (a2Id) party.addArtist(a2Id)
            if (a3Id) party.addArtist(a3Id)
            if (a4Id) party.addArtist(a4Id)
            if (a5Id) party.addArtist(a5Id)

            if (params.genres) genres.each { party.addGenre(it) }

            party.save()

            List<String> artistNames = new ArrayList<String>()

            party.artists.each { artistNames.add(wrapper.getArtist(it).getName()) }

            return [ partyCode: party.getCode(), userId: auth.userId, playlistId: party.playlistId, artists: artistNames, genres: party.genres ]
        } else {
            render "ERROR could not find party"
        }
    }
    def adminview() {
        // copied and pasted from partyview
        Party party = Party.findByCode(params.partyCode)
        println params.partyCode
        if (party) {
            println params.genres
            SpotifyWrapper wrapper = new SpotifyWrapper()
            wrapper.setAccessToken(Auth.findByPartyCode(party.getCode()).authorize())

            String a1Id, a2Id, a3Id, a4Id, a5Id
            if (params.artist1) a1Id = wrapper.getFirstArtistResultByName(params.artist1)?.getId()
            if (params.artist2) a2Id = wrapper.getFirstArtistResultByName(params.artist2)?.getId()
            if (params.artist3) a3Id = wrapper.getFirstArtistResultByName(params.artist3)?.getId()
            if (params.artist4) a4Id = wrapper.getFirstArtistResultByName(params.artist4)?.getId()
            if (params.artist5) a5Id = wrapper.getFirstArtistResultByName(params.artist5)?.getId()

            if (a1Id) party.addArtist(a1Id)
            if (a2Id) party.addArtist(a2Id)
            if (a3Id) party.addArtist(a3Id)
            if (a4Id) party.addArtist(a4Id)
            if (a5Id) party.addArtist(a5Id)

            if (params.genres) params.genres.each { party.addGenre(it) }

            println "These are our params: $params"
            println party.artists + party.genres

            party.save()

            // add song so there will be a playlist
            party.addSong()

            List<String> artistNames = new LinkedList<String>()
            party.artists.each { artistNames.add(wrapper.getArtist(it).getName()) }

            println party.playlistId
            println artistNames
            println party.genres

            Auth auth = Auth.findByPartyCode(party.code)

            return [partyCode: party.getCode(), userId: auth.userId, playlistId: party.playlistId, artists: artistNames, genres: party.genres ]
        } else {
            render "ERROR could not find party"
        }
    }
}
