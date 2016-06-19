package crowdsound

class SpotifyController {

    def index() { render "this is an index fam" }

    def addCoolSongsToNewPlaylist() {
        Auth userAuth = Auth.findByUserId("12182647490")
        String accessToken = userAuth.authorize()

        SpotifyWrapper wrapper = new SpotifyWrapper()
        wrapper.setAccessToken(userAuth.authorize())
        boolean success = wrapper.createPlaylist(userAuth.userId, "My cool playlist yo")

        String messages = "$userAuth.userId and $accessToken"

        return [playlistName: wrapper.getPlaylistByName(userAuth.userId, "My cool playlist yo"), messages: messages]
    }
}
