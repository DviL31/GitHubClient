package fr.rmouton.githubclient.api.events;

public class LoadReposEvent {

    private final String mUser;

    public LoadReposEvent(String user) {
        this.mUser = user;
    }

    public String getUser() {
        return mUser;
    }
}

