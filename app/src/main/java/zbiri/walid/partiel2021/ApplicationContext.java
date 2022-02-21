package zbiri.walid.partiel2021;

import android.app.Application;

public class ApplicationContext extends Application {
    private int team_id;
    private String team_Name;

    public String getTeam_Name() {
        return team_Name;
    }

    public void setTeam_Name(String setTeam_Name) {
        this.team_Name = setTeam_Name;
    }




    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    @Override
    public void onCreate(){
        super.onCreate();
    }
}
