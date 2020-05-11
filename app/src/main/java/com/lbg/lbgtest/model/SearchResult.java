package com.lbg.lbgtest.model;

import com.google.gson.annotations.SerializedName;

public class SearchResult {

    @SerializedName("results")
    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
