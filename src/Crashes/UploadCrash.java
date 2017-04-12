package Crashes;

import API.Initialization;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import okhttp3.ResponseBody;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Response;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by yanagusti on 3/17/17.
 */
public class UploadCrash extends Initialization {


    public UploadCrash(String url) {
        super(url);
    }

    /**
     * Method for uploading a crash which was appeared on mobile phone
     * @param token of your project in EasyQA. You can generate it on Integrations page
     * @param packageName name of application package
     * @param buildVersionCode code version of your build
     * @param buildVersionName version name of your build
     * @param deviceSerial serial number of your device
     * @param deviceModel model name of you device
     * @param osVersion operation system version of your phone
     * @param createdAt date of the crash creation
     * @param file log file of crash
     * @throws IOException for incorrect parsing of the server response
     * @throws JSONException if the server returns not Json object
     * @return success message or the server response
     */

    public String uploadCrash(String token, String packageName, String buildVersionCode, String buildVersionName
            , String deviceSerial, String deviceModel, String osVersion, String createdAt, File file) throws IOException, JSONException {
        JsonObject cred = new JsonObject();
        cred.addProperty("token", token);
        cred.addProperty("packageName", packageName);
        cred.addProperty("buildVersionCode", buildVersionCode);
        cred.addProperty("buildVersionName", buildVersionName);
        cred.addProperty("deviceSerial", deviceSerial);
        cred.addProperty("deviceModel", deviceModel);
        cred.addProperty("osVersion", osVersion);



        JsonObject crashesData = new JsonObject();

        String logFile = encodeFileToBase64Binary(file);

        crashesData.addProperty("createdAt", createdAt);
        crashesData.addProperty("fileName", file.getName());
        crashesData.addProperty("logFile", logFile);
        JsonArray data = new JsonArray();
        data.add(crashesData);
        cred.add("crashesData", data);

        System.out.println(cred);

        Call<ResponseBody> call= easyqaUserAPI.uploadCrash(cred);

        Response<ResponseBody> bodyResponse = call.execute();
        String result = bodyResponse.body().string();
        System.out.println(result);


        if (result.startsWith("{")) {
            JSONObject jsonObj = new JSONObject(result);
            String message = jsonObj.getString("message");
            System.out.println("Upload crash=" + message);
            return message;

        }else {
            System.out.println(result);
            return result;
        }

    }

    /**
     * Method for encoding log file to Base64
     * @param file log file of crash
     * @throws IOException for incorrect parsing of the server response
     * @return encoded string of your crash log file
     */
    private String encodeFileToBase64Binary(File file) throws IOException {

        byte[] bytes = loadFile(file);
        byte[] encoded = Base64.encodeBase64(bytes);
        String encodedString = new String(encoded);

        return encodedString;
    }

    /**
     * Method for converting log file into bytes
     * @param file log file of your crash
     * @throws IOException for incorrect parsing of the server response
     * @return log file bytes
     */

    private static byte[] loadFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
        byte[] bytes = new byte[(int)length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }

        is.close();
        return bytes;
    }
}
