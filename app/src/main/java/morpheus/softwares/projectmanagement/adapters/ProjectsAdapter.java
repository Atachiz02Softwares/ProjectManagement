package morpheus.softwares.projectmanagement.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import morpheus.softwares.projectmanagement.models.Projects;

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.Holder> {
    Context context;
    ArrayList<Projects> projects;

    public ProjectsAdapter(Context context, ArrayList<Projects> projects) {
        this.context = context;
        this.projects = projects;
    }

    @NonNull
    @Override
    public ProjectsAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.users_view, parent, false);
//        return new Holder(view);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectsAdapter.Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class Holder extends RecyclerView.ViewHolder {
        public Holder(@NonNull View itemView) {
            super(itemView);
        }
    }
}