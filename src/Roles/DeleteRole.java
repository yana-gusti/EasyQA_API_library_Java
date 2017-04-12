package Roles;

import API.Initialization;
import com.google.gson.JsonObject;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

/**
 * Created by yanagusti on 3/31/17.
 */
public class DeleteRole extends Initialization {
    public DeleteRole(String url) {
        super(url);
    }

    /**
     * Method for deleting the role of the member in EasyQA
     * @param auth_token your authorization token in EasyQA
     * @param role_id the id of the member role
     * @throws IOException for incorrect parsing of the server response
     */

    public void delete (String role_id, String auth_token) throws IOException {

        JsonObject role = new JsonObject();
        role.addProperty("auth_token", auth_token);

        Call<ResponseBody> call= easyqaUserAPI.deleteRole(role_id, role);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        if (result!=null) {
            System.out.println(result);

        }

        if (result.startsWith("{")) {
            System.out.println("Delete Organization "+role_id+"=" + result);

        }else {
            System.out.println("error");
        }

    }
}
