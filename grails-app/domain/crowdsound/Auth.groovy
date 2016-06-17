package crowdsound

class Auth {
    String token
    static constraints = {
        token: blank: false
    }
}
