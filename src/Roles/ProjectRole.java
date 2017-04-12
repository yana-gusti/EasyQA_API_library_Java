package Roles;

import API.Initialization;
import com.google.gson.JsonObject;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

/**
 * Created by yanagusti on 3/31/17.
 */
public class ProjectRole extends Initialization {
    public ProjectRole(String url) {
        super(url);
    }

    /**
     * Method for adding user to your project (assign him role)
     * @param auth_token your authorization token in EasyQA
     * @param user_id an id of the user to which you want to assign the role (add to project)
     * @param organization_id an id of your organization
     * @param project_token token of your project in EasyQA. You can generate it on Integrations page
     * @param role can be "developer", "tester", "viewer" or "project_manager"
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */

    public void assign(String auth_token, Integer user_id, String organization_id, String project_token, String role) throws IOException, JSONException {


        JsonObject parent=new JsonObject();
        parent.addProperty("token", project_token);
        parent.addProperty("auth_token", auth_token);
        parent.addProperty("user_id", user_id);
        parent.addProperty("role", role);


        Call<ResponseBody> call= easyqaUserAPI.assigneOrganizationRole(organization_id, parent);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        if (result!=null){
            System.out.println(result);

            if (result.startsWith("{")) {
                JSONObject jsonObj = new JSONObject(result);
                Integer id_value = jsonObj.getInt("user_id");
                String role1 = jsonObj.getString("role");
                System.out.println("Role is assigned! role =" + id_value + "user ID = " + role1);

            }else {
                System.out.println(result);
            }
        }else {
            System.out.println("Something went wrong!");
        }

    }
}
