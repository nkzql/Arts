package com.example.finalexamart.activity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.finalexamart.R;
import com.example.finalexamart.asyncs.ArticleAsync;
import static com.example.finalexamart.activity.MainActivity.BASE_URL;
public class ContentActivity extends AppCompatActivity {
    private TextView textView;
    private ProgressBar bar;
    private int type=0;
    private String url1=BASE_URL + "/getHomeArticle?&id=";
    private String url2=BASE_URL + "/getStoryArticle?&id=";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        textView=findViewById(R.id.content_content);
        bar=findViewById(R.id.content_progressbar);
        String id=getIntent().getStringExtra("id");
        type=getIntent().getIntExtra("type",0);
        switch (type){
            case 0:
                new ArticleAsync(this,textView,bar).execute(id,url1);
                break;
            case 1:
                new ArticleAsync(this,textView,bar).execute(id,url2);
                break;
            default:
                break;
        }
    }
}
