package dhbw.spotify;

import com.fasterxml.jackson.databind.ObjectMapper;
import dhbw.pojo.detail.album.DetailsAlbum;
import dhbw.pojo.detail.artist.DetailsArtist;
import dhbw.pojo.detail.track.DetailsTrack;
import dhbw.pojo.result.detail.DetailResult;

import java.io.IOException;

public class DetailsResponse {

    static ObjectMapper mapper = new ObjectMapper();

    public static String DetailResult(RequestCategory requestCategory, String id, String json) throws IOException {
        DetailResult dR = new DetailResult();
        switch (requestCategory) {
            case ALBUM:
                dR = album(id, json);
                break;

            case TRACK:
                dR = track(id, json);
                break;

            case ARTIST:
                dR = artist(id, json);
                break;
        }
        return mapper.writeValueAsString(dR);
    }

    public static DetailResult album(String id, String json) throws IOException {
        DetailsAlbum dAlb = mapper.readValue(json, DetailsAlbum.class);
        DetailResult dR1 = new DetailResult();
        dR1.setTitle(dAlb.getName());
        dR1.setInfo(dAlb.getType());
        return dR1;
    }

    public static DetailResult track(String id, String json) throws IOException {
        DetailsTrack dTra = mapper.readValue(json, DetailsTrack.class);
        DetailResult dR2 = new DetailResult();
        dR2.setTitle(dTra.getName());
        dR2.setInfo(dTra.getType());
        return dR2;
    }

    public static DetailResult artist(String id, String json) throws IOException {
        DetailsArtist dArt = mapper.readValue(json, DetailsArtist.class);
        DetailResult dR3 = new DetailResult();
        dR3.setTitle(dArt.getName());
        dR3.setInfo(dArt.getType());
        return dR3;
    }
}
