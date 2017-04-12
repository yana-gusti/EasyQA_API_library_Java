package Organizations;

import API.Initialization;
import com.google.gson.JsonObject;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

/**
 * Created by yanagusti on 3/21/17.
 */
public class CreateOrganization extends Initialization{

    public Integer id_value;

    public CreateOrganization(String url) {
        super(url);
    }

    /**
     * Method for creating the organization in EasyQA
     * @param auth_token your authorization token in EasyQA
     * @param title the name of your organization
     * @param data additional parameters for an organization
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return Integer id of the organization
     */

    public Integer create(String auth_token, String title, String... data) throws IOException, JSONException {

        JsonObject cred = new JsonObject();
        cred.addProperty("title", title);
        if (data.length!=0) {
            cred.addProperty(data[0], data[1]);
        }
        JsonObject parent=new JsonObject();
        parent.addProperty("auth_token", auth_token);
        parent.add("organization", cred);

        Call<ResponseBody> call= easyqaUserAPI.createOrganization(parent);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        if (result!=null){
        System.out.println(result);

        if (result.startsWith("{")) {
            JSONObject jsonObj = new JSONObject(result);
            id_value = jsonObj.getInt("id");
            System.out.println("Organization is created! id=" + id_value);

        }else {
            System.out.println(result);
        }
        }else {
            System.out.println("Such company already exists");
        }
        return id_value;
    }


}
