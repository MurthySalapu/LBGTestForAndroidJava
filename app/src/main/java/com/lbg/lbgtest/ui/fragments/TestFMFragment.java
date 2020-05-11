package com.lbg.lbgtest.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lbg.lbgtest.BR;
import com.lbg.lbgtest.NetworkInterface.RetrofitHelper;
import com.lbg.lbgtest.R;
import com.lbg.lbgtest.business.SearchBusiness;
import com.lbg.lbgtest.databinding.FragmentHomeBinding;
import com.lbg.lbgtest.model.CommonResult;
import com.lbg.lbgtest.ui.WebViewActivity;
import com.lbg.lbgtest.viewmodel.ItemAdapterViewModel;
import com.lbg.lbgtest.viewmodel.TestFMFragmentViewModel;
import com.lbg.lbgtest.viewmodel.ViewModelProviderFactory;

import java.util.List;

/**
 * Name - Test FM Fragment
 * Purpose - Search the query and load the data that gets on query
 */

public class TestFMFragment extends BaseFragment<FragmentHomeBinding, TestFMFragmentViewModel> implements ItemViewAdapter.OnItemClickListener {

    private TestFMFragmentViewModel mViewModel;

    private ItemViewAdapter mItemViewAdapter;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public TestFMFragmentViewModel getViewModel() {
        ViewModelProvider.Factory viewModelProviderFactory = new ViewModelProviderFactory<>(new TestFMFragmentViewModel());
        mViewModel = ViewModelProviders.of(this,viewModelProviderFactory).get(TestFMFragmentViewModel.class);

        return mViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel.setUp(new SearchBusiness(new RetrofitHelper()));
        subscribeObserver();
    }

    /**
     * Subscribe observer to listen the events
     */
    private void subscribeObserver(){
        mViewModel.getCommonResult().observe(this, new Observer<List<CommonResult>>() {
            @Override
            public void onChanged(List<CommonResult> commonResult) {
                loadAdapter(commonResult);
            }
        });
        mViewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
              displayToast(s);
            }
        });
    }

    /**
     * Display toast for error
     * @param error - error
     */
    private void displayToast(String error){
        Toast.makeText(getBaseActivity(),"Error:"+error
        ,Toast.LENGTH_LONG).show();
    }

    /**
     * load the adapter with content
     * @param commonResults - commonResults
     */
    private void loadAdapter(List<CommonResult> commonResults){
        if(mItemViewAdapter == null){
            mItemViewAdapter = new ItemViewAdapter(R.layout.item_view,commonResults,new ItemAdapterViewModel(),this);
            getViewDataBinding().recyclerView.setAdapter(mItemViewAdapter);
            getViewDataBinding().recyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        }else{
            mItemViewAdapter.setDataSet(commonResults);
            mItemViewAdapter.notifyDataSetChanged();
        }
    }

    /**
     * Launch web view activity for url
     * @param url - url
     */
    @Override
    public void onClicked(String url) {
        //launch web view to see the content
        if(!TextUtils.isEmpty(url)){
            Intent webIntent = new Intent(getBaseActivity(), WebViewActivity.class);
            webIntent.putExtra("url",url);
            startActivity(webIntent);
        }
    }

    @BindingAdapter("profileImage")
    public static void loadImage(ImageView view, String imageUrl) {
        if(!TextUtils.isEmpty(imageUrl)) {
            Glide.with(view.getContext())
                    .load(imageUrl).apply(new RequestOptions().circleCrop())
                    .into(view);
        }
    }
}
