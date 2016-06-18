package crowdsound

import com.wrapper.spotify.Api
import com.wrapper.spotify.models.AuthorizationCodeCredentials
import com.wrapper.spotify.models.RefreshAccessTokenCredentials

class Auth {
    String token
    String accessToken
    String refreshToken
    String code
    String username

    public Auth(String authCode) {
        code = authCode
        authorize()


        SpotifyWrapper wrapper = new SpotifyWrapper()
        Api api = wrapper.getApi()
        api.setAccessToken(accessToken)
        api.setRefreshToken(refreshToken)

        username = api.getMe().build().get().getDisplayName()

    }

    public String authorize() {
        SpotifyWrapper wrapper = new SpotifyWrapper()
        final Api api = wrapper.getApi()

        if (!refreshToken) {
            AuthorizationCodeCredentials authCredentials
            authCredentials = api.authorizationCodeGrant(code).build().get()

            accessToken = authCredentials.getAccessToken()
            refreshToken = authCredentials.getRefreshToken()
        } else {
            RefreshAccessTokenCredentials refreshCredentials
            api.setRefreshToken(refreshToken)
            refreshCredentials = api.refreshAccessToken().build().get()

            accessToken = refreshCredentials.getAccessToken()
        }

        return accessToken
    }
    static constraints = {
        code nullable: false
    }
}
