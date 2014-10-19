package fr.rmouton.githubclient.api.events;

import java.util.List;

import fr.rmouton.githubclient.api.models.Repo;

public class ReposResponseEvent {

    private final List<Repo> mRepos;

    public ReposResponseEvent(List<Repo> r) {
        this.mRepos = r;
    }

    public List<Repo> getRepos() {
        return mRepos;
    }
}

