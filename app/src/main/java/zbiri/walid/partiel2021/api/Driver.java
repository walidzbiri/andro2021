package zbiri.walid.partiel2021.api;

public class Driver {
    private int position;
    private String points;
    private DriverDetails driver;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public DriverDetails getDriver() {
        return driver;
    }

    public void setDriver(DriverDetails driver) {
        this.driver = driver;
    }
}
