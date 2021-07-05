package com.example.finalexamart.activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.example.finalexamart.R;
import com.example.finalexamart.adapter.ImagesAdapter;
import com.example.finalexamart.asyncs.ImagesAsync;
import com.example.finalexamart.bean.AreaCulture;
import java.util.ArrayList;
import java.util.List;
public class ImagesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ImagesAdapter mImagesAdapter;
    private TextView textView;
    private String id;
    private int position;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        recyclerView=findViewById(R.id.images_recycle);
        textView=findViewById(R.id.image_no);
        id=getIntent().getStringExtra("id");
        mImagesAdapter=new ImagesAdapter(ImagesActivity.this);
        StaggeredGridLayoutManager mLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mImagesAdapter);
        Log.e("image",id);
        new ImagesAsync(textView,mImagesAdapter).execute(id);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
