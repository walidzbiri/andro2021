package zbiri.walid.partiel2021.api;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DriversResponse {

    @SerializedName("drivers")
    @Expose
    private List<Driver> drivers;

    public List<Driver> getDrivers() {
        return drivers;
    }

    @NonNull
    @Override
    public String toString() {
        return drivers.toString();
    }


}
