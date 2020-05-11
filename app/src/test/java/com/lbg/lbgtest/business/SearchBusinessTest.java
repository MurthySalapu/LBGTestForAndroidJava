package com.lbg.lbgtest.business;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.lbg.lbgtest.NetworkInterface.RetrofitHelper;
import com.lbg.lbgtest.apiInterface.SearchService;
import com.lbg.lbgtest.common.AppSettings;
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

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class SearchBusinessTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();


    @Mock
    RetrofitHelper mRetrofitHelper;

    @Mock
    SearchService mSearchService;

    SearchBusiness mSearchBusiness;

    /**
     * Initialize the io and main thread scheduler for Rx java an Rx Android
     */
    @BeforeClass
    public static void setupClass() {

        RxJavaPlugins.setIoSchedulerHandler(
                new Function<Scheduler, Scheduler>() {
                    @Override
                    public Scheduler apply(Scheduler scheduler) throws Exception {
                        return Schedulers.trampoline();
                    }
                });
        RxJavaPlugins.setComputationSchedulerHandler(
                new Function<Scheduler, Scheduler>() {
                    @Override
                    public Scheduler apply(Scheduler scheduler) throws Exception {
                        return Schedulers.trampoline();
                    }
                });
        RxJavaPlugins.setNewThreadSchedulerHandler(
                new Function<Scheduler, Scheduler>() {
                    @Override
                    public Scheduler apply(Scheduler scheduler) throws Exception {
                        return Schedulers.trampoline();
                    }
                });
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(
                new Function<Callable<Scheduler>, Scheduler>() {
                    @Override
                    public Scheduler apply(Callable<Scheduler> __) throws Exception {
                        return Schedulers.trampoline();
                    }
                });
    }

    /**
     * Initial set up
     */
    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
        mSearchBusiness = new SearchBusiness(mRetrofitHelper);

    }

    /**
     * Test search result network  call and verify the  Album matcher result
     */
    @Test
    public void testSearchResultOnSuccessForAlbum(){
        SearchResult searchResult = new SearchResult();
        Result result = new Result();
        AlbumMatcher albumMatcher = new AlbumMatcher();
        List<Album> albumList = new ArrayList<>();
        List<AlbumImage> albumImageList = new ArrayList<>();
        AlbumImage albumImage1 = new AlbumImage();
        albumImage1.setSize("size1");
        albumImage1.setText("Text1");
        AlbumImage albumImage2 = new AlbumImage();
        albumImage2.setSize("size2");
        albumImage2.setText("Text2");
        albumImageList.add(albumImage1);
        albumImageList.add(albumImage2);
        Album album = new Album();
        album.setArtist("Artist");
        album.setName("Name");
        album.setUrl("Url");
        album.setImageList(albumImageList);
        albumList.add(album);
        albumMatcher.setAlbums(albumList);
        result.setAlbumMatcher(albumMatcher);
        searchResult.setResult(result);
        when(mRetrofitHelper.createService(SearchService.class)).thenReturn(mSearchService);
        when(mSearchService.getSearchResult(Mockito.<String, String>anyMap())).thenReturn(Observable.just(searchResult));
        mSearchBusiness.searchResult(SearchType.ALBUM.getKey(),"Test",SearchType.ALBUM.getKey(), AppSettings.API_KEY,AppSettings.FORMAT);
        List<CommonResult> commonResultList = mSearchBusiness.getCommonResult().getValue();
        Assert.assertEquals(1,commonResultList.size());

    }



    /**
     * Test search result network  call and verify the Artist  result
     */
    @Test
    public void testSearchResultOnSuccessForArtist(){
        SearchResult searchResult = new SearchResult();
        Result result = new Result();

        ArtistMatcher artistMatcher = new ArtistMatcher();


        List<Artist> artistList = new ArrayList<>();
        List<AlbumImage> albumImageList = new ArrayList<>();
        AlbumImage albumImage1 = new AlbumImage();
        albumImage1.setSize("size1");
        albumImage1.setText("Text1");
        AlbumImage albumImage2 = new AlbumImage();
        albumImage2.setSize("size2");
        albumImage2.setText("Text2");
        albumImageList.add(albumImage1);
        albumImageList.add(albumImage2);
        Artist artist = new Artist();
        artist.setListeners("12345");
        artist.setName("Name");
        artist.setUrl("Url");
        artist.setImageList(albumImageList);
        artistList.add(artist);
        artistMatcher.setArtist(artistList);
        result.setArtistMatcher(artistMatcher);
        searchResult.setResult(result);
        when(mRetrofitHelper.createService(SearchService.class)).thenReturn(mSearchService);
        when(mSearchService.getSearchResult(Mockito.<String, String>anyMap())).thenReturn(Observable.just(searchResult));
        mSearchBusiness.searchResult(SearchType.ALBUM.getKey(),"Test",SearchType.ARTIST.getKey(), AppSettings.API_KEY,AppSettings.FORMAT);
        List<CommonResult> commonResultList = mSearchBusiness.getCommonResult().getValue();
        Assert.assertEquals(1,commonResultList.size());

    }

    /**
     * Test search result network  call and verify the Track  result
     */
    @Test
    public void testSearchResultOnSuccessForTrack(){
        SearchResult searchResult = new SearchResult();
        Result result = new Result();

        TrackMatcher trackMatcher = new TrackMatcher();


        List<Track> trackList = new ArrayList<>();
        List<AlbumImage> albumImageList = new ArrayList<>();
        AlbumImage albumImage1 = new AlbumImage();
        albumImage1.setSize("size1");
        albumImage1.setText("Text1");
        AlbumImage albumImage2 = new AlbumImage();
        albumImage2.setSize("size2");
        albumImage2.setText("Text2");
        albumImageList.add(albumImage1);
        albumImageList.add(albumImage2);
        Track track = new Track();
        track.setListeners("12345");
        track.setName("Name");
        track.setUrl("Url");
        track.setImageList(albumImageList);
        trackList.add(track);
        trackMatcher.setTrack(trackList);
        result.setTrackMatcher(trackMatcher);
        searchResult.setResult(result);
        when(mRetrofitHelper.createService(SearchService.class)).thenReturn(mSearchService);
        when(mSearchService.getSearchResult(Mockito.<String, String>anyMap())).thenReturn(Observable.just(searchResult));
        mSearchBusiness.searchResult(SearchType.ALBUM.getKey(),"Test",SearchType.TRACK.getKey(), AppSettings.API_KEY,AppSettings.FORMAT);
        List<CommonResult> commonResultList = mSearchBusiness.getCommonResult().getValue();
        Assert.assertEquals(1,commonResultList.size());

    }

    @Test
    public void testSearchResultOnFailure(){
        Exception exception = new Exception("Server Down.Please try again");
        when(mRetrofitHelper.createService(SearchService.class)).thenReturn(mSearchService);
        when(mSearchService.getSearchResult(Mockito.<String, String>anyMap())).thenReturn(Observable.<SearchResult>error(exception));
        mSearchBusiness.searchResult(SearchType.ALBUM.getKey(),"Test",SearchType.ALBUM.getKey(), AppSettings.API_KEY,AppSettings.FORMAT);
        String error = mSearchBusiness.getError().getValue();
        Assert.assertEquals("Server Down.Please try again",error);
    }
}
