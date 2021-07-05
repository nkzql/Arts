package com.example.finalexamart.asyncs;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.finalexamart.adapter.CommandAdapter;
import com.example.finalexamart.bean.Commands;
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
public class CommandAsync extends AsyncTask<Integer, Commands, List<Commands>> {
    private CommandAdapter adapter;
    private ProgressBar bar;
    private String url1=BASE_URL + "/getHomeCommand?&page=";
    private String url2=BASE_URL + "/getAreaCommand?&page=";
    private String url;
    private int page=1;
    public CommandAsync(CommandAdapter adapter,ProgressBar bar){
        this.adapter=adapter;
        this.bar=bar;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected List<Commands> doInBackground(Integer... integers) {
        page=integers[0];
        int home=integers[1];
        if(home==0){
            url=url1;
        }else{
            url=url2;
        }
        int type=integers[2];
        Log.e("commandAsync", String.valueOf(type));
        List<Commands> data = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url + String.valueOf(page)+"&type="+String.valueOf(type))
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
                String id=jsonObject.getString("_id");
                String cover = jsonObject.getString("cover");
                String title = jsonObject.getString("title");
                Commands commands=new Commands(id,title,cover);
                data.add(commands);
                publishProgress(commands);
            }
            } catch(JSONException | IOException e){
                e.printStackTrace();
            }
        return data;
    }
    @Override
    protected void onProgressUpdate(Commands... values) {
        super.onProgressUpdate(values);
        bar.setVisibility(View.GONE);
    }
    @Override
    protected void onPostExecute(List<Commands> commands) {
        super.onPostExecute(commands);
        if(page==1){
            adapter.initDataList(commands);
        }else{
            adapter.addDataList(commands);
        }
    }
    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}
