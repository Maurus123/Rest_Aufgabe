package dhbw.spotify;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.util.Optional;

import static dhbw.spotify.DetailsResponse.DetailResult;
import static dhbw.spotify.RequestType.DETAIL;

@RestController
public class DetailsWebservice {
    public SpotifyRequest sR = new SpotifyRequest(DETAIL);

    @RequestMapping("/detail/{id}")
    public String detailResult(@PathVariable String id, @RequestParam("type")RequestCategory requestCategory) throws WrongRequestTypeException, IOException {
        Optional<String> o = sR.performeRequestDetail(requestCategory, id);
        String json = null;
        if (o.isPresent()) {
            json = o.get();
        }
        return DetailResult(requestCategory, id, json);
    }
}
