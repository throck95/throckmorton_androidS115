package com.example.tyler.finalproject;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;

import java.util.Map;

/**
 * Created by Tyler on 12/10/2015.
 */
public class BeverageRequest extends Request<String> {
    private Map<String, String> mParams;
    private Response.Listener<String> mListener;

    public BeverageRequest(Map<String,String> map, int method, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, "http://tyler.finalproject.dennisiscool.tech/beverages/insert/beer", errorListener);
        mListener = listener;
        mParams = map;
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        return null;
    }

    @Override
    protected void deliverResponse(String response) {

    }

    public Map<String,String> getmParams() {
        return mParams;
    }
}
