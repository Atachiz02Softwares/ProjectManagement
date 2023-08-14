package morpheus.softwares.projectmanagement.adapters;

import static java.lang.String.format;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

import morpheus.softwares.projectmanagement.R;
import morpheus.softwares.projectmanagement.models.Database;
import morpheus.softwares.projectmanagement.models.Links;
import morpheus.softwares.projectmanagement.models.Projects;
import morpheus.softwares.projectmanagement.models.Student;

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.Holder> {
    Context context;
    ArrayList<Student> students;
    Database database;

    public ProjectsAdapter(Context context, ArrayList<Student> students) {
        this.context = context;
        this.students = students;
        this.database = new Database(context);
    }

    @NonNull
    @Override
    public ProjectsAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.projects_recycler, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectsAdapter.Holder holder, int position) {
        Student student = students.get(position);

        String firstTopic = student.getFirstProject(), secondTopic = student.getSecondProject(),
                thirdTopic = student.getThirdProject(), idNumber = student.getIdNumber(),
                supervisors = format("%s %s %s", new Links(context).matchSupervisors(student.getFirstArea()),
                        new Links(context).matchSupervisors(student.getSecondArea()),
                        new Links(context).matchSupervisors(student.getThirdArea()));

        holder.firstTopic.setText(firstTopic);
        holder.secondTopic.setText(secondTopic);
        holder.thirdTopic.setText(thirdTopic);
        holder.idNumber.setText(idNumber);
        holder.supervisors.setText(supervisors);

        holder.firstApprove.setOnClickListener(v -> {
            if ((holder.firstApprove.getText()).equals("Approve")) {
                Projects project = new Projects(0, idNumber, firstTopic);
                database.insertProject(project);

                student.setFirstStatus("Approved");
                student.setSecondStatus("Disapproved");
                student.setThirdStatus("Disapproved");
                holder.secondApprove.setText(R.string.disapprove);
                holder.thirdApprove.setVisibility(R.string.disapprove);
            } else {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context, R.style.MaterialAlertDialogRounded);
                builder.setCancelable(false);
                builder.setTitle("Reason for disapproval");
                builder.setIcon(R.drawable.baseline_cancel_24);
                builder.setCancelable(false);

                LayoutInflater inflater = LayoutInflater.from(context);
                View view = inflater.inflate(R.layout.report_dialog, null);
                EditText report = view.findViewById(R.id.report);
                Button disapprove = view.findViewById(R.id.disapprove);

                AlertDialog alertDialog = builder.create();

                disapprove.setOnClickListener(vi -> {
                    if (String.valueOf(report.getText()).isEmpty()) {
                        report.setError("Please provide a reason for your disapproval...");
                    } else {
                        student.setFirstReport(String.valueOf(report.getText()).trim());
                        Toast.makeText(context, "Topic disapproved!", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });

                builder.setView(view);

                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.show();
            }
        });

        holder.secondApprove.setOnClickListener(v -> {
            if ((holder.secondApprove.getText()).equals("Approve")) {
                Projects project = new Projects(0, idNumber, secondTopic);
                database.insertProject(project);

                student.setFirstStatus("Disapproved");
                student.setSecondStatus("Approved");
                student.setThirdStatus("Disapproved");
                holder.firstApprove.setText(R.string.disapprove);
                holder.thirdApprove.setVisibility(R.string.disapprove);
            } else {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context, R.style.MaterialAlertDialogRounded);
                builder.setCancelable(false);
                builder.setTitle("Reason for disapproval");
                builder.setIcon(R.drawable.baseline_cancel_24);
                builder.setCancelable(false);

                LayoutInflater inflater = LayoutInflater.from(context);
                View view = inflater.inflate(R.layout.report_dialog, null);
                EditText report = view.findViewById(R.id.report);
                Button disapprove = view.findViewById(R.id.disapprove);

                AlertDialog alertDialog = builder.create();

                disapprove.setOnClickListener(vi -> {
                    if (String.valueOf(report.getText()).isEmpty()) {
                        report.setError("Please provide a reason for your disapproval...");
                    } else {
                        student.setSecondReport(String.valueOf(report.getText()).trim());
                        Toast.makeText(context, "Topic disapproved!", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });

                builder.setView(view);

                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.show();
            }
        });

        holder.thirdApprove.setOnClickListener(v -> {
            if ((holder.thirdApprove.getText()).equals("Approve")) {
                Projects project = new Projects(0, idNumber, thirdTopic);
                database.insertProject(project);

                student.setFirstStatus("Disapproved");
                student.setSecondStatus("Disapproved");
                student.setThirdStatus("Approved");
                holder.firstApprove.setText(R.string.disapprove);
                holder.thirdApprove.setVisibility(R.string.disapprove);
            } else {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context, R.style.MaterialAlertDialogRounded);
                builder.setCancelable(false);
                builder.setTitle("Reason for disapproval");
                builder.setIcon(R.drawable.baseline_cancel_24);
                builder.setCancelable(false);

                LayoutInflater inflater = LayoutInflater.from(context);
                View view = inflater.inflate(R.layout.report_dialog, null);
                EditText report = view.findViewById(R.id.report);
                Button disapprove = view.findViewById(R.id.disapprove);

                AlertDialog alertDialog = builder.create();

                disapprove.setOnClickListener(vi -> {
                    if (String.valueOf(report.getText()).isEmpty()) {
                        report.setError("Please provide a reason for your disapproval...");
                    } else {
                        student.setThirdReport(String.valueOf(report.getText()).trim());
                        Toast.makeText(context, "Topic disapproved!", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });

                builder.setView(view);

                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.show();
            }
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