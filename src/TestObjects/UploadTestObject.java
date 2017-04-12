package TestObjects;

import API.Initialization;
import com.google.gson.JsonObject;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Response;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanagusti on 4/5/17.
 */
public class UploadTestObject extends Initialization {
    public Integer id_value;

    public UploadTestObject(String url) {
        super(url);
    }

    /**
     * Method for adding the test object with a link of the tested website on Test Objects page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param auth_token your authorization token in EasyQA
     * @param link the link of tested website
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return Integert id of created test object
     */

    public Integer uploadLink(String auth_token, String token, String link) throws IOException, JSONException {


        JsonObject parent=new JsonObject();
        parent.addProperty("auth_token", auth_token);
        parent.addProperty("token", token);
        parent.addProperty("link", link);


        Call<ResponseBody> call= easyqaUserAPI.uploadLink(parent);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        if (result!=null){
            System.out.println(result);

            if (result.startsWith("{")) {
                JSONObject jsonObj = new JSONObject(result);
                id_value = jsonObj.getInt("id");
                System.out.println("Link is created! id=" + id_value);

            }else {
                System.out.println(result);
            }
        }else {
            System.out.println("Such status already exists");
        }
        return id_value;
    }

    /**
     * Method for adding the test object with a link of the tested website on Test Objects page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param auth_token your authorization token in EasyQA
     * @param file the file of mobile build, can be .apk or .ipa
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return Integert id of created test object
     */

    public Integer uploadBuild(String token, String auth_token, File file) throws IOException, JSONException {

        Map<String, RequestBody> model= new HashMap<>();

        model.put("token", RequestBody.create(textType, token));
        model.put("auth_token", RequestBody.create(textType, auth_token));
        model.put("file\"; filename=\""+file.getName()+"", RequestBody.create(MediaType.parse("application/apk"), file));

        Call<ResponseBody> call= easyqaUserAPI.uploadBuild(model);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        System.out.println(result);
        if (result.startsWith("{")) {
            JSONObject jsonObj = new JSONObject(result);
            id_value = jsonObj.getInt("id");
            System.out.println("Create success! Issue id=" + id_value);
        }else {
            System.out.println(result);
        }
        return id_value;
    }

}