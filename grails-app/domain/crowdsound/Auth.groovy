package crowdsound

class Auth {
    String username
    String password
    static constraints = {
        username nullable: false
        password nullable: false
    }
}
