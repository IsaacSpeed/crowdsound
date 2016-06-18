package crowdsound

import com.wrapper.spotify.Api
import com.wrapper.spotify.exceptions.BadRequestException
import com.wrapper.spotify.models.AuthorizationCodeCredentials
import com.wrapper.spotify.models.RefreshAccessTokenCredentials

class Auth {
    // should we not include accessToken and instead just return it? idk
    //String accessToken
    String refreshToken
    String userId

    public Auth(String authCode) {
        authorize(authCode)


        SpotifyWrapper wrapper = new SpotifyWrapper()
        Api api = wrapper.getApi()
        api.setAccessToken(accessToken)
        api.setRefreshToken(refreshToken)

        userId = api.getMe().build().get().getId()
    }

    public String authorize() {
        SpotifyWrapper wrapper = new SpotifyWrapper()
        final Api api = wrapper.getApi()

        try {
            RefreshAccessTokenCredentials refreshCredentials
            api.setRefreshToken(refreshToken)
            refreshCredentials = api.refreshAccessToken().build().get()

            return refreshCredentials.getAccessToken()
        } catch (BadRequestException e) {
            return e.getMessage()
        }
    }

    public String authorize(String code) {
        SpotifyWrapper wrapper = new SpotifyWrapper()
        final Api api = wrapper.getApi()

        try {
            AuthorizationCodeCredentials authCredentials
            authCredentials = api.authorizationCodeGrant(code).build().get()

            refreshToken = authCredentials.getRefreshToken()
            return authCredentials.getAccessToken()
        } catch (BadRequestException e) {
            return e.getMessage()
        }
    }

    static constraints = {
        userId unique: true
    }
}
