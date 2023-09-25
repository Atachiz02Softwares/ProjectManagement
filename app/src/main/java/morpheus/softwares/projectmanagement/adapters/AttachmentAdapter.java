package morpheus.softwares.projectmanagement.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import morpheus.softwares.projectmanagement.R;
import morpheus.softwares.projectmanagement.models.Attachment;

public class AttachmentAdapter extends RecyclerView.Adapter<AttachmentAdapter.AttachmentViewHolder> {
    private final ArrayList<Attachment> attachments;
    private final Context context;

    public AttachmentAdapter(ArrayList<Attachment> attachments, Context context) {
        this.attachments = attachments;
        this.context = context;
    }

    @NonNull
    @Override
    public AttachmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_file, parent, false);
        return new AttachmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AttachmentViewHolder holder, int position) {
        Attachment attachment = attachments.get(position);

        holder.fileNameTextView.setText(attachment.getAttachmentName());

        holder.fileCard.setOnClickListener(view -> openFile(attachment.getAttachmentName(), attachment.getAttachmentData()));
    }

    @Override
    public int getItemCount() {
        return attachments.size();
    }

    private void openFile(String fileName, byte[] fileData) {
        try {
            // Create a temporary file
            File tempFile = File.createTempFile("temp_file", ".pdf", context.getCacheDir());

            // Write the file data to the temporary file
            FileOutputStream fos = new FileOutputStream(tempFile);
            fos.write(fileData);
            fos.close();

            // Create an intent to open the file
            Intent openIntent = new Intent(Intent.ACTION_VIEW);
            Uri fileUri = FileProvider.getUriForFile(context, "morpheus.softwares.projectmanagement.provider", tempFile);
            openIntent.setDataAndType(fileUri, "application/pdf"); // Replace with the appropriate

            // Grant permission to the receiving app
            openIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            // Start the chooser intent
            Intent chooserIntent = Intent.createChooser(openIntent, "Open pdf with:");
            context.startActivity(chooserIntent);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Error: " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public class AttachmentViewHolder extends RecyclerView.ViewHolder {
        private final CardView fileCard;
        private final TextView fileNameTextView;

        public AttachmentViewHolder(View itemView) {
            super(itemView);
            fileCard = itemView.findViewById(R.id.fileCard);
            fileNameTextView = itemView.findViewById(R.id.txtFileName);
        }
    }
}