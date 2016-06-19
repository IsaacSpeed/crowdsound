package crowdsound

class UserController {

    def scaffold = crowdsound.User
    def index() { }
    def partyview() {
        String code = "!TODO!"
        String nickname = params.nickname
        String artists = params.a1 + ", " + params.a2 + ", " + params.a3 + ", " + params.a4 + ", " + params.a5
        String genres = params.g1 + ", " + params.g2 + ", " + params.g3 + ", " + params.g4 + ", " + params.g5
        int admin = 0
        User u = new User(code, nickname, artists, genres, admin)
        u.save()
    }
    def adminview() {}
}
