package fr.rmouton.githubclient.api;


import java.util.List;

import fr.rmouton.githubclient.api.models.Repo;
import fr.rmouton.githubclient.api.models.RequestBody;
import retrofit.Callback;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by dvil on 14/09/14.
 */
public interface GitHubService {

    @GET("/users/{user}/repos")
    void listRepos(@Path("user") String user, Callback<List<Repo>> r);

    @Headers("Super-Power: true")
    @GET("/heaven/{door}?gender=male")
    void god(@Header("Authorization") String token,
             @Path("door") int door,
             @Query("age") int age,
             Callback<Void> c);

    @Multipart
    @PUT("/user/photo")
    void uploadAvatar(@Part("photo") RequestBody photo, @Part("description") RequestBody description, Callback<Void> c);

    @FormUrlEncoded
    @POST("/user/edit")
    void updateUser(@Field("first_name") String first, @Field("last_name") String last);

    @DELETE("/deleteIngredient")
    void delIngredient(@Query("recipe_id") int id, @Query("recipe_name") String name, @Query("time") long timestamp);

}
