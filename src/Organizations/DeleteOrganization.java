package Organizations;

import API.Initialization;
import com.google.gson.JsonObject;
import okhttp3.ResponseBody;
import org.json.JSONException;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

/**
 * Created by yanagusti on 3/21/17.
 */
public class DeleteOrganization extends Initialization{

    public DeleteOrganization(String url) {
        super(url);
    }

    /**
     * Method for deleting the organization in EasyQA
     * @param auth_token your authorization token in EasyQA
     * @param id the id of the organization
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */

    public void delete(String auth_token, String id) throws IOException, JSONException {

        JsonObject parent=new JsonObject();
        parent.addProperty("auth_token", auth_token);

        Call<ResponseBody> call= easyqaUserAPI.deleteOrganization(id, parent);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        if (result!=null) {
            System.out.println(result);

        }

        if (result.startsWith("{")) {
            System.out.println("Delete Organization "+id+"=" + result);

        }else {
            System.out.println(result);
        }

    }
}
