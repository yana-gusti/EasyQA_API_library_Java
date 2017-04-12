package TestRuns.TestRun;

import API.GetNotRequiredParameters;
import API.GetResponse;
import API.Initialization;
import com.google.gson.JsonArray;
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
public class UpdateTestRun extends Initialization{

    public UpdateTestRun(String url) {
        super(url);
    }

    /**
     * Method for getting the list of test runs on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param auth_token your authorization token in EasyQA
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return map with ids and titles of test runs
     */

    public Map<Integer, String> getTestRunsList(String auth_token, String token) throws IOException, JSONException {
        Call<ResponseBody> call= easyqaUserAPI.getTestRunsList(auth_token, token);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        Map<Integer, String> links = new GetResponse().getMapInteger(result, "id", "title");
        return links;
    }

    /**
     * Method for getting the test run on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param auth_token your authorization token in EasyQA
     * @param test_run_id the id of the test run
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return JSONObject result of the server response
     */

    public JSONObject getTestRun(String token, String auth_token, String test_run_id) throws IOException, JSONException {


        String name;
        Call<ResponseBody> call= easyqaUserAPI.getTestRun(test_run_id, auth_token, token);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        System.out.println(result);
        if (result.startsWith("{")) {

            JSONObject obj1 = new JSONObject(result);
            name = obj1.getString("title");
            System.out.println("Test Run = "+name);
            return obj1;

        }else {
            System.out.println("Something went wrong");
            return null;
        }

    }

    /**
     * Method for updating a test run with all test cases of the selected test plan on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param auth_token your authorization token in EasyQA
     * @param test_plan_id the id of the test plan from which you want to get all test cases
     * @param test_run_id an id of your test run
     * @param data additional parameters for a test run, can be description
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */

    public void updateIncludeAllTestCases(String token, String auth_token, String test_run_id, Integer test_plan_id,
                                             String... data) throws IOException, JSONException {

        JsonObject test_run = new GetNotRequiredParameters().getIntoJSON(data);

        JsonObject testplan = new JsonObject();
        testplan.addProperty("test_plan_id", test_plan_id);

        JsonArray test_run_results_attributes = new JsonArray();
        test_run_results_attributes.add(testplan);

        test_run.add("test_run_results_attributes", test_run_results_attributes);


        JsonObject parent=new JsonObject();
        parent.addProperty("auth_token", auth_token);
        parent.addProperty("token", token);
        parent.add("test_run",  test_run);

        Call<ResponseBody> call= easyqaUserAPI.updateTestRun(test_run_id, parent);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        if (result!=null){
            System.out.println(result);

            if (result.startsWith("{")) {
                JSONObject jsonObj = new JSONObject(result);
                Integer id_value = jsonObj.getInt("id");
                System.out.println("Test run is updated! id=" + id_value);

            }else {
                System.out.println(result);
            }
        }else {
            System.out.println("Something went wrong");
        }

    }

    /**
     * Method for updating a test run with selected test cases on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param auth_token your authorization token in EasyQA
     * @param test_case_ids the array of ids of the test cases
     * @param test_run_id an id of your test run
     * @param data additional parameters for a test run, can be description
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */

    public void updateSelectTestCases(String token, String auth_token, String test_run_id,
                                         Integer test_case_ids[],
                                         String... data) throws IOException, JSONException {

        JsonObject test_run = new GetNotRequiredParameters().getIntoJSON(data);
        JsonArray test_run_results_attributes = new JsonArray();

        for (Integer test_case_id:test_case_ids) {
            JsonObject test_case = new JsonObject();
            test_case.addProperty("test_case_id", test_case_id);
            test_run_results_attributes.add(test_case);
        }

        test_run.add("test_run_results_attributes", test_run_results_attributes);

        JsonObject parent=new JsonObject();
        parent.addProperty("auth_token", auth_token);
        parent.addProperty("token", token);
        parent.add("test_run",  test_run);

        Call<ResponseBody> call= easyqaUserAPI.updateTestRun(test_run_id, parent);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        if (result!=null){
            System.out.println(result);

            if (result.startsWith("{")) {
                JSONObject jsonObj = new JSONObject(result);
                Integer id_value = jsonObj.getInt("id");
                System.out.println("Test run is updated! id=" + id_value);

            }else {
                System.out.println(result);
            }
        }else {
            System.out.println("Something went wrong");
        }

    }

    /**
     * Method for deleting test cases from selected test run on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param auth_token your authorization token in EasyQA
     * @param ids the array of ids of test run results.
     * @param test_run_id an id of your test run
     * @param data additional parameters for a test run, can be description
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */

    public void deleteTestCasesFromTestRun(String token, String auth_token, String test_run_id,
                                      Integer ids[],
                                      String... data) throws IOException, JSONException {

        JsonObject test_run = new GetNotRequiredParameters().getIntoJSON(data);
        JsonArray test_run_results_attributes = new JsonArray();

        for (Integer test_case_id : ids) {
            JsonObject test_case = new JsonObject();
            test_case.addProperty("id", test_case_id);
            test_case.addProperty("_destroy", true);
            test_run_results_attributes.add(test_case);
        }

        test_run.add("test_run_results_attributes", test_run_results_attributes);

        JsonObject parent = new JsonObject();
        parent.addProperty("auth_token", auth_token);
        parent.addProperty("token", token);
        parent.add("test_run", test_run);

        Call<ResponseBody> call = easyqaUserAPI.updateTestRun(test_run_id, parent);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        if (result != null) {
            System.out.println(result);

            if (result.startsWith("{")) {
                JSONObject jsonObj = new JSONObject(result);
                Integer id_value = jsonObj.getInt("id");
                System.out.println("Test run is updated! id=" + id_value);

            } else {
                System.out.println(result);
            }
        } else {
            System.out.println("Something went wrong");
        }
    }
}
