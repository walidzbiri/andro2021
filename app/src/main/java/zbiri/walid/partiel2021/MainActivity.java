package zbiri.walid.partiel2021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zbiri.walid.partiel2021.api.ApiService;
import zbiri.walid.partiel2021.api.Team;
import zbiri.walid.partiel2021.api.TeamDetails;
import zbiri.walid.partiel2021.api.TeamsResponse;

public class MainActivity extends AppCompatActivity implements TeamsAdapter.OnTeamListener{

    private List<Team> mTeams;
    private RecyclerView mRecyclerView;
    private TeamsAdapter mTeamsAdapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ApplicationContext app= (ApplicationContext) getApplicationContext();
        setTitle("Formula 1 teams");
        ApiService apiService=new Retrofit.Builder()
                .baseUrl(ApiService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService.class);
        Call<TeamsResponse> call=apiService.getAllTeams();
        mRecyclerView=findViewById(R.id.rvTeams);
        mRecyclerView.setLayoutManager(new GridLayoutManager(context,1));

        call.enqueue(new Callback<TeamsResponse>() {
            @Override
            public void onResponse(Call<TeamsResponse> call, Response<TeamsResponse> response) {
                TeamsResponse repo_response = (TeamsResponse) response.body();
                mTeams=repo_response.getTeams();
                mTeamsAdapter=new TeamsAdapter(context,mTeams,MainActivity.this);
                mRecyclerView.setAdapter(mTeamsAdapter);
            }
            @Override
            public void onFailure(Call<TeamsResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        t.getMessage()
                        ,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void OnTeamClick(int position) {
        Intent intent=new Intent(this,team_drivers.class);
        ApplicationContext app=(ApplicationContext) getApplicationContext();
        app.setTeam_id(mTeams.get(position).getTeam().getId());
        app.setTeam_Name(mTeams.get(position).getTeam().getName());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        MenuItem searchItem=menu.findItem(R.id.search);
        SearchView searchView=(SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mTeamsAdapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }
}