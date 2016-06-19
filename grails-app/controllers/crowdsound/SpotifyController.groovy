package crowdsound

class SpotifyController {

    def index() { }

    def addCoolSongsToNewPlaylist() {
        Auth userAuth = Auth.findByUserId("12182647490")
        String accessToken = userAuth.authorize()

        SpotifyWrapper wrapper = new SpotifyWrapper()
        wrapper.createPlaylist(userAuth.userId, "My cool playlist yo")

        [playlistName: wrapper.getPlaylistByName(userAuth.userId, "My cool playlist yo")]
    }
}
