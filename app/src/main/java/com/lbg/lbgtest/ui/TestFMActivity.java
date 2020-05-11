package com.lbg.lbgtest.ui;


import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.lbg.lbgtest.BR;
import com.lbg.lbgtest.R;
import com.lbg.lbgtest.databinding.ActivityMainBinding;
import com.lbg.lbgtest.ui.fragments.TestFMFragment;
import com.lbg.lbgtest.viewmodel.TestFMActivityViewModel;
import com.lbg.lbgtest.viewmodel.ViewModelProviderFactory;


/**
 * Name - Test FM Activity
 * Purpose - Home Page to attach the fragments
 */
public class TestFMActivity extends BaseActivity<ActivityMainBinding, TestFMActivityViewModel> {


    private TestFMActivityViewModel mViewModel;
    private ActivityMainBinding mActivityMainBinding;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public TestFMActivityViewModel getViewModel() {
        ViewModelProvider.Factory viewModelProviderFactory = new ViewModelProviderFactory<>(new TestFMActivityViewModel());
        mViewModel = ViewModelProviders.of(this,viewModelProviderFactory).get(TestFMActivityViewModel.class);
        return mViewModel;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = getViewDataBinding();
        launchFragment();
    }

    /**
     * Launch Fragment
     */
    private void launchFragment(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.container,new TestFMFragment());
        fragmentTransaction.commit();
    }
}
