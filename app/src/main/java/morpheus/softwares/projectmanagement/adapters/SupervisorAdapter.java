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
import morpheus.softwares.projectmanagement.models.Student;

public class SupervisorAdapter extends RecyclerView.Adapter<SupervisorAdapter.Holder> {
    Context context;
    ArrayList<Student> students;

    public SupervisorAdapter(Context context, ArrayList<Student> students) {
        this.context = context;
        this.students = students;
    }

    @NonNull
    @Override
    public SupervisorAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.supervisor_recycler, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SupervisorAdapter.Holder holder, int position) {
        Student student = students.get(position);

        String topic = student.get
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        TextView topic, idNumber;

        public Holder(@NonNull View itemView) {
            super(itemView);

            topic = itemView.findViewById(R.id.projectTopic);
            idNumber = itemView.findViewById(R.id.idNum);
        }
    }
}