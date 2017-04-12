package TestPlans.Modules;

import API.GetNotRequiredParameters;
import API.Initialization;
import com.google.gson.JsonObject;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

/**
 * Created by yanagusti on 3/20/17.
 */
public class CreateModule extends Initialization{
    public Integer id_value;

    public CreateModule(String url) {
        super(url);
    }

    /**
     * Method for creating a module for the selected test plan on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param auth_token your authorization token in EasyQA
     * @param test_plan_id the id of the test plan in which you will create the module
     * @param title a name of your module
     * @param data additional parameters for a module, can be description
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return id of the created module
     */

    public Integer create(String token, String auth_token, String test_plan_id, String title, String... data) throws IOException, JSONException {

        JsonObject test_case = new GetNotRequiredParameters().getIntoJSON(data);
        test_case.addProperty("title", title);


        JsonObject parent=new JsonObject();
        parent.addProperty("auth_token", auth_token);
        parent.addProperty("token", token);
        parent.add("test_module", test_case);

        Call<ResponseBody> call= easyqaUserAPI.createTestModule(test_plan_id, parent);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        if (result!=null){
            System.out.println(result);

            if (result.startsWith("{")) {
                JSONObject jsonObj = new JSONObject(result);
                id_value = jsonObj.getInt("id");
                System.out.println("Test module is created! id=" + id_value);

            }else {
                System.out.println(result);
            }
        }else {
            System.out.println("Something went wrong");
        }
        return id_value;
    }

}
