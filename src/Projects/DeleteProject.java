package Projects;

import API.Initialization;
import com.google.gson.JsonObject;
import okhttp3.ResponseBody;
import org.json.JSONException;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

/**
 * Created by yanagusti on 3/31/17.
 */
public class DeleteProject extends Initialization{
    public DeleteProject(String url) {
        super(url);
    }

    /**
     * Method for deleting the project in EasyQA
     * @param auth_token your authorization token in EasyQA
     * @param id the id of your project
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */

    public void delete(String auth_token, String id) throws IOException, JSONException {

        JsonObject parent=new JsonObject();
        parent.addProperty("auth_token", auth_token);

        Call<ResponseBody> call= easyqaUserAPI.deleteProject(id, parent);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        if (result!=null) {
            System.out.println(result);

        }

        if (result.startsWith("{")) {
            System.out.println("Delete Project "+id+"=" + result);

        }else {
            System.out.println("error");
        }

    }
}
