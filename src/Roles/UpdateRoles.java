package Roles;

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
public class UpdateRoles extends Initialization {

    public UpdateRoles(String url) {
        super(url);
    }

    /**
     * Method for getting all roles in the organization
     * @param auth_token your authorization token in EasyQA
     * @param organization_id an id of the organization
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return Map with ids and titles of organization roles
     */

    public Map<Integer, String> getOrganizationRolesList(String organization_id, String auth_token) throws IOException, JSONException {

        Call<ResponseBody> call= easyqaUserAPI.getOrganizationsRolesList(organization_id, auth_token);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        Map<Integer, String> roles = new GetResponse().getMapInteger(result, "id", "title");
        return roles;
    }

    /**
     * Method for getting all roles in the project
     * @param auth_token your authorization token in EasyQA
     * @param organization_id an id of the organization
     * @param token token of your project in EasyQA. You can generate it on Integrations page
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return Map with ids and titles of project roles
     */


    public Map<Integer, String> getProjectRolesList(String organization_id, String auth_token, String token) throws IOException, JSONException {

        Call<ResponseBody> call= easyqaUserAPI.getProjectRolesList(organization_id, auth_token, token);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        Map<Integer, String> roles = new GetResponse().getMapInteger(result, "id", "title");
        return roles;
    }

    /**
     * Method for getting role in EasyQA
     * @param auth_token your authorization token in EasyQA
     * @param id an id of the role
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return JSONObject with the server response
     */

    public JSONObject getRole(String auth_token, String id) throws IOException, JSONException {



        Call<ResponseBody> call= easyqaUserAPI.getRole(id, auth_token);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        System.out.println(result);
        if (result!=null){
            System.out.println(result);
            JSONObject object = new JSONObject(result);
            return object;
        }else {
            System.out.println("Something went wrong!");
            return null;
        }

    }

    /**
     * Method for updating the role of the user in the organization in EasyQA
     * @param auth_token your authorization token in EasyQA
     * @param user_id an id of the user to which you want to assign the role (add to organization)
     * @param role_id the id of your role in the organization
     * @param role can be "user" or "admin"
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */

    public void updateOrganizationRole(String role_id, String auth_token, String user_id, String role) throws IOException, JSONException {

        JsonObject parent=new JsonObject();
        parent.addProperty("auth_token", auth_token);
        parent.addProperty("user_id", user_id);
        parent.addProperty("role", role);

        Call<ResponseBody> call= easyqaUserAPI.updateRole(role_id, parent);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        if (result!=null){
            System.out.println(result);

            if (result.startsWith("{")) {
                JSONObject jsonObj = new JSONObject(result);
                Integer id_value = jsonObj.getInt("id");
                System.out.println("Role is updated! id=" + id_value);

            }else {
                System.out.println(result);
            }
        }else {
            System.out.println("Such company already exists");
        }

    }

    /**
     * Method for updating the role of the user in your project
     * @param auth_token your authorization token in EasyQA
     * @param role_id an id of the role in the project
     * @param token token of your project in EasyQA. You can generate it on Integrations page
     * @param role can be "developer", "tester", "viewer" or "project_manager"
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */

    public void updateProjectRole(String role_id, String auth_token, String token, String role) throws IOException, JSONException {

        JsonObject parent=new JsonObject();
        parent.addProperty("auth_token", auth_token);
        parent.addProperty("token", token);
        parent.addProperty("role", role);

        Call<ResponseBody> call= easyqaUserAPI.updateRole(role_id, parent);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        if (result!=null){
            System.out.println(result);

            if (result.startsWith("{")) {
                JSONObject jsonObj = new JSONObject(result);
                Integer id_value = jsonObj.getInt("id");
                System.out.println("Role is updated! id=" + id_value);

            }else {
                System.out.println(result);
            }
        }else {
            System.out.println("Such company already exists");
        }

    }
}
