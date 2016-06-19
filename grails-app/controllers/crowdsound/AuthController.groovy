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
    }

    def authHost() {
        String code = params.code
        Auth auth = new Auth(code)
        String message = "Welcome, $auth.userId!"

        // check to see if the user is already in the database
        if (Auth.findByUserId(auth.userId)) {
            auth = Auth.findByUserId(auth.userId)
            message = "Welcome back, $auth.userId!"
        } else {
            auth.save()
        }

        def pool = ['A'..'Z',0..9].flatten()
        Random rand = new Random(System.currentTimeMillis())

        def passChars = (0..5).collect { pool[rand.nextInt(pool.size())] }
        def partyCode = passChars.join()
        println partyCode
        [code:partyCode, username:auth.userId, token:auth, errors: auth.errors.getAllErrors(), message: message]
    }

}
