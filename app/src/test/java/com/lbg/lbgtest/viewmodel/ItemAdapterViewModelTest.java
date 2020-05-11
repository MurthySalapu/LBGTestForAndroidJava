package com.lbg.lbgtest.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.lbg.lbgtest.model.CommonResult;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(JUnit4.class)
public class ItemAdapterViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    private ItemAdapterViewModel mViewModel;
    @Before
    public void setUp(){
        mViewModel = new ItemAdapterViewModel();
    }

    /**
     * Test the onCard Item clicked method to set the url to mutable live data.
     */
    @Test
    public void testOnCardItemClicked(){
        mViewModel.onCardItemClicked("https://www.google.com");
        assertEquals("https://www.google.com",mViewModel.getUrl().getValue());
    }

    /**
     * This is for getting the right data based on the search type
     */
    @Test
    public void testGetDataForAlbum(){
        CommonResult commonResult = new CommonResult("cher","https://url","12345","Bob","",0);
        assertEquals(mViewModel.getData(commonResult),"Artist:Bob");
    }

    /**
     * This is for getting the right data based on the search type
     */
    @Test
    public void testGetDataForArtist(){
        CommonResult commonResult = new CommonResult("cher","https://url","12345","Bob","",1);
        assertEquals(mViewModel.getData(commonResult),"Listeners:12345");
    }

    /**
     * This is for getting the right data based on the search type
     */
    @Test
    public void testGetDataForTrack(){
        CommonResult commonResult = new CommonResult("cher","https://url","12345","Bob","",2);
        assertEquals(mViewModel.getData(commonResult),"Listeners:12345");
    }

    /**
     * This is for getting the right data based on the search type
     */
    @Test
    public void testGetDataForNothing(){
        CommonResult commonResult = new CommonResult("cher","https://url","12345","Bob","",3);
        assertNull(mViewModel.getData(commonResult));
    }
}
