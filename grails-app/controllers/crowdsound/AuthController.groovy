package crowdsound

class AuthController {

    def auth() { }

    def save() {
        def auth = new Auth(params)
        auth.save()
        [token: auth.getToken()]
    }

}
