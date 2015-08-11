package com.nytimes.data.net;

import com.nytimes.data.entity.EmptyRequestResponse;
import com.nytimes.data.entity.RequestResult;
import com.nytimes.data.entity.Stringer;

import java.util.List;

import retrofit.RestAdapter;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by alexio on 8/9/15.
 */
public class StringerApiClient {

    private static final String API_BASE_URL = "http://ec2-54-163-158-107.compute-1.amazonaws.com/api";
    private final StringerService stringerService;

    public StringerApiClient() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_BASE_URL)
                .build();

        stringerService = restAdapter.create(StringerService.class);
    }

    public Observable<Stringer> getStringer(final String id) {
        return stringerService.getStringer(id)
                .map(new Func1<RequestResult, Stringer>() {
                    @Override
                    public Stringer call(RequestResult requestResult) {
                        return requestResult.results;
                    }
                });
    }

    public Observable<List<Stringer>> getClosestStringers(float longtitude, float latitude) {
        return stringerService.getClosestStringers(longtitude, latitude);
    }

//    public Observable createStringer() {
//        return stringerService.createStringer();
//    }

    public Observable<EmptyRequestResponse> setAvailability(String id, boolean available) {
        return stringerService.setAvailability(new Object(), id, Boolean.toString(available));
    }

    public Observable setCurrentLocation(String id, float currentLong, float currentLat) {
        return stringerService.setCurrentLocation(id, currentLong, currentLat);
    }
}
