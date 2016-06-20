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
        String message = "Welcome!"

        // check to see if the user is already in the database
        if (Auth.findByUserId(auth.userId)) {
            auth = Auth.findByUserId(auth.userId)
            message = "Welcome back!"

            partyCode = auth.partyCode

            if (!partyCode) {
                print "Party not found, creating....."
                partyCode = generatePartyCode()
                auth.partyCode = partyCode
                Party party = new Party(partyCode)
                party.save()
                println party.errors.allErrors
            }
        } else {
            partyCode = generatePartyCode()
            auth.partyCode = partyCode
            auth.save()

            Party party = new Party(partyCode)
            party.save()
            println party.errors.allErrors
        }

        [code:partyCode, username:auth.userId, errors: auth.errors.getAllErrors(), message: message]
    }

    private String generatePartyCode() {
        def pool = ['A'..'Z',0..9].flatten()
        Random rand = new Random(System.currentTimeMillis())

        def passChars = (0..5).collect { pool[rand.nextInt(pool.size())] }
        return passChars.join()
    }

}
