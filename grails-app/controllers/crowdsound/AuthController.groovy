package crowdsound

class AuthController {

    def auth() {

    }

    def save() {
        render "deprecated"
     }

    def authUser() {
        def code = params.userCode
        println "User entered PartyCode: " + code

        Party party = Party.findByCode(params.userCode)
        if (!party) {
            render "That's not a valid party code!"
        } else {
            println party.artists
        }

        [partyCode: code]
    }

    def authHost() {
        String partyCode = params.code

        if (!partyCode) {
            render "Please log in."
        }

        Auth auth = new Auth(partyCode)

        // check to see if the user is already in the database
        if (Auth.findByUserId(auth.userId)) {
            auth = Auth.findByUserId(auth.userId)

            partyCode = auth.partyCode

            if (!partyCode) {
                print "Party not found, creating....."
                partyCode = generatePartyCode()
                auth.partyCode = partyCode
                Party party = new Party(partyCode)
                party.save(failOnError: true, flush: true)
                println party.errors.allErrors
            } else {
                println "Party $partyCode already exists"
                Party party = Party.findByCode(partyCode)
                if (!party) {
                    partyCode = generatePartyCode()
                    auth.partyCode = partyCode
                    party = new Party(partyCode)
                    party.save(failOnError: true, flush: true)
                    println party.errors.allErrors
                }
            }
        } else {
            partyCode = generatePartyCode()
            auth.partyCode = partyCode
            auth.save()

            Party party = new Party(partyCode)
            party.save(failOnError: true, flush: true)
        }

        [code:partyCode, username:auth.userId]
    }

    private String generatePartyCode() {
        def pool = ['A'..'Z',0..9].flatten()
        Random rand = new Random(System.currentTimeMillis())

        def passChars = (0..5).collect { pool[rand.nextInt(pool.size())] }
        return passChars.join()
    }

}
