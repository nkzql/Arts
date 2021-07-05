package com.example.finalexamart.asyncs;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import com.example.finalexamart.adapter.AreaAdapter;
import com.example.finalexamart.bean.AreaCulture;
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
public class AreaCultureAsync extends AsyncTask<String,AreaCulture, AreaCulture> {
    private AreaAdapter adapter;
    private ProgressBar bar;
    public AreaCultureAsync(AreaAdapter adapter,ProgressBar bar){
        this.adapter=adapter;
        this.bar=bar;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected AreaCulture doInBackground(String... strings) {
        AreaCulture areaCulture=null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(BASE_URL + "/getAreaArticle?&id="+strings[0])
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
                    String id1=jsonObject.getString("_id");
                    Log.e("areaasync",id1);
                    String title = jsonObject.getString("title");
                    String background = jsonObject.getString("cover");
                    String content = jsonObject.getString("article");
                    List<String> images = new ArrayList<>();
                    if(jsonObject.has("images")){
                        JSONArray jsonArray = jsonObject.getJSONArray("images");
                        for (int j = 0; j < jsonArray.length(); j++) {
                            JSONObject im=jsonArray.getJSONObject(j);
                            images.add(im.getString("tempFileURL"));
                        }
                    }
                    areaCulture = new AreaCulture(id1,title, content, background, images);
                    publishProgress(areaCulture);
                }
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
            Log.e("areaculture",areaCulture.getId());
        return areaCulture;
    }
    @Override
    protected void onProgressUpdate(AreaCulture... values) {
        super.onProgressUpdate(values);
        bar.setVisibility(View.GONE);
    }
    @Override
    protected void onPostExecute(AreaCulture areaCultures) {
        super.onPostExecute(areaCultures);
        adapter.addData(areaCultures);
    }
    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

//    private List<AreaCulture> getData(){
//        List<AreaCulture> data=null;
//        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//        JSONObject json = new JSONObject();
////        try{
////            json.put("key1","name");
////
////        } catch (JSONException e) {
////            e.printStackTrace();
////        }
//        try{
//            OkHttpClient client = new OkHttpClient();
//            RequestBody requestBody = RequestBody.create(JSON, String.valueOf(json));
//            Request request = new Request.Builder()
//                    .url(BASE_URL+"areaCulture"+"/?limit="+LIMITS)
//                    //.post(requestBody)
//                    .build();
//            client.newCall(request).enqueue(new Callback() {
//                @Override
//                public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                    e.printStackTrace();
//                }
//                @Override
//                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                    String responseData = response.body().string();
//                    Log.e("responsedata",responseData);
//                    JSONObject object = null;
//                    try {
//                        object = new JSONObject(responseData);
//                        JSONArray loadArray=object.getJSONArray("data");
//                        for(int i=0;i<object.getInt("total");i++){
//                            JSONObject jsonObject=loadArray.getJSONObject(i);
//                            String title=jsonObject.getString("title");
//                            String background=jsonObject.getString("background");
//                            String content=jsonObject.getString("article");
//                            List<String> images=new ArrayList<>();
//                            JSONArray jsonArray=jsonObject.getJSONArray("images");
//                            for(int j=0;j<jsonArray.length();j++){
//                                images.add(jsonArray.getString(j));
//                            }
//                            AreaCulture areaCulture=new AreaCulture(title,content,background,images);
//                            data.add(areaCulture);
////                        Message message = new Message();
////                        message.what = 1;
////                        handler.sendMessage(message);
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        Log.e("getData",data.toString());
//        return data;
//    }
}
