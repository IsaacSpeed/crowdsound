package crowdsound

import com.wrapper.spotify.Api
import com.wrapper.spotify.exceptions.BadRequestException
import com.wrapper.spotify.models.AuthorizationCodeCredentials
import com.wrapper.spotify.models.RefreshAccessTokenCredentials

class Auth {
    String accessToken
    String refreshToken
    String username

    public Auth(String authCode) {
        authorize(authCode)


        SpotifyWrapper wrapper = new SpotifyWrapper()
        Api api = wrapper.getApi()
        api.setAccessToken(accessToken)
        api.setRefreshToken(refreshToken)

        username = api.getMe().build().get().getDisplayName()
    }

    public String authorize() {
        SpotifyWrapper wrapper = new SpotifyWrapper()
        final Api api = wrapper.getApi()

        try {
            RefreshAccessTokenCredentials refreshCredentials
            api.setRefreshToken(refreshToken)
            refreshCredentials = api.refreshAccessToken().build().get()

            accessToken = refreshCredentials.getAccessToken()
        } catch (BadRequestException e) {
            return e.getMessage()
        }

        return accessToken
    }

    public String authorize(String code) {
        SpotifyWrapper wrapper = new SpotifyWrapper()
        final Api api = wrapper.getApi()

        try {
            AuthorizationCodeCredentials authCredentials
            authCredentials = api.authorizationCodeGrant(code).build().get()

            accessToken = authCredentials.getAccessToken()
            refreshToken = authCredentials.getRefreshToken()
        } catch (BadRequestException e) {
            return e.getMessage()
        }

        return accessToken
    }

    static constraints = {
    }
}
