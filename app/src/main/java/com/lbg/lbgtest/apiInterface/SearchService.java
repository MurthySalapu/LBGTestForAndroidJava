package com.lbg.lbgtest.apiInterface;

import com.lbg.lbgtest.model.SearchResult;

import java.util.Map;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Interface to interact with search service
 */
public interface SearchService {

    @GET(".")
    Observable<SearchResult> getSearchResult(@QueryMap Map<String, String> options);

}
