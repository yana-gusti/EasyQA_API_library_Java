package TestRuns.TestRunResults;

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
public class UpdateTestRunResults extends Initialization {

    public UpdateTestRunResults(String url) {
        super(url);
    }

    /**
     * Method for getting the list of test run results on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param auth_token your authorization token in EasyQA
     * @param test_run_id the id of your test run
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return map with ids and statuses of test run results (of test cases which are added to the test run)
     */

    public Map<Integer, String> getTestRunResultsList(String auth_token, String token, String test_run_id) throws IOException, JSONException {

        Call<ResponseBody> call= easyqaUserAPI.getTestRunResultsList(test_run_id, auth_token, token);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        Map<Integer, String> links = new GetResponse().getMapInteger(result, "id", "status");
        return links;
    }

    /**
     * Method for getting the test run result on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param auth_token your authorization token in EasyQA
     * @param test_run_result_id the id of the test run result (for test case which is added to the test run)
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return JSONObject result of the server response
     */
    public JSONObject getTestRunResult(String token, String auth_token, String test_run_result_id) throws IOException, JSONException {


        String name;
        Call<ResponseBody> call= easyqaUserAPI.getTestRunResult(test_run_result_id, auth_token, token);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        System.out.println(result);
        if (result.startsWith("{")) {

            JSONObject obj1 = new JSONObject(result);
            name = obj1.getString("status");
            System.out.println("Test Run Result = "+name);
            return obj1;

        }else {
            System.out.println("Something went wrong");
            return null;
        }

    }

    /**
     * Method for updating a test run result on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param auth_token your authorization token in EasyQA
     * @param test_run_result_id an id of your test run result (for test case which is added to the test run)
     * @param result_status Status of test run results, might be in "pass", "block", "untested" and "fail"
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     */
    public void update(String token, String auth_token, String test_run_result_id, String result_status) throws IOException, JSONException {

        JsonObject test_run_result = new JsonObject();
        test_run_result.addProperty("result_status", result_status);

        JsonObject parent=new JsonObject();
        parent.addProperty("auth_token", auth_token);
        parent.addProperty("token", token);
        parent.add("test_run_result",  test_run_result);

        Call<ResponseBody> call= easyqaUserAPI.updateTestRunResult(test_run_result_id, parent);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        if (result!=null){
            System.out.println(result);

            if (result.startsWith("{")) {
                JSONObject jsonObj = new JSONObject(result);
                 String name = jsonObj.getString("status");
                System.out.println("Test Run Result = "+name);

            }else {
                System.out.println(result);
            }
        }else {
            System.out.println("Something went wrong");
        }

    }
}
