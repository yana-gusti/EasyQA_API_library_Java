package TestPlans.TestPlan;

import API.GetNotRequiredParameters;
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
 * Created by yanagusti on 3/20/17.
 */
public class UpdateTestPlan  extends Initialization {

    public UpdateTestPlan(String url) {
        super(url);
    }

    /**
     * Method for getting the list of test plans on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param auth_token your authorization token in EasyQA
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return map with ids and titles of test plans
     */

    public Map<Integer, String> getTestPlansList(String auth_token, String token) throws IOException, JSONException {

        Call<ResponseBody> call= easyqaUserAPI.getTestPlansList(auth_token, token);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        Map<Integer, String> links = new GetResponse().getMapInteger(result, "id", "title");
        return links;
    }

    /**
     * Method for getting the test plan on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param auth_token your authorization token in EasyQA
     * @param testplan_id the id of the test plan
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return JSONObject result of the server response
     */
    public JSONObject getTestPlan(String token, String auth_token, String testplan_id) throws IOException, JSONException {


        String name;
        Call<ResponseBody> call= easyqaUserAPI.getTestPlan(testplan_id, auth_token, token);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        System.out.println(result);
        if (result.startsWith("{")) {

            JSONObject obj1 = new JSONObject(result);
            name = obj1.getString("title");
            System.out.println("Test Plan title= "+name);
            return obj1;

        }else {
            System.out.println("Something went wrong");
            return null;
        }

    }

    /**
     * Method for updating the test plan on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param auth_token your authorization token in EasyQA
     * @param test_plan_id the id of the test plan
     * @param title a name of your test plan
     * @param data additional parameters for a module, can be description
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */

        public void update (String token, String auth_token, String test_plan_id, String title, String... data) throws IOException, JSONException {

            JsonObject test_plan = new GetNotRequiredParameters().getIntoJSON(data);
            test_plan.addProperty("title", title);


            JsonObject parent=new JsonObject();
            parent.addProperty("auth_token", auth_token);
            parent.addProperty("token", token);
            parent.add("test_plan", test_plan);

            Call<ResponseBody> call= easyqaUserAPI.updateTestPlan(test_plan_id, parent);

            Response<ResponseBody> bodyResponse = call.execute();
            String result = bodyResponse.body().string();
            if (result!=null){
                System.out.println(result);

                if (result.startsWith("{")) {
                    JSONObject jsonObj = new JSONObject(result);
                    Integer id_value = jsonObj.getInt("id");
                    System.out.println("Test plan is created! id=" + id_value);

                }else {
                    System.out.println(result);
                }
            }else {
                System.out.println("Something went wrong");
            }

        }
}
