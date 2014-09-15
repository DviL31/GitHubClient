package fr.rmouton.githubclient.api;

import java.util.List;

import de.greenrobot.event.EventBus;
import fr.rmouton.githubclient.api.events.LoadReposEvent;
import fr.rmouton.githubclient.api.events.ReposResponseEvent;
import fr.rmouton.githubclient.api.models.Repo;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class GitHubClientManager {

    private GitHubService mApi;
    private EventBus mBus;

    public GitHubClientManager(GitHubService api, EventBus bus) {
        mApi = api;
        mBus = bus;
    }


    public void onEvent(LoadReposEvent event) {

        mApi.listRepos(event.getUser(), new Callback<List<Repo>>() {
            @Override
            public void success(List<Repo> repo, Response response) {
               mBus.post(new ReposResponseEvent(repo));
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });
    }

}
