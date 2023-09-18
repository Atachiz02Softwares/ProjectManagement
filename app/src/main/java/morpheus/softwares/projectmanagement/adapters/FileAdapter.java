package morpheus.softwares.projectmanagement.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import morpheus.softwares.projectmanagement.R;
import morpheus.softwares.projectmanagement.models.FileItem;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.Holder> {
    Context context;
    private ArrayList<FileItem> fileList;

    public FileAdapter(Context context, ArrayList<FileItem> fileList) {
        this.fileList = fileList;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_file, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        FileItem fileItem = fileList.get(position);

        holder.fileName.setText(fileItem.getFileName());

        holder.fileName.setOnClickListener(v -> {
            // Get the resource ID based on the file name
            int resourceId = context.getResources().getIdentifier(
                    "raw/" + fileItem.getFileName(), null, context.getPackageName()
            );

            // Open the file using an Intent Chooser
            openFile(resourceId);
        });

    }

    /**
     * Adds a file to the list
     */
    public void addFile(FileItem fileItem) {
        fileList.add(fileItem);
        notifyDataSetChanged(); // Notify the adapter of the data change
    }

    @Override
    public int getItemCount() {
        return fileList.size();
    }

    private void openFile(int resourceId) {
        // Read the file contents from the raw resource
        InputStream inputStream = context.getResources().openRawResource(resourceId);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder fileContent = new StringBuilder();
        String line;

        try {
            while ((line = reader.readLine()) != null) fileContent.append(line).append('\n');
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Now you can display the file content or perform any other actions with it
        String content = fileContent.toString();
        // For example, you can display the content in a TextView or show it in a dialog
        showFileContentDialog(content);
    }

    private void showFileContentDialog(String content) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context, R.style.MaterialAlertDialogRounded);
        builder.setTitle("File Content");
        builder.setMessage(content);
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView fileName;

        public Holder(@NonNull View itemView) {
            super(itemView);
            fileName = itemView.findViewById(R.id.txtFileName);
        }
    }
}