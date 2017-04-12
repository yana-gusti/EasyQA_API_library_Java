package TestRuns.TestRun;

import API.GetNotRequiredParameters;
import API.Initialization;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

/**
 * Created by yanagusti on 4/5/17.
 */
public class CreateTestRun extends Initialization{

    Integer id_value;

    public CreateTestRun(String url) {
        super(url);
    }

    /**
     * Method for creating a test run with all test cases of the selected test plan on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param auth_token your authorization token in EasyQA
     * @param test_plan_id the id of the test plan from which you want to get all test cases
     * @param title a name of your test run
     * @param assigner_id the id of the project member, with role owner, admin, project_admin or tester
     * @param data additional parameters for a test run, can be description
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return id of the created test run
     */

    public Integer createIncludeAllTestCases(String token, String auth_token, String title, Integer test_plan_id, Integer assigner_id,
                           String... data) throws IOException, JSONException {

        JsonObject test_run = new GetNotRequiredParameters().getIntoJSON(data);
        test_run.addProperty("title", title);
        test_run.addProperty("assigner_id", assigner_id);

        JsonObject testplan = new JsonObject();
        testplan.addProperty("test_plan_id", test_plan_id);

        JsonArray test_run_results_attributes = new JsonArray();
        test_run_results_attributes.add(testplan);

        test_run.add("test_run_results_attributes", test_run_results_attributes);


        JsonObject parent=new JsonObject();
        parent.addProperty("auth_token", auth_token);
        parent.addProperty("token", token);
        parent.add("test_run",  test_run);

        Call<ResponseBody> call= easyqaUserAPI.createTestRun(parent);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        if (result!=null){
            System.out.println(result);

            if (result.startsWith("{")) {
                JSONObject jsonObj = new JSONObject(result);
                id_value = jsonObj.getInt("id");
                System.out.println("Test run is created! id=" + id_value);

            }else {
                System.out.println(result);
            }
        }else {
            System.out.println("Something went wrong");
        }
        return id_value;
    }

    /**
     * Method for creating a test run with selected test cases on Test Activities page within your project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param auth_token your authorization token in EasyQA
     * @param test_case_ids the array of ids of the test cases
     * @param title a name of your test run
     * @param assigner_id the id of the project member, with role owner, admin, project_admin or tester
     * @param data additional parameters for a test run, can be description
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return id of the created test run
     */
    public Integer createSelectTestCases(String token, String auth_token, String title, Integer assigner_id,
                                         Integer test_case_ids[],
                                             String... data) throws IOException, JSONException {

        JsonObject test_run = new GetNotRequiredParameters().getIntoJSON(data);
        test_run.addProperty("title", title);
        test_run.addProperty("assigner_id", assigner_id);
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

        Call<ResponseBody> call= easyqaUserAPI.createTestRun(parent);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        if (result!=null){
            System.out.println(result);

            if (result.startsWith("{")) {
                JSONObject jsonObj = new JSONObject(result);
                id_value = jsonObj.getInt("id");
                System.out.println("Test run is created! id=" + id_value);

            }else {
                System.out.println(result);
            }
        }else {
            System.out.println("Something went wrong");
        }
        return id_value;
    }
}
