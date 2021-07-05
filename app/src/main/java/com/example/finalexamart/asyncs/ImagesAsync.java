package com.example.finalexamart.asyncs;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.finalexamart.adapter.ImagesAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import static com.example.finalexamart.activity.MainActivity.BASE_URL;
public class ImagesAsync extends AsyncTask<String, String, List<String>> {
    private ImagesAdapter adapter;
    private TextView textView;
    //private ProgressBar bar;
    public ImagesAsync(ImagesAdapter adapter){
        this.adapter=adapter;
        //this.bar=bar;
    }
    public ImagesAsync(TextView textView,ImagesAdapter adapter){
        this.textView=textView;
        this.adapter=adapter;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected List<String> doInBackground(String... strings) {
        String id=strings[0];
        Log.e("id",id);
        List<String> data = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(BASE_URL + "/getAreaImages?&id=" + id)
                .get()
                .build();
        Response response = null;
        JSONObject object = null;
        try {
            response = client.newCall(request).execute();
            String responseData = response.body().string();
            object = new JSONObject(responseData);
            JSONArray loadArray = object.getJSONArray("data");
            for (int i = 0; i < loadArray.length(); i++) {
                JSONObject jsonObject = loadArray.getJSONObject(i);
                String _id=jsonObject.getString("_id");
                if(jsonObject.has("images")){
                    JSONArray jsonArray = jsonObject.getJSONArray("images");
                    for (int j = 0; j < jsonArray.length(); j++) {
                        JSONObject im=jsonArray.getJSONObject(j);
                        data.add(im.getString("tempFileURL"));
                    }
                }
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
    protected void onPostExecute(List<String> commands) {
        super.onPostExecute(commands);
        if(commands.size()<1){
            textView.setVisibility(View.VISIBLE);
        }
        adapter.setData(commands);
        adapter.setAll(true);
    }
    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}
