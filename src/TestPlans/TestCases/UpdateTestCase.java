package TestPlans.TestCases;

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
 * Created by yanagusti on 4/5/17.
 */
public class UpdateTestCase extends Initialization{

    public UpdateTestCase(String url) {
        super(url);
    }

    /**
     * Method for getting the list of test cases of the selected module on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param auth_token your authorization token in EasyQA
     * @param module_id the id of the module
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return map with ids and titles of test cases
     */

    public Map<Integer, String> getTestCasesListOfModule(String auth_token, String token, String module_id) throws IOException, JSONException {

        Call<ResponseBody> call= easyqaUserAPI.getTestCasesListOfModule(module_id, auth_token, token);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        Map<Integer, String> links = new GetResponse().getMapInteger(result, "id", "title");
        return links;
    }

    /**
     * Method for getting the list of test cases of the selected test plan on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param auth_token your authorization token in EasyQA
     * @param test_plan_id the id of the test plan
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return map with ids and titles of test cases
     */

    public Map<Integer, String> getTestCasesListOfTestPlan(String auth_token, String token, String test_plan_id) throws IOException, JSONException {

        Call<ResponseBody> call= easyqaUserAPI.getTestCasesListOfTestPlan(test_plan_id, auth_token, token);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        Map<Integer, String> links = new GetResponse().getMapInteger(result, "id", "title");
        return links;
    }

    /**
     * Method for getting the test case on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param auth_token your authorization token in EasyQA
     * @param testcase_id the id of the test case
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return JSONObject result of the server response
     */

    public JSONObject getTestCase(String token, String auth_token, String testcase_id) throws IOException, JSONException {


        String name;
        Call<ResponseBody> call= easyqaUserAPI.getTestCase(testcase_id, auth_token, token);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        System.out.println(result);
        if (result.startsWith("{")) {

            JSONObject obj1 = new JSONObject(result);
            name = obj1.getString("title");
            System.out.println("Test Case title= "+name);
            return obj1;

        }else {
            System.out.println("Something went wrong");
            return null;
        }

    }
    /**
     * Method for updating the test case on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param auth_token your authorization token in EasyQA
     * @param testcase_id the id of the test case
     * @param data additional parameters for the test case
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void update(String token, String auth_token, String testcase_id, String... data) throws IOException, JSONException {

        JsonObject test_case = new GetNotRequiredParameters().getIntoJSON(data);

        JsonObject parent = new JsonObject();
        parent.addProperty("auth_token", auth_token);
        parent.addProperty("token", token);
        parent.add("test_case", test_case);

        Call<ResponseBody> call = easyqaUserAPI.updateTestCase(testcase_id, parent);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        System.out.println(result);
        if (result.startsWith("{")) {

            JSONObject obj1 = new JSONObject(result);
            String name = obj1.getString("title");
            System.out.println("Test Case title= " + name);


        } else {
            System.out.println("Something went wrong");

        }
    }
}
