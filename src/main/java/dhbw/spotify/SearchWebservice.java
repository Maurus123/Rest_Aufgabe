package dhbw.spotify;

import dhbw.pojo.result.search.SearchResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

import static dhbw.spotify.RequestType.SEARCH;
import static dhbw.spotify.SearchRespone.SearchResult;

@RestController
public class SearchWebservice {

    public SpotifyRequest sR = new SpotifyRequest(SEARCH);

    @RequestMapping("/search")
    public SearchResult suche (@RequestParam("query") String query, @RequestParam("type")RequestCategory requestCategory) throws WrongRequestTypeException, IOException {
        Optional<String> o = sR.performeRequestSearch(requestCategory, query);
        String json = null;
        if (o.isPresent()) {
            json = o.get();
        }
        return SearchResult(requestCategory, json, query);
    }



}
