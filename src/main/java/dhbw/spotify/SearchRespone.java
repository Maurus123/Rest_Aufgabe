package dhbw.spotify;


import com.fasterxml.jackson.databind.ObjectMapper;
import dhbw.pojo.result.search.SearchResult;
import dhbw.pojo.result.search.SearchResultList;
import dhbw.pojo.search.album.Item;
import dhbw.pojo.search.album.SearchAlbum;
import dhbw.pojo.search.artist.SearchArtist;
import dhbw.pojo.search.track.SearchTrack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static dhbw.spotify.RequestCategory.TRACK;

public class SearchRespone {

    static ObjectMapper mapper = new ObjectMapper();

    public static SearchResult SearchResult(RequestCategory requestCategory, String json, String query) throws IOException {
        SearchResult result = new SearchResult();
        List<SearchResultList> list = new ArrayList<SearchResultList>();
        result.setSearchTerm(null);
        result.setSearchCategory(null);
        result.setResults(null);
        result.setSearchCategory(requestCategory.toString());
        result.setSearchTerm(query);

        switch (requestCategory) {
            case ALBUM:
                list = album(json);
                break;
            case TRACK:
                list = track(json);
                break;
            case ARTIST:
                list = artist(json);
                break;
        }


        result.setResults(list);
        return result;
    }

    public static List<SearchResultList> album(String json) throws IOException {
        List<SearchResultList> liste = new ArrayList<SearchResultList>();
        SearchAlbum sucheAlbum = mapper.readValue(json, SearchAlbum.class);
        for (Item a : sucheAlbum.getAlbums().getItems()) {
            SearchResultList sRl = new SearchResultList();
            sRl.setId(a.getId());
            sRl.setTitle(a.getName());
            sRl.setDescription(a.getType());
            sRl.setPlayLink(a.getUri());
            liste.add(sRl);
        }
        return liste;
    }

    public static List<SearchResultList> track(String json) throws IOException {
        SearchTrack searchTrack = mapper.readValue(json, SearchTrack.class);
        List<SearchResultList> liste1 = new ArrayList<SearchResultList>();
        for (dhbw.pojo.search.track.Item a : searchTrack.getTracks().getItems()) {
            SearchResultList sRl1 = new SearchResultList();
            sRl1.setId(a.getId());
            sRl1.setTitle(a.getName());
            sRl1.setDescription(a.getType());
            sRl1.setPlayLink(a.getUri());
            liste1.add(sRl1);
        }
        return liste1;
    }

    public static List<SearchResultList> artist(String json) throws IOException {
        SearchArtist searchArtist = mapper.readValue(json, SearchArtist.class);
        List<SearchResultList> liste2 = new ArrayList<SearchResultList>();
        for (dhbw.pojo.search.artist.Item a : searchArtist.getArtists().getItems()) {
            SearchResultList sRl2 = new SearchResultList();
            sRl2.setId(a.getId());
            sRl2.setTitle(a.getName());
            sRl2.setDescription(a.getType());
            sRl2.setPlayLink(a.getUri());
            liste2.add(sRl2);
        }
        return liste2;
    }
}
