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
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SupervisorAdapter.Holder holder, int position) {
        Student student = students.get(position);

        ArrayList<String> areas = new ArrayList<>();
        for (Student std : students) {
//            if (std.getFirstArea().equals()
        }
    }

    @Override
    public int getItemCount() {
        return students.size();
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