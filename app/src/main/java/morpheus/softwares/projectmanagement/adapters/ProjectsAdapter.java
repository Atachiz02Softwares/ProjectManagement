package morpheus.softwares.projectmanagement.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import morpheus.softwares.projectmanagement.R;
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
        View view = LayoutInflater.from(context).inflate(R.layout.project_recycler, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectsAdapter.Holder holder, int position) {
        Projects project = projects.get(position);

        String topic = project.getFirstTopic(), idNumber, name, email, area, supervisor;
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        TextView topic, idNumber, name, email, area, supervisor;

        public Holder(@NonNull View itemView) {
            super(itemView);

            topic = itemView.findViewById(R.id.topic);
            idNumber = itemView.findViewById(R.id.idNumber);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            area = itemView.findViewById(R.id.area);
            supervisor = itemView.findViewById(R.id.supervisor);
        }
    }
}