package fr.rmouton.githubclient.api;


import java.util.List;

import fr.rmouton.githubclient.api.models.Photo;
import fr.rmouton.githubclient.api.models.Repo;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by dvil on 14/09/14.
 */
@SuppressWarnings("unused")
public interface GitHubService {

    @GET("/users/{user}/repos")
    void listRepos(@Path("user") String user, Callback<List<Repo>> r);

    @GET("/users/{user}/repos")
    Observable<List<Repo>> listRepos(@Path("user") String user);

    @GET("/user/{id}/photo")
    Observable<Photo> getUserPhoto(@Path("id") int id);

}
