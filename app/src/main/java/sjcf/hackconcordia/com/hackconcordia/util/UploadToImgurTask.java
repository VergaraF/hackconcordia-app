package sjcf.hackconcordia.com.hackconcordia.util;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.File;

/**
 * Created by MLH-Admin on 1/23/2016.
 */

//public class UploadToImgurTask extends AsyncTask<String, Void, Boolean> {
//
//    @Override
//    protected Boolean doInBackground(String... params) {
//        final String upload_to = "https://api.imgur.com/3/upload";
//
//        HttpClient httpClient = new DefaultHttpClient();
//        HttpContext localContext = new BasicHttpContext();
//        HttpPost httpPost = new HttpPost(upload_to);
//
//        try {
//            HttpEntity entity = MultipartEntityBuilder.create()
//                    .addPart("image", new FileBody(new File(params[0])))
//                    .build();
//
//            httpPost.setHeader("Authorization", "Bearer " + accessToken);
//            httpPost.setEntity(entity);
//
//            final HttpResponse response = httpClient.execute(httpPost,
//                    localContext);
//
//            final String response_string = EntityUtils.toString(response
//                    .getEntity());
//
//            final JSONObject json = new JSONObject(response_string);
//
//            Log.d("tag", json.toString());
//
//            JSONObject data = json.optJSONObject("data");
//            uploadedImageUrl = data.optString("link");
//            Log.d("tag", "uploaded image url : " + uploadedImageUrl);
//
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }