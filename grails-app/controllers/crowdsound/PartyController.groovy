package crowdsound

import groovy.json.JsonOutput

class PartyController {

    def index() { render "nope" }

    def start() {
        String partyCode = params.partyCode
        println params
        println partyCode

        if (partyCode) {
            Party party = Party.findByCode(partyCode)

            if (party) {
                party.isStarted = true
                party.save(flush: true)
                println "Party $partyCode has started! $party.isStarted"

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
                println "Deleting party $partyCode"
                party.delete()

                Auth auth = Auth.findByPartyCode(partyCode)
                auth.partyCode = ''
                auth.save()

                render "Party $partyCode has ended!"
            } else {
                println "Could not find party to delete."
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

    def isStarted() {
        String partyCode = params.partyCode

        if (partyCode) {
            Party party = Party.findByCode(partyCode)

            if (party) {
                println "Party $party.code/$partyCode is started? $party.isStarted"
                render party.isStarted
            } else {
                render "Invalid party code"
            }
        } else {
            render "No party code specified"
        }
    }

    def getGenresAndArtistsFrequency() {
        String partyCode = params.partyCode
        Party party = Party.findByCode(partyCode)
        SpotifyWrapper wrapper = new SpotifyWrapper()
        wrapper.setAccessToken(Auth.findByPartyCode(partyCode)?.authorize())

        if (partyCode && party) {
            List artistNames = new ArrayList<String>()
            party.artists.each { artistNames.add(wrapper.getArtist(it).getName())}

            ArrayList<String> both = (party.genres + artistNames).findAll() as ArrayList<String>

            HashMap frequency = getWordFrequency(both)

            render wordFrequencyToJson(frequency)
        } else {
            render "Invalid party code"
        }
    }

    private String wordFrequencyToJson(HashMap<String, Integer> wordFrequency) {
        String wordsJson

        wordFrequency.each { word, frequency ->
            if (wordsJson) {
                wordsJson = """$wordsJson,{"text": "$word", "size": $frequency}"""
            } else {
                wordsJson = """{"word_freq": [{"text": "$word", "size": $frequency}"""
            }
        }
        wordsJson = "$wordsJson]}"

        return wordsJson
    }

    private HashMap<String, Integer> getWordFrequency(ArrayList<String> words) {
        HashMap<String, Integer> wordFrequency = new HashMap<String, Integer>();

        for (String word : words) {
            if (wordFrequency.get(word)) {
                wordFrequency.put(word, wordFrequency.get(word) + 1)
            } else {
                wordFrequency.put(word, 1)
            }
        }

        return wordFrequency
    }
}
