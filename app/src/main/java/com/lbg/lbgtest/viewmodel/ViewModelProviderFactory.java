package com.lbg.lbgtest.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * View Model Provider Factory
 * @param <V>
 */

public class ViewModelProviderFactory<V> implements ViewModelProvider.Factory {

    private final V viewmModel;

    public ViewModelProviderFactory(V viewmModel) {
        this.viewmModel = viewmModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(viewmModel.getClass())){
            return  (T)viewmModel;
        }
        throw  new NullPointerException("Unknown class name");
    }
}
