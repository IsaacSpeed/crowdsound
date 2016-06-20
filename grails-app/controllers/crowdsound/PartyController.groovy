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
                party.save()

                while (party.isStarted) {
                    int duration = party.addSong()
                    sleep(duration - 2000)
                }
                render "Party $partyCode has started!"
            } else {
                render "Invalid party code"
            }
        } else {
            render "Sorry, partycode not specified"
        }
    }

    def addSong() {
        String partyCode = params.partyCode

        if (partyCode) {
            Party party = Party.findByCode(partyCode)

            if (party) {
                int duration = party.addSong()
                render duration
            } else {
                render "Invalid party code"
            }
        } else {
            render "No party code specified"
        }
    }

    def isPartyStarted() {
        String partyCode = params.partyCode

        if (partyCode) {
            Party party = Party.findByCode(partyCode)

            if (party) {
                render party.isStarted
            } else {
                render "Invalid party code"
            }
        } else {
            render "No party code specified"
        }
    }
}
