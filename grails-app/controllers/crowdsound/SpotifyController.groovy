package crowdsound

class SpotifyController {

    def index() { render "this is an index fam" }

    def addCoolSongsToNewPlaylist() {
        Auth userAuth = Auth.findByUserId("12182647490")
        String accessToken = userAuth.authorize()

        SpotifyWrapper wrapper = new SpotifyWrapper()
        boolean success = wrapper.createPlaylist(userAuth.userId, "My cool playlist yo")

        if (success)
            return [playlistName: wrapper.getPlaylistByName(userAuth.userId, "My cool playlist yo")]
        else
            return [playlistName: "Failure haha"]
    }
}
