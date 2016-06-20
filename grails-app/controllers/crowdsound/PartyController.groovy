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

                // add two extra songs, in case someone skips a song?
                party.addSong()
                party.addSong()

                render "Party $partyCode has started!"

                while (party.isStarted) {
                    int duration = party.addSong()

                    if (params.isPresentation) sleep(30000)
                    else sleep(duration - 2000)
                }
            } else {
                render "Invalid party code"
            }
        } else {
            render "Sorry, partycode not specified"
        }
    }

    def stop() {
        String partyCode = params.partyCode
        println params
        println partyCode

        if (partyCode) {
            Party party = Party.findByCode(partyCode)

            if (party) {
                party.end()
                party.save()

                render "Party $partyCode has stopped!"
            } else {
                render "Invalid party code"
            }
        } else {
            render "Sorry, partycode not specified"
        }
    }

    def end() {
        String partyCode = params.partyCode
        println params
        println partyCode

        if (partyCode) {
            Party party = Party.findByCode(partyCode)

            if (party) {
                party.delete()

                Auth auth = Auth.findByPartyCode(partyCode)
                auth.partyCode = ''
                auth.save()

                render "Party $partyCode has ended!"
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
