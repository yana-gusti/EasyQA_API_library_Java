package Statuses;

import API.GetResponse;
import API.Initialization;
import com.google.gson.JsonObject;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Map;

/**
 * Created by yanagusti on 4/5/17.
 */
public class UpdateStatus extends Initialization{

    public UpdateStatus(String url) {
        super(url);
    }

    /**
     * Method for getting the list of status on Issues page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param auth_token your authorization token in EasyQA
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return map with ids and names of statuses
     */

    public Map<Integer, String> getStatusesList(String auth_token, String token) throws IOException, JSONException {

        Call<ResponseBody> call= easyqaUserAPI.getStatusesList(auth_token, token);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        Map<Integer, String> statuses = new GetResponse().getMapInteger(result, "id", "name");
        return statuses;
    }

    /**
     * Method for getting status info by the status ID in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param auth_token your authorization token in EasyQA
     * @param id the status ID
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return JSONObject result of the server response
     */

    public JSONObject getStatus(String auth_token, String token, String id) throws IOException, JSONException {


        String name;
        Call<ResponseBody> call= easyqaUserAPI.getStatus(id, auth_token, token);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        System.out.println(result);
        if (result.startsWith("{")) {

            JSONObject obj1 = new JSONObject(result);
            name = obj1.getString("name");
            System.out.println("Status name = "+name);
            return obj1;

        }else {
            System.out.println("Something went wrong");
            return null;
        }

    }

    /**
     * Method for updating status by the status ID in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param auth_token your authorization token in EasyQA
     * @param name the name of status
     * @param id the status ID
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void update(String auth_token, String token, String name, String id) throws IOException, JSONException {

        JsonObject cred = new JsonObject();
        cred.addProperty("name", name);
        JsonObject parent=new JsonObject();
        parent.addProperty("auth_token", auth_token);
        parent.addProperty("token", token);
        parent.add("status_object", cred);

        Call<ResponseBody> call= easyqaUserAPI.updateStatus(id, parent);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        if (result!=null){
            System.out.println(result);

            if (result.startsWith("{")) {
                JSONObject jsonObj = new JSONObject(result);
                Integer id_value = jsonObj.getInt("id");
                System.out.println("Status is updated! id=" + id_value);

            }else {
                System.out.println(result);
            }
        }else {
            System.out.println("Something went wrong");
        }

    }
}
