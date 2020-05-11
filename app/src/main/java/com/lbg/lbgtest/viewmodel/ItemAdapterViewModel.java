package com.lbg.lbgtest.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.lbg.lbgtest.model.CommonResult;

/**
 * Name - Item Adapter view model
 * Purpose - Bind the data to View
 */
public class ItemAdapterViewModel extends BaseViewModel {

    private MutableLiveData<String> mUrl= new MutableLiveData<>();


    public void onCardItemClicked(String url){
        mUrl.setValue(url);
    }

    public MutableLiveData<String> getUrl() {
        return mUrl;
    }



    public String getData(CommonResult dataModel){
         switch (dataModel.getType()){
             case 0:return  "Artist:"+dataModel.getArtist();
             case 1:
             case 2:return  "Listeners:"+dataModel.getListeners();
         }
         return null;
    }
}
