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

public class SupervisorAdapter extends RecyclerView.Adapter<SupervisorAdapter.Holder> {
    Context context;
    ArrayList<Projects> projects;

    public SupervisorAdapter(Context context, ArrayList<Projects> projects) {
        this.context = context;
        this.projects = projects;
    }

    @NonNull
    @Override
    public SupervisorAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SupervisorAdapter.Holder holder, int position) {
        Projects project = projects.get(position);

        holder.idNumber.setText(project.getIdNumber());
        holder.topic.setText(project.getApprovedTopic());
        holder.status.setText(R.string.approved);
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        TextView topic, idNumber, status;

        public Holder(@NonNull View itemView) {
            super(itemView);

            topic = itemView.findViewById(R.id.projectTopic);
            idNumber = itemView.findViewById(R.id.idNum);
            status = itemView.findViewById(R.id.status);
        }
    }
}