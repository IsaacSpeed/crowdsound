package crowdsound

import com.wrapper.spotify.Api

class SpotifyController {

    def index() { render "this is an index fam" }

    def addCoolSongsToNewPlaylist() {
        Auth userAuth = Auth.findByUserId("12182647490")
        String accessToken = userAuth.authorize()

        SpotifyWrapper wrapper = new SpotifyWrapper()
        wrapper.setAccessToken(accessToken)
        Api api = wrapper.getApi()
        api.setAccessToken(accessToken)
        api.createPlaylist(userAuth.userId, "My cool playlist yo").build().get()

        String messages = "$userAuth.userId and $accessToken"

        return [playlistName: wrapper.getPlaylistByName(userAuth.userId, "My cool playlist yo").getName(), messages: messages]
    }
}
