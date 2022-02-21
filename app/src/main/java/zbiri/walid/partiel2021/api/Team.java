package zbiri.walid.partiel2021.api;



public class Team {
    private int position;
    private int points;
    private TeamDetails team;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }


    public TeamDetails getTeam() {
        return team;
    }

    public void setTeam(TeamDetails team) {
        this.team = team;
    }

}
