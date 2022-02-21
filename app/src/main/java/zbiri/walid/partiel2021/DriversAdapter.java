package zbiri.walid.partiel2021;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import zbiri.walid.partiel2021.api.Driver;
import zbiri.walid.partiel2021.api.Team;

public class DriversAdapter extends RecyclerView.Adapter<DriversAdapter.ViewHolder>{
    private List<Driver> mDriverList;

    public DriversAdapter(List<Driver> mDriverList) {
        this.mDriverList = mDriverList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView nameView;
        public TextView pointsView;

        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.driver_logo);
            this.nameView = (TextView) itemView.findViewById(R.id.name);
            this.pointsView = (TextView) itemView.findViewById(R.id.points);
        }

    }

    @NonNull
    @Override
    public DriversAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context= parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View repoView=inflater.inflate(R.layout.activity_driver_item,parent,false);
        return new ViewHolder(repoView,context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Driver repo =mDriverList.get(position);
        ImageView imageView=holder.imageView;
        holder.nameView.setText(repo.getDriver().getName());
        holder.pointsView.setText("Points: "+repo.getPoints());
        Picasso.get().load(repo.getDriver().getImage()).into(imageView);

    }

    @Override
    public int getItemCount() {
        return mDriverList.size();
    }


}
