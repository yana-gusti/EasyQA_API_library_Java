package Projects;

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
public class CreateProject extends Initialization{

    public Integer id_value;

    public CreateProject(String url) {
        super(url);
    }

    /**
     * Method for creating the project in EasyQA
     * @param auth_token your authorization token in EasyQA
     * @param id an id of your organization in which you will create the project
     * @param title the name of your project
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return an id of your project
     */

    public Integer create(String auth_token, Integer id, String title) throws IOException, JSONException {

        JsonObject cred = new JsonObject();
        cred.addProperty("title", title);
        JsonObject parent=new JsonObject();
        parent.addProperty("auth_token", auth_token);
        parent.addProperty("organization_id", id);
        parent.add("project", cred);

        Call<ResponseBody> call= easyqaUserAPI.createProject(parent);

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
    /**
     * Method for getting the id of the created project in EasyQA
     * @return an id of the created project
     */
    public Integer getId(){
        return id_value;
    }
}
