package zbiri.walid.partiel2021;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import zbiri.walid.partiel2021.api.Team;
import zbiri.walid.partiel2021.api.TeamDetails;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.ViewHolder> implements Filterable {

    private List<Team> mTeamList;
    private List<Team> mTeamListFull;

    private OnTeamListener onTeamListener;
    private Context myActivity;


    public TeamsAdapter(Context context,List<Team> mTeamList,OnTeamListener onTeamListener) {
        this.mTeamList = mTeamList;
        this.mTeamListFull=new ArrayList<>(mTeamList);

        this.myActivity=context;
        this.onTeamListener=onTeamListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView logoView;
        public OnTeamListener onTeamListener;

        public ViewHolder(@NonNull View itemView,Context context,OnTeamListener onTeamListener) {
            super(itemView);
            this.logoView = (ImageView) itemView.findViewById(R.id.team_logo);
            this.onTeamListener=onTeamListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onTeamListener.OnTeamClick(getAdapterPosition());
        }

    }

    @NonNull
    @Override
    public TeamsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context= parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View repoView=inflater.inflate(R.layout.activity_team_item,parent,false);
        return new ViewHolder(repoView,context,onTeamListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Team team =mTeamList.get(position);
        ImageView logoView=holder.logoView;
       Picasso.get().load(team.getTeam().getLogo()).into(logoView);
    }

    @Override
    public int getItemCount() {
        return mTeamList.size();
    }

    public interface OnTeamListener{
        void OnTeamClick(int position);
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Team> filteredList=new ArrayList<>();
            if(charSequence==null || charSequence.length()==0){
                filteredList.addAll(mTeamListFull);
            }else{
                String filterPattern=charSequence.toString().toLowerCase().trim();
                for(Team item:mTeamListFull){
                    if(item.getTeam().getName().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results=new FilterResults();
            results.values=filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mTeamList.clear();
            mTeamList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };
}
