package crowdsound

class AuthController {

    def auth() {

    }

    def save() {
        def code = params.code
        Auth auth = new Auth(code)
        auth.save()
        //def username = params.username
        //def password = params.password
        //println "User: " + username + " Pass: " + password

        def pool = ['A'..'Z',0..9].flatten()
        Random rand = new Random(System.currentTimeMillis())

        def passChars = (0..6).collect { pool[rand.nextInt(pool.size())] }
        def partyCode = passChars.join()
        println partyCode
        [code:partyCode]
    }

}
