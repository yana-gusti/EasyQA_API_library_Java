package TestPlans.TestPlan;



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
 * Created by yanagusti on 3/17/17.
 */
public class CreateTestPlan extends Initialization {
    public Integer id_value;

    public CreateTestPlan(String url) {
        super(url);
    }

    /**
     * Method for creating a test plan on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param auth_token your authorization token in EasyQA
     * @param title a name of your test plan
     * @param data additional parameters for a test plan, can be description
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return id of the created test plan
     */

    public Integer create(String token, String auth_token, String title, String... data) throws IOException, JSONException {

        JsonObject test_plan = new GetNotRequiredParameters().getIntoJSON(data);
        test_plan.addProperty("title", title);


        JsonObject parent=new JsonObject();
        parent.addProperty("auth_token", auth_token);
        parent.addProperty("token", token);
        parent.add("test_plan", test_plan);

        Call<ResponseBody> call= easyqaUserAPI.createTestPlan(parent);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        if (result!=null){
            System.out.println(result);

            if (result.startsWith("{")) {
                JSONObject jsonObj = new JSONObject(result);
                id_value = jsonObj.getInt("id");
                System.out.println("Test plan is created! id=" + id_value);

            }else {
                System.out.println(result);
            }
        }else {
            System.out.println("Something went wrong");
        }
        return id_value;
    }

}
