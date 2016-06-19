package crowdsound

class AuthController {

    def auth() {

    }

    def save() {
        render "deprecated"
     }

    def authUser() {
        def code = params.userCode
        def nickname = params
        println "User entered PartyCode: " + code

        //TODO check partycode against database
        Party party = Party.findByCode(params.userCode)
        if (!party) {
            render "That's not a valid party code!"
        } else {
            println party.artists
        }
    }

    def authHost() {
        String partyCode
        String code = params.code

        if (!code) {
            render "Please log in."
        }

        Auth auth = new Auth(code)
        String message = "Welcome, $auth.userId!"

        // check to see if the user is already in the database
        if (Auth.findByUserId(auth.userId)) {
            auth = Auth.findByUserId(auth.userId)
            message = "Welcome back, $auth.userId!"
            partyCode = auth.partyCode
        } else {
            def pool = ['A'..'Z',0..9].flatten()
            Random rand = new Random(System.currentTimeMillis())

            def passChars = (0..5).collect { pool[rand.nextInt(pool.size())] }
            partyCode = passChars.join()

            auth.partyCode = partyCode
            auth.save()

            Party party = new Party()
            party.code = partyCode
        }

        [code:partyCode, username:auth.userId, errors: auth.errors.getAllErrors(), message: message]
    }

}
