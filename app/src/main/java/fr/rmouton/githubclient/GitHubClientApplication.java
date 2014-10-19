package fr.rmouton.githubclient;

import android.app.Application;

import com.orm.SugarApp;

import de.greenrobot.event.EventBus;
import fr.rmouton.githubclient.api.GitHubClientManager;
import fr.rmouton.githubclient.api.GitHubService;
import retrofit.RestAdapter;

public class GitHubClientApplication extends SugarApp {

    private GitHubClientManager mGitHubManager;
    private EventBus mBus;

    @Override
    public void onCreate() {
        super.onCreate();

        mBus = EventBus.getDefault();

        mGitHubManager = new GitHubClientManager(buildApi(), mBus);
        mBus.register(mGitHubManager);
    }

    private GitHubService buildApi() {
        return new RestAdapter.Builder()
                .setEndpoint("https://api.github.com")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build().create(GitHubService.class);
    }
}
