package crowdsound

class AuthController {

    def authHost() {
        def username = params.username
        def password = params.password
        println "User: " + username + " Pass: " + password

        //TODO authorize spotify credentials

        def pool = ['A'..'Z',0..9].flatten()
        Random rand = new Random(System.currentTimeMillis())

        def passChars = (0..5).collect { pool[rand.nextInt(pool.size())] }
        def code = passChars.join()
        println code
        [code:code]
    }

    def authUser() {
        def userCode = params.userCode
        println "User entered PartyCode: " + userCode

        //TODO check partycode against database
    }

}
