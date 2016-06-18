package crowdsound

class Auth {
    String token
    String refreshToken
    String code
    String username

    public Auth(String authCode) {
        code = authCode

    }
    static constraints = {
        code nullable: false
    }
}
