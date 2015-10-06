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

import fr.rmouton.githubclient.api.GitHubService;
import fr.rmouton.githubclient.api.models.Repo;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class GitHubRepos extends Activity {

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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService service = retrofit.create(GitHubService.class);

        Call<List<Repo>> call = service.listRepos(NAME);
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Response<List<Repo>> response, Retrofit retrofit) {
                mAdaper.setData(response.body());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        super.onResume();
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
