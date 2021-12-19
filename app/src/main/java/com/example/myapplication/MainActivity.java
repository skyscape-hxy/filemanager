package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.documentfile.provider.DocumentFile;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<DocumentFile> fs=new ArrayList<>();
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ScaleView view = findViewById(R.id.iv);
//        TextView b = findViewById(R.id.b);
//        TextView s = findViewById(R.id.s);
        //MediaStore.getDocumentUri(this,)
        rv = findViewById(R.id.rv);
        requestPermission();

//        b.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                view.bigger();
//            }
//        });
//        s.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//view.smaller();
//            }
//        });
//
//
//        InputStream is = getResources().openRawResource(R.drawable.t);
//
//        Bitmap mBitmap = BitmapFactory.decodeStream(is);
//        view.setImageBitmap(mBitmap);
    }

    private void requestPermission() {


        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);


            } else {
getFile();
                // No explanation needed, we can request the permission.

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

getFile();
                } else {

                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
    private void getFile(){
        Log.e("Tag", "getFile: 开始" );
//        DocumentFile root = DocumentFile.fromTreeUri(this,
//                MediaStore.Files.getContentUri("external"));
        File directory = Environment.getExternalStorageDirectory();
        DocumentFile root = DocumentFile.fromFile(directory);
//        DocumentFile root = DocumentFile.fromTreeUri(this,
//                Uri.parse("external" ));
        DocumentFile[] files = root.listFiles();
        if(files!=null){
            Log.e("TAG", "getFile: 大小"+files.length );
            for(DocumentFile file : files){
                fs.add(file);
//                if (file.isDirectory()) {
//                    if (file.getName().equals("Pictures")){
//                        DocumentFile[] documentFiles = file.listFiles();
//                        for (DocumentFile documentFile : documentFiles) {
//                            Log.e("TAG", "onCreate: 文件名称----"+documentFile.getName() );
//                        }
//                    }
//                   // Log.e("TAG", "onCreate: 文件夹名称----"+file.getName() );
//                }else {
//                    Log.e("TAG", "onCreate: 文件名称----"+file.getName() );
//                }

                // sb.append(file.getName()).append("\r\n");
            }
        }
        MyAdapter myAdapter = new MyAdapter(this,fs);

        rv.setAdapter(myAdapter);
        Log.e("TAGy", "getFile: 完成 " );

    }
}