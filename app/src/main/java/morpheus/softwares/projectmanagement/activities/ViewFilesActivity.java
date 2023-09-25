package morpheus.softwares.projectmanagement.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import morpheus.softwares.projectmanagement.R;
import morpheus.softwares.projectmanagement.adapters.AttachmentAdapter;
import morpheus.softwares.projectmanagement.models.Attachment;
import morpheus.softwares.projectmanagement.models.Database;

public class ViewFilesActivity extends AppCompatActivity {
    private ArrayList<Attachment> attachments;
    private RecyclerView recyclerView;
    private AttachmentAdapter fileAdapter;

    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_files);

        database = new Database(this);
        attachments = database.selectAllAttachments();

        recyclerView = findViewById(R.id.fileRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        fileAdapter = new AttachmentAdapter(attachments, this);
        recyclerView.setAdapter(fileAdapter);
    }
}