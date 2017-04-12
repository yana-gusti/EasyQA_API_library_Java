package Projects;

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
 * Created by yanagusti on 3/31/17.
 */
public class UpdateProject extends Initialization{
    public UpdateProject(String url) {
        super(url);
    }

    /**
     * Method for getting the list of the projects in EasyQA
     * @param auth_token your authorization token in EasyQA
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return map of Integer ids and String titles of the projects
     */

    public Map<Integer, String> getProjectsList(String auth_token) throws IOException, JSONException {

        Call<ResponseBody> call= easyqaUserAPI.getProjectsList(auth_token);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        Map<Integer, String> projects = new GetResponse().getMapInteger(result, "id", "title");
        return projects;
    }
    /**
     * Method for getting the project in EasyQA
     * @param auth_token your authorization token in EasyQA
     * @param id the id of your project
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return JsonObject with info about your project
     */
    public JSONObject getProject(String auth_token, String id) throws IOException, JSONException {


        String name;
        Call<ResponseBody> call= easyqaUserAPI.getProject(id, auth_token);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        System.out.println(result);
        if (result.startsWith("{")) {

            JSONObject obj1 = new JSONObject(result);
            name = obj1.getString("title");
            System.out.println("Project title= "+name);
            return obj1;

        }else {
            System.out.println("Something went wrong");
            return null;
        }

    }
    /**
     * Method for updating the project in EasyQA
     * @param auth_token your authorization token in EasyQA
     * @param title the name of your project
     * @param id the id of your project
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void update(String auth_token, String title, String id) throws IOException, JSONException {

        JsonObject cred = new JsonObject();
        cred.addProperty("title", title);
        JsonObject parent=new JsonObject();
        parent.addProperty("auth_token", auth_token);
        parent.add("project", cred);

        Call<ResponseBody> call= easyqaUserAPI.updateProject(id, parent);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        if (result!=null){
            System.out.println(result);

            if (result.startsWith("{")) {
                JSONObject jsonObj = new JSONObject(result);
                Integer id_value = jsonObj.getInt("id");
                System.out.println("Project is updated! id=" + id_value);

            }else {
                System.out.println(result);
            }
        }else {
            System.out.println("Such company already exists");
        }

    }
}
