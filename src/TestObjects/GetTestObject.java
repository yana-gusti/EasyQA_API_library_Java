package TestObjects;

import API.GetResponse;
import API.Initialization;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Map;

/**
 * Created by yanagusti on 4/5/17.
 */
public class GetTestObject extends Initialization{

    public GetTestObject(String url) {
        super(url);
    }

    /**
     * Method for getting all links of test objects of the project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return map with ids and links of test objects
     */

    public Map<Integer, String> getTestObjectsLinks(String token) throws IOException, JSONException {
        Call<ResponseBody> call= easyqaUserAPI.getTestObjectLinks(token);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        Map<Integer, String> links = new GetResponse().getMapInteger(result, "id", "link");
        return links;
    }

    /**
     * Method for getting test objects of the project in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param auth_token your authorization token in EasyQA
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return map with ids and links of test objects
     */

    public JSONArray getTestObjectListFullInfo(String token, String auth_token) throws IOException, JSONException {

        Call<ResponseBody> call= easyqaUserAPI.getTestObjecsList(token, auth_token);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        System.out.println(result);
        if (result.startsWith("[")) {
            JSONArray jsondata = new JSONArray(result);
            return jsondata;

        }else {

            return null;
        }

    }

    /**
     * Method for getting test object info by ID in EasyQA
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param auth_token your authorization token in EasyQA
     * @param id the unique ID of the test object
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return JSONObject result of the server response
     */

    public JSONObject getTestObject(String auth_token, String token, String id) throws IOException, JSONException {


        String name;
        Call<ResponseBody> call= easyqaUserAPI.getTestObjec(id, token, auth_token);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        System.out.println(result);
        if (result.startsWith("{")) {

            JSONObject obj1 = new JSONObject(result);
            name = obj1.getString("url");
            System.out.println("Test Object url = "+name);
            return obj1;

        }else {
            System.out.println("Something went wrong");
            return null;
        }

    }
}
