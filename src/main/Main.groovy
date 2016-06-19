import com.wrapper.spotify.methods.*
import com.wrapper.spotify.Api
import com.wrapper.spotify.models.*;


Api api = Api.DEFAULT_API

final TrackRequest request = api.getTrack("6D1P1mKUJX1AXeTyRUrJtc").build();

Track track = null;
try {
    track = request.get();
} catch (Exception e) {
    System.out.println(e.getMessage());
}

System.out.println(track);