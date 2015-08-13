package com.nytimes.data.net;

import com.nytimes.data.entity.EmptyRequestResponse;
import com.nytimes.data.entity.RequestResult;
import com.nytimes.data.entity.Stringer;

import java.util.List;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by alexio on 8/9/15.
 */
public interface StringerService {

    @GET("/stringer.json")
    Observable<RequestResult> getStringer(@Query("id") String id);

    @GET("/search.json")
    Observable<List<Stringer>> getClosestStringers(@Query("long") float longitude, @Query("lat") float latitude);

    @POST("/name={name}&phone={phone}&email={email}&desk={desk}" +
            "&available={available}&baseLong={baseLong}&baseLat={baseLat}" +
            "&languages={languages}&skills={skills}&notes={notes")
    Observable createStringer(@Path("name") String name, @Path("phone") String phone,
                              @Path("email") String email, @Path("desk") String desk,
                              @Path("available") boolean available, @Path("baseLong") float baseLong,
                              @Path("baseLat") float baseLat, @Path("languages") String[] languages,
                              @Path("skills") String[] skills, @Path("notes") String notes);


    @POST("/available.json")
    Observable<EmptyRequestResponse> setAvailability(@Body Object dummyBody,
            @Query("id") String id, @Query("available") String available);

    @POST("/currentlocation.json")
    Observable<EmptyRequestResponse> setCurrentLocation(@Body Object dummyBody, @Query("id") String id, @Query("currentLong") String currentLong,
                                  @Query("currentLat") String currentLat);
}
