package morpheus.softwares.projectmanagement.adapters;

import static java.lang.String.format;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

import morpheus.softwares.projectmanagement.R;
import morpheus.softwares.projectmanagement.models.Links;
import morpheus.softwares.projectmanagement.models.Student;

public class CoodinatorAdapter extends RecyclerView.Adapter<CoodinatorAdapter.Holder> {
    Context context;
    ArrayList<Student> students;

    public CoodinatorAdapter(Context context, ArrayList<Student> students) {
        this.context = context;
        this.students = students;
    }

    @NonNull
    @Override
    public CoodinatorAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.coordinator_recycler, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoodinatorAdapter.Holder holder, int position) {
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
                        student.setFirstReport(String.valueOf(report.getText()));
                        alertDialog.dismiss();
                    }
                });

                builder.setView(view);

                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.show();
            }
        });

//        holder.secondApprove.setOnClickListener(v -> student.setSecondStatus("Approved"));

//        holder.thirdApprove.setOnClickListener(v -> student.setThirdStatus("Approved"));
    }

    @Override
    public int getItemCount() {
        return students.size();
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