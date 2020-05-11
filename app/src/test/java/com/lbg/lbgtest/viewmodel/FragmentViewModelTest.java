package com.lbg.lbgtest.viewmodel;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.lbg.lbgtest.business.SearchBusiness;
import com.lbg.lbgtest.common.AppSettings;
import com.lbg.lbgtest.enums.SearchType;
import com.lbg.lbgtest.model.CommonResult;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class FragmentViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Mock
    SearchBusiness mSearchBusiness;

    private TestFMFragmentViewModel mViewModel;
    private MutableLiveData<List<CommonResult>> mCommonResult = new MutableLiveData<>();
    private MutableLiveData<String> mError = new MutableLiveData<>();


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mViewModel = new TestFMFragmentViewModel();
        when(mSearchBusiness.getCommonResult()).thenReturn(mCommonResult);
        when(mSearchBusiness.getError()).thenReturn(mError);
        mViewModel.setUp(mSearchBusiness);
        Assert.assertEquals(false,mViewModel.loadView.getValue());
    }

    /**
     * Test onSearchTextChanged is called and see the value is set to mutable object.
     */
    @Test
    public void testOnSearchTextChanged(){
         mViewModel.onSearchTextChanged("Hello");
         Assert.assertEquals("Hello",mViewModel.getQuery());
    }

    /**
     * Test onSelectItem is called and see the value is set to internal object.
     */
    @Test
    public void testOnSelectionItem(){
        mViewModel.onSelectItem(null,null,1,0);
        Assert.assertEquals(1,mViewModel.getSelectedPos());
    }

    /**
     * Verify the search result service call is called
     */
    @Test
    public void testOnSearchClicked(){
        mViewModel.setQuery("album");
        mViewModel.setSelectedPos(0);
        mViewModel.onSearchClicked();
        verify(mSearchBusiness).searchResult(SearchType.ALBUM.getKey(),"album",SearchType.ALBUM.getKey(),
                AppSettings.API_KEY,AppSettings.FORMAT);

    }

    /**
     * Search should be called if query is empty
     */
    @Test
    public void testOnSearchClickedIfQueryNull(){
        mViewModel.setQuery("");
        mViewModel.setSelectedPos(0);
        mViewModel.onSearchClicked();
        verify(mSearchBusiness,never()).searchResult(SearchType.ALBUM.getKey(),"album",SearchType.ALBUM.getKey(),
                AppSettings.API_KEY,AppSettings.FORMAT);
    }

    /**
     * Test the subscriber for success
     */
    @Test
    public void testSubscriberForSuccess(){
        CommonResult commonResult = new CommonResult("Cher","","12345","Myth","",0);
        List<CommonResult> commonResultList = new ArrayList<>();
        commonResultList.add(commonResult);
        mCommonResult.setValue(commonResultList);
        List<CommonResult> result = mViewModel.getCommonResult().getValue();
        CommonResult cmResult = result.get(0);
        Assert.assertEquals("12345",cmResult.getListeners());

    }

    @Test
    public void testSubscribeForError(){
        mError.setValue("IO Exception");
        String error = mViewModel.getError().getValue();
        Assert.assertEquals("IO Exception",error);
    }

}
