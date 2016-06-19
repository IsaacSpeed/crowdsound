package crowdsound

class PartyController {

    def index() { render "nope" }

    def start() {
        String partyCode = params.partyCode
        println params
        println partyCode

        if (partyCode) {
            Party party = Party.findByCode(partyCode)

            if (party) {
                party.start()
                party.addSong()
                render "Party $partyCode has started!"
            } else {
                render "Invalid party code"
            }
        } else {
            render "Sorry, partycode not specified"
        }
    }
}
