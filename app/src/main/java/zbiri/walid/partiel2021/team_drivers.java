package zbiri.walid.partiel2021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zbiri.walid.partiel2021.api.ApiService;
import zbiri.walid.partiel2021.api.Driver;
import zbiri.walid.partiel2021.api.DriversResponse;
import zbiri.walid.partiel2021.api.Team;
import zbiri.walid.partiel2021.api.TeamsResponse;

public class team_drivers extends AppCompatActivity {

    private List<Driver> mDrivers;
    private RecyclerView mRecyclerView;
    private DriversAdapter mDriversAdapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_drivers);
        ApplicationContext app= (ApplicationContext) getApplicationContext();
        //change title of header
        setTitle(app.getTeam_Name()+" Drivers");
        ApiService apiService=new Retrofit.Builder()
                .baseUrl(ApiService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService.class);
        Call<DriversResponse> call=apiService.getTeamDrivers();
        mRecyclerView=findViewById(R.id.rvDrivers);
        mRecyclerView.setLayoutManager(new GridLayoutManager(context,1));

        call.enqueue(new Callback<DriversResponse>() {
            @Override
            public void onResponse(Call<DriversResponse> call, Response<DriversResponse> response) {
                DriversResponse repo_response = (DriversResponse) response.body();
                mDrivers=repo_response.getDrivers().stream()
                        .filter(d -> d.getDriver().getTeam_id() == app.getTeam_id()) // keep only projects of a
                        .collect(Collectors.toList());
                mDriversAdapter=new DriversAdapter(mDrivers);
                mRecyclerView.setAdapter(mDriversAdapter);
            }
            @Override
            public void onFailure(Call<DriversResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        t.getMessage()
                        ,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }


}