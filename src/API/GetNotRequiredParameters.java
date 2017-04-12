package API;

import com.google.gson.JsonObject;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.util.*;

/**
 * Created by yanagusti on 3/31/17.
 */
public class GetNotRequiredParameters {

    /**
     * Convert optional parameters into Map with String key and RequestBody value
     * @param mediaType media type for parsing optional data
     * @param data optional data
     * @return Map
     */

    public Map<String, RequestBody> getIntoMap(String mediaType, String... data) {

        Map<String, RequestBody> model = new HashMap<>();

        List<String> keys = new ArrayList<>();
        List<RequestBody> values = new ArrayList<>();

        if (data.length!=0) {

            for (int i = 0; i < data.length; i++) {

                if (i % 2 == 0) {
                    keys.add(data[i]);
                }

            }

            for (int j = 0; j < data.length; j++) {

                if (j % 2 == 1) {
                    values.add(RequestBody.create(MediaType.parse(mediaType), data[j]));
                }

            }


            for (int a = 0; a < keys.size(); a++) {
                model.put(keys.get(a), values.get(a));
            }
        }

      return model;
    }

    /**
     * Convert optional parameters into JsonObject
     * @param data optional data
     * @return JsonObject
     */
    public JsonObject getIntoJSON(String... data) {

        JsonObject model = new JsonObject();

        List<String> keys = new ArrayList<>();
        List<String> values = new ArrayList<>();

        if (data.length!=0) {

            for (int i = 0; i < data.length; i++) {

                if (i % 2 == 0) {
                    keys.add(data[i]);
                }

            }

            for (int j = 0; j < data.length; j++) {

                if (j % 2 == 1) {
                    values.add(data[j]);
                }

            }


            for (int a = 0; a < keys.size(); a++) {
                model.addProperty(keys.get(a), values.get(a));
            }
        }

        return model;
    }

}
