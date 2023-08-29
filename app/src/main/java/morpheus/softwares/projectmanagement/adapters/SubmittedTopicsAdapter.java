package morpheus.softwares.projectmanagement.adapters;

import static java.lang.String.format;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import morpheus.softwares.projectmanagement.R;
import morpheus.softwares.projectmanagement.models.Database;
import morpheus.softwares.projectmanagement.models.Links;
import morpheus.softwares.projectmanagement.models.Project;
import morpheus.softwares.projectmanagement.models.Student;

public class SubmittedTopicsAdapter extends RecyclerView.Adapter<SubmittedTopicsAdapter.Holder> {
    Context context;
    ArrayList<Student> students;
    Database database;

    public SubmittedTopicsAdapter(Context context, ArrayList<Student> students) {
        this.context = context;
        this.students = students;
        this.database = new Database(context);
    }

    @NonNull
    @Override
    public SubmittedTopicsAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.projects_recycler, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubmittedTopicsAdapter.Holder holder, int position) {
        Student student = students.get(position);

        String firstTopic = student.getFirstProject(), secondTopic = student.getSecondProject(),
                thirdTopic = student.getThirdProject(), email = student.getEmail(), idNumber = student.getIdNumber(),
                firstStatus = student.getFirstStatus(), secondStatus = student.getSecondStatus(),
                thirdStatus = student.getThirdStatus(), supervisors = format("%s %s %s",
                new Links(context).matchSupervisors(student.getFirstArea()),
                new Links(context).matchSupervisors(student.getSecondArea()),
                new Links(context).matchSupervisors(student.getThirdArea()));

        holder.firstTopic.setText(firstTopic);
        holder.secondTopic.setText(secondTopic);
        holder.thirdTopic.setText(thirdTopic);
        holder.idNumber.setText(idNumber);
        holder.supervisors.setText(supervisors);

        if (firstStatus.equals(context.getString(R.string.approved))) {
            holder.secondApprove.setEnabled(false);
            holder.thirdApprove.setEnabled(false);
        } else if (secondStatus.equals(context.getString(R.string.approved))) {
            holder.firstApprove.setEnabled(false);
            holder.thirdApprove.setEnabled(false);
        } else if (thirdStatus.equals(context.getString(R.string.approved))) {
            holder.firstApprove.setEnabled(false);
            holder.secondApprove.setEnabled(false);
        }

        holder.firstApprove.setOnClickListener(v -> {
            if (holder.secondApprove.isEnabled() && holder.thirdApprove.isEnabled()) {
                Project project = new Project(0, idNumber, firstTopic);
                database.insertProject(project);
                database.updateFistTopicApprovalStatus(email, context.getString(R.string.approved));
                database.updateSecondTopicApprovalStatus(email, context.getString(R.string.disapproved));
                database.updateThirdTopicApprovalStatus(email, context.getString(R.string.disapproved));
                Toast.makeText(context, context.getString(R.string.topic_approved), Toast.LENGTH_SHORT).show();

                holder.secondApprove.setEnabled(false);
                holder.thirdApprove.setEnabled(false);
            } else
                Toast.makeText(context, context.getString(R.string.topic_already_approved), Toast.LENGTH_SHORT).show();
        });

        holder.secondApprove.setOnClickListener(v -> {
            if (holder.firstApprove.isEnabled() && holder.thirdApprove.isEnabled()) {
                Project project = new Project(0, idNumber, secondTopic);
                database.insertProject(project);
                database.updateFistTopicApprovalStatus(email, context.getString(R.string.disapproved));
                database.updateSecondTopicApprovalStatus(email, context.getString(R.string.approved));
                database.updateThirdTopicApprovalStatus(email, context.getString(R.string.disapproved));
                Toast.makeText(context, context.getString(R.string.topic_approved), Toast.LENGTH_SHORT).show();

                holder.firstApprove.setEnabled(false);
                holder.thirdApprove.setEnabled(false);
            } else
                Toast.makeText(context, context.getString(R.string.topic_already_approved), Toast.LENGTH_SHORT).show();
        });

        holder.thirdApprove.setOnClickListener(v -> {
            if (holder.firstApprove.isEnabled() && holder.secondApprove.isEnabled()) {
                Project project = new Project(0, idNumber, thirdTopic);
                database.insertProject(project);
                database.updateFistTopicApprovalStatus(email, context.getString(R.string.disapproved));
                database.updateSecondTopicApprovalStatus(email, context.getString(R.string.disapproved));
                database.updateThirdTopicApprovalStatus(email, context.getString(R.string.approved));
                Toast.makeText(context, context.getString(R.string.topic_approved), Toast.LENGTH_SHORT).show();

                holder.firstApprove.setEnabled(false);
                holder.thirdApprove.setEnabled(false);
            } else
                Toast.makeText(context, context.getString(R.string.topic_already_approved), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filter(ArrayList<Student> filteredStudents) {
        this.students = filteredStudents;
        notifyDataSetChanged();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        TextView firstTopic, secondTopic, thirdTopic, idNumber, supervisors;
        Button firstApprove, secondApprove, thirdApprove;

        public Holder(@NonNull View itemView) {
            super(itemView);

            firstTopic = itemView.findViewById(R.id.firstTopic);
            secondTopic = itemView.findViewById(R.id.secondTopic);
            thirdTopic = itemView.findViewById(R.id.thirdTopic);
            idNumber = itemView.findViewById(R.id.idNumber);
            supervisors = itemView.findViewById(R.id.supervisors);
            firstApprove = itemView.findViewById(R.id.firstApprove);
            secondApprove = itemView.findViewById(R.id.secondApprove);
            thirdApprove = itemView.findViewById(R.id.thirdApprove);
        }
    }
}