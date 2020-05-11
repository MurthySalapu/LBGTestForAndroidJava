package com.lbg.lbgtest.viewmodel;


import android.view.View;
import android.widget.AdapterView;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.lbg.lbgtest.business.SearchBusiness;
import com.lbg.lbgtest.common.AppSettings;
import com.lbg.lbgtest.enums.SearchType;
import com.lbg.lbgtest.model.CommonResult;

import java.util.List;

/**
 * Name - Test FM Fragment view model
 * Purpose - bind the search result to view
 */

public class TestFMFragmentViewModel extends BaseViewModel{

    private SearchBusiness mSearchBusiness;
    private MutableLiveData<List<CommonResult>> mCommonResult = new MutableLiveData<>();
    private MutableLiveData<String> mError = new MutableLiveData<>();
    private String mQuery = "";
    private int mSelectedPos = 0;

    public MutableLiveData<Boolean> loadView = new MutableLiveData<>();

    public void setUp(SearchBusiness searchBusiness){
        this.mSearchBusiness = searchBusiness;
        loadView.setValue(false);
        subscribeObserver();
    }

    public void onSearchTextChanged(CharSequence query) {
       this.mQuery = query.toString();
    }

    public void onSelectItem(AdapterView<?> parent, View view, int pos, long id)
    {
        mSelectedPos = pos;
    }

    public void onSearchClicked(){
       // call search service call.
        if(isStringEmpty(mQuery)) {
            loadView.setValue(true);

            String type = getMethod(mSelectedPos);
            callSearchService(type, mQuery, type, AppSettings.API_KEY, AppSettings.FORMAT);
        }
    }

    private String getMethod(int pos){
        switch (pos){
            case 0: return SearchType.ALBUM.getKey();
            case 1:return SearchType.ARTIST.getKey();
            case 2:return SearchType.TRACK.getKey();
        }
        return null;
    }


  private boolean isStringEmpty(String str){
      return str != null && !str.equals("");

  }

    /**
     * subscribe observer to listen the events
     */
    private void subscribeObserver(){
        mSearchBusiness.getCommonResult().observeForever(new Observer<List<CommonResult>>() {
            @Override
            public void onChanged(List<CommonResult> commonResult) {
                if(commonResult != null) {
                    loadView.setValue(false);
                    mCommonResult.setValue(commonResult);
                    mSearchBusiness.getCommonResult().setValue(null);
                }
            }
        });
        mSearchBusiness.getError().observeForever(new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(isStringEmpty(s)){
                    loadView.setValue(false);
                    mError.setValue(s);
                    mSearchBusiness.getError().setValue(null);
                }
            }
        });
    }

    /**
     * call search service
     * @param method - method like album/artist/track.search
     * @param query - search text
     * @param type - album/artist/track
     * @param apiKey - api key
     * @param format - json/xml
     */
    public void callSearchService(String method,String query,String type,String apiKey,String format){
        mSearchBusiness.searchResult(method,query,type,apiKey,format);
    }

    /**
     * Mutable live data object
     * @return
     */
    public MutableLiveData<List<CommonResult>> getCommonResult() {
        return mCommonResult;
    }

    public MutableLiveData<String> getError() {
        return mError;
    }

    public String getQuery() {
        return mQuery;
    }

    public int getSelectedPos() {
        return mSelectedPos;
    }

    public void setQuery(String mQuery) {
        this.mQuery = mQuery;
    }

    public void setSelectedPos(int mSelectedPos) {
        this.mSelectedPos = mSelectedPos;
    }
}
