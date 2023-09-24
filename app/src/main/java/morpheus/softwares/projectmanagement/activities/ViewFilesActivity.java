package morpheus.softwares.projectmanagement.activities;

import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;

import morpheus.softwares.projectmanagement.R;

public class ViewFilesActivity extends AppCompatActivity {
//    private ArrayList<FileItem> fileList;
//    private RecyclerView recyclerView;
//    private FileAdapter fileAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_files);

        // Get the AssetManager
//        AssetManager assetManager = getResources().getAssets();
//
//        try {
//            // List all files in the raw folder
//            String[] files = assetManager.list("raw");
//
//            for (String fileName : files) {
//                // Create a FileItem object for each resource
//                FileItem fileItem = new FileItem(fileName, null); // You can set other properties
//
//                // Add the FileItem to your RecyclerView adapter
//                fileAdapter.addFile(fileItem);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        recyclerView = findViewById(R.id.fileRecyclerView);
//
//        fileList = new ArrayList<>();
//        fileAdapter = new FileAdapter(this, fileList);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(fileAdapter);
    }
}