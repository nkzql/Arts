package com.example.finalexamart.asyncs;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.finalexamart.utils.MarkWonUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import static com.example.finalexamart.activity.MainActivity.BASE_URL;
public class ArticleAsync extends AsyncTask<String, String, String> {
    private ProgressBar bar;
    private TextView textView;
    private Context context;
    public ArticleAsync(Context context,TextView textView,ProgressBar bar){
        this.context=context;
        this.textView=textView;
        this.bar=bar;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected String doInBackground(String... strings) {
        Log.e("do",strings[1]);
        String url=strings[1];
        String id=strings[0];
        String data = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url + id)
                .get()
                .build();
        Response response = null;
        JSONObject object = null;
        try {
            response = client.newCall(request).execute();
            String responseData = response.body().string();
            Log.e("responsedata",responseData);
            object = new JSONObject(responseData);
            JSONArray loadArray = object.getJSONArray("data");
            for (int i = 0; i < loadArray.length(); i++) {
                JSONObject jsonObject = loadArray.getJSONObject(i);
                String _id=jsonObject.getString("_id");
                String article=jsonObject.getString("article");
                data=article;
            }
        } catch(JSONException | IOException e){
            e.printStackTrace();
        }
        return data;
    }
    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        //bar.setVisibility(View.GONE);
    }
    @Override
    protected void onPostExecute(String commands) {
        super.onPostExecute(commands);
        this.bar.setVisibility(View.GONE);
        if(commands!=null) {
            MarkWonUtils.setMarkwon(context,commands,textView);
        }
    }
    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}
