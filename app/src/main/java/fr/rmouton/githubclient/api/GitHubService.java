package fr.rmouton.githubclient.api;

import java.util.List;

import fr.rmouton.githubclient.api.models.Repo;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface GitHubService {
    @GET("/users/{user}/repos")
    void listRepos(@Path("user") String user, Callback<List<Repo>> r);
}
