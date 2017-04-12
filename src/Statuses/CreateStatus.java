package Statuses;

import API.Initialization;
import com.google.gson.JsonObject;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

/**
 * Created by yanagusti on 4/5/17.
 */
public class CreateStatus extends Initialization{
    public Integer id_value;

    public CreateStatus(String url) {
        super(url);
    }

    /**
     * Method for creating a status for Agile board on Issues page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param auth_token your authorization token in EasyQA
     * @param name a short description of your status
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return id of the created status
     */

    public Integer create(String auth_token, String token, String name) throws IOException, JSONException {

        JsonObject cred = new JsonObject();
        cred.addProperty("name", name);
        JsonObject parent=new JsonObject();
        parent.addProperty("auth_token", auth_token);
        parent.addProperty("token", token);
        parent.add("status_object", cred);

        Call<ResponseBody> call= easyqaUserAPI.createStatus(parent);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        if (result!=null){
            System.out.println(result);

            if (result.startsWith("{")) {
                JSONObject jsonObj = new JSONObject(result);
                id_value = jsonObj.getInt("id");
                System.out.println("Status is created! id=" + id_value);

            }else {
                System.out.println(result);
            }
        }else {
            System.out.println("Such status already exists");
        }
        return id_value;
    }


}
