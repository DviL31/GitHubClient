package fr.rmouton.githubclient;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import de.greenrobot.event.EventBus;
import fr.rmouton.githubclient.api.events.LoadReposEvent;
import fr.rmouton.githubclient.api.events.ReposResponseEvent;
import fr.rmouton.githubclient.api.models.Repo;


public class GitHubReposActivity extends Activity {

    private static final String NAME = "square";

    private ListView mListView;
    private ReposAdapter mAdaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_git_hub_repos);

        mListView = (ListView) findViewById(android.R.id.list);

        getActionBar().setTitle(NAME);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.git_hub_repos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {

        mAdaper = new ReposAdapter(this, android.R.layout.simple_list_item_1);
        mListView.setAdapter(mAdaper);

        EventBus.getDefault().register(this);
        EventBus.getDefault().post(new LoadReposEvent(NAME));

        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(ReposResponseEvent event) {
        mAdaper.setData(event.getRepos());
    }

    public class ReposAdapter extends ArrayAdapter<Repo> {

        public ReposAdapter(Context context, int resource) {
            super(context, resource);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = View.inflate(getContext(), android.R.layout.simple_list_item_1, null);

            TextView name = (TextView) view.findViewById(android.R.id.text1);
            name.setText(getItem(position).name);
            return view;
        }

        public void setData(List<Repo> r) {
            clear();
            addAll(r);
            notifyDataSetChanged();
        }
    }
}
