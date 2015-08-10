package com.nytimes.data.net;

import com.nytimes.data.entity.RequestResult;
import com.nytimes.data.entity.Stringer;

import java.util.List;

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

    @GET("/search.json?long={long}&lat={lat}")
    Observable<List<Stringer>> getClosestStringers(@Path("long") float longitude, @Query("lat") float latitude);

    @POST("/name={name}&phone={phone}&email={email}&desk={desk}" +
            "&available={available}&baseLong={baseLong}&baseLat={baseLat}" +
            "&languages={languages}&skills={skills}&notes={notes")
    Observable createStringer(@Path("name") String name, @Path("phone") String phone,
                              @Path("email") String email, @Path("desk") String desk,
                              @Path("available") boolean available, @Path("baseLong") float baseLong,
                              @Path("baseLat") float baseLat, @Path("languages") String[] languages,
                              @Path("skills") String[] skills, @Path("notes") String notes);


    @POST("/available.json?id={id}&available={available}")
    Observable setAvailability(@Path("id") String id, @Path("available") boolean available);

    @POST("/currentlocation.json?id={id}&currentLong={currentLong}&currentLat={currantLat}")
    Observable setCurrentLocation(@Path("id") String id, @Path("currentLong") float currentLong,
                                  @Path("currentLat") float currentLat);
}
