package com.example.finalexamart.activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.finalexamart.R;
import com.example.finalexamart.adapter.AreaAdapter;
import com.example.finalexamart.asyncs.AreaCultureAsync;
import com.example.finalexamart.bean.AreaCulture;
import com.example.finalexamart.utils.ButtonClick;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import static com.example.finalexamart.activity.MainActivity.BASE_URL;
import static com.example.finalexamart.activity.MainActivity.LIMITS;
public class XINanActivity extends AppCompatActivity implements ButtonClick{
    private AreaAdapter areaAdapter;
    private RecyclerView recyclerView;
    private List<AreaCulture> data=new ArrayList<>();
    private ProgressBar bar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xinan);
        String id=getIntent().getStringExtra("id");
        recyclerView=findViewById(R.id.xinan_recycle);
        bar=findViewById(R.id.xinan_progressbar);
        bar.setVisibility(View.VISIBLE);
        LinearLayoutManager manager=new LinearLayoutManager(XINanActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        areaAdapter=new AreaAdapter(XINanActivity.this);
        areaAdapter.setButtonClick(this::onButtonClick);
        recyclerView.setAdapter(areaAdapter);
        // 参数id
        new AreaCultureAsync(areaAdapter,bar).execute(id);
    }
    @Override
    public void onButtonClick(String message, int position) {
        if(message!=null){
            Intent intent=new Intent(XINanActivity.this,ImagesActivity.class);
            intent.putExtra("id",message);
            Log.e("xinan",message);
            startActivity(intent);
        }
    }
}
