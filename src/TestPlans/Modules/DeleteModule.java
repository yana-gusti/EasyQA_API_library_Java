package TestPlans.Modules;

import API.Initialization;
import com.google.gson.JsonObject;
import okhttp3.ResponseBody;
import org.json.JSONException;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

/**
 * Created by yanagusti on 4/5/17.
 */
public class DeleteModule extends Initialization {

    public DeleteModule (String url) {
        super(url);
    }

    /**
     * Method for deleting a module from the selected test plan on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param auth_token your authorization token in EasyQA
     * @param module_id an id of your module
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */

    public void delete(String auth_token, String token, String module_id) throws IOException, JSONException {

        JsonObject parent=new JsonObject();
        parent.addProperty("auth_token", auth_token);
        parent.addProperty("token", token);

        Call<ResponseBody> call= easyqaUserAPI.deleteTestModule(module_id, parent);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        if (result!=null) {
            System.out.println(result);

        }

        if (result.startsWith("{")) {
            System.out.println("Delete Module "+module_id+"=" + result);

        }else {
            System.out.println("error");
        }

    }

}
