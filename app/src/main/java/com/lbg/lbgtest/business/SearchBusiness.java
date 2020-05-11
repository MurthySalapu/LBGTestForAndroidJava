package com.lbg.lbgtest.business;

import androidx.lifecycle.MutableLiveData;

import com.lbg.lbgtest.NetworkInterface.RetrofitHelper;
import com.lbg.lbgtest.apiInterface.SearchService;
import com.lbg.lbgtest.enums.SearchType;
import com.lbg.lbgtest.model.Album;
import com.lbg.lbgtest.model.AlbumImage;
import com.lbg.lbgtest.model.AlbumMatcher;
import com.lbg.lbgtest.model.Artist;
import com.lbg.lbgtest.model.ArtistMatcher;
import com.lbg.lbgtest.model.CommonResult;
import com.lbg.lbgtest.model.Result;
import com.lbg.lbgtest.model.SearchResult;
import com.lbg.lbgtest.model.Track;
import com.lbg.lbgtest.model.TrackMatcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Name - SearchBusiness
 * Purpose - This is to communicate with service and update the same back to viewmodel
 */
public class SearchBusiness {

    private MutableLiveData<List<CommonResult>> mCommonResult = new MutableLiveData<>();
    private MutableLiveData<String> mError = new MutableLiveData<>();

    private RetrofitHelper  mRetrofitHelper;

    public SearchBusiness(RetrofitHelper retrofitHelper) {
        this.mRetrofitHelper  = retrofitHelper;

    }


    /**
     * Make service call to fetch the search result
     * @param method - method to search
     * @param search - search text
     * @param searchType - type of search
     * @param apiKey - api key
     * @param format - format like json/xml
     */
    public void searchResult(String method, String search, final String searchType, String apiKey, String format){
        SearchService searchService = mRetrofitHelper.createService(SearchService.class);
        searchService.getSearchResult(getQueryMap(method,search,searchType,apiKey,format))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SearchResult searchResult) {
                        handleResult(searchResult.getResult(),searchType);
                    }

                    @Override
                    public void onError(Throwable e) {

                        handleError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /**
     * Process the Album result
     * @param albumMatcher -album matcher
     * @return - common data model
     */
    private List<CommonResult> processAlbumMatcher(AlbumMatcher albumMatcher){
        List<CommonResult> commonResultList = new ArrayList<>();
        List<Album> albums = albumMatcher.getAlbums();
        if(!albums.isEmpty()){
            for(Album album : albums) {
                List<AlbumImage> imageList = album.getImageList();
                CommonResult commonResult = new CommonResult(album.getName(),album.getUrl(),"",
                        album.getArtist(),imageList.get(1).getText(),0);
                commonResultList.add(commonResult);
            }
        }
        return commonResultList;
    }

    /**
     * Process thhe artist result
     * @param artistMatcher -artist matcher
     * @return - common data model
     */
    private List<CommonResult> processArtistMatcher(ArtistMatcher artistMatcher){
        List<CommonResult> commonResultList = new ArrayList<>();
        List<Artist> artists = artistMatcher.getArtist();
        if(!artists.isEmpty()){
            for(Artist artist : artists) {
                List<AlbumImage> imageList = artist.getImageList();
                CommonResult commonResult = new CommonResult(artist.getName(),artist.getUrl(),
                        artist.getListeners(),"",imageList.get(1).getText(),1);
                commonResultList.add(commonResult);
            }
        }
        return commonResultList;
    }

    /**
     * Process the track Matcher
     * @param trackMatcher - Track matcher
     * @return - common data model
     */
    private List<CommonResult> processTrackMatcher(TrackMatcher trackMatcher){
        List<CommonResult> commonResultList = new ArrayList<>();
        List<Track> tracks = trackMatcher.getTrack();
        if(!tracks.isEmpty()){
            for(Track track : tracks) {
                List<AlbumImage> imageList = track.getImageList();
                CommonResult commonResult = new CommonResult(track.getName(),track.getUrl(),
                        track.getListeners(),track.getArtist(),imageList.get(1).getText(),2);
                commonResultList.add(commonResult);
            }
        }
        return commonResultList;
    }

    /**
     * Handle the response from service call
     * @param result -  result
     */
    private void handleResult(Result result,String type){
         // process the result and put into common result to render it on UI
         SearchType searchType = SearchType.valueOf(type.toUpperCase());
         switch (searchType){
             case ALBUM:
                  mCommonResult.setValue(processAlbumMatcher(result.getAlbumMatcher()));
                 break;
             case ARTIST:
                 mCommonResult.setValue(processArtistMatcher(result.getArtistMatcher()));
                 break;
             case TRACK:
                 mCommonResult.setValue(processTrackMatcher(result.getTrackMatcher()));
                 break;

         }

    }

    /**
     * Handle error from service call
     * @param e - exception
     */
    private void handleError(Throwable e){

        mError.setValue(e.getMessage());
    }

    /**
     * Map all the option into Query map
     * @param method - method like album/artist/track.search
     * @param search - search query
     * @param searchType - type like album/artist/track
     * @param apiKey - api key
     * @param format - json/xml
     * @return - map
     */

    private Map getQueryMap(String method,String search,String searchType,String apiKey,String format){
        Map map = new HashMap<String,String>();
        map.put(SearchType.METHOD.getKey(),method+".search");
        map.put(searchType,search);
        map.put(SearchType.API_KEY.getKey(),apiKey);
        map.put(SearchType.FORMAT.getKey(),format);
        return map;
    }


    /**
     * Mutable live data for common result
     * @return
     */
    public MutableLiveData<List<CommonResult>> getCommonResult() {
        return mCommonResult;
    }

    /**
     *
     * @return
     */
    public MutableLiveData<String> getError() {
        return mError;
    }
}
