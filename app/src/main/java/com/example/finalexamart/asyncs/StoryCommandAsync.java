package com.example.finalexamart.asyncs;
import android.os.AsyncTask;
import com.example.finalexamart.adapter.StoryAdapter;
import com.example.finalexamart.bean.Story;
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
public class StoryCommandAsync extends AsyncTask<Integer, Story, List<Story>> {
    private StoryAdapter adapter;
    //private ProgressBar bar;
    private int page=1;
    public StoryCommandAsync(StoryAdapter adapter){
        this.adapter=adapter;
        //this.bar=bar;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected List<Story> doInBackground(Integer... integers) {
        page=integers[0];
        List<Story> data = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(BASE_URL + "/getStoryCommand?&page=" + String.valueOf(page))
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
                String title = jsonObject.getString("title");
                Story commands=new Story(id,title);
                data.add(commands);
                publishProgress(commands);
            }
        } catch(JSONException | IOException e){
            e.printStackTrace();
        }
        return data;
    }
    @Override
    protected void onProgressUpdate(Story... values) {
        super.onProgressUpdate(values);
        //bar.setVisibility(View.GONE);
    }
    @Override
    protected void onPostExecute(List<Story> commands) {
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
