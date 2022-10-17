package stuthemp.utils;

import net.minidev.json.JSONObject;
import stuthemp.model.Question;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

/**
 * todo Document type JsonConverter
 */
public class JsonConverter {

    public static String toJSON(Question question, String creatorName){
        String str="";
        JSONObject jsonObject = new JSONObject();
        try {
            for (Field filed : question.getClass().getDeclaredFields()) {
                filed.setAccessible(true);
                String name = filed.getName();
                String value = String.valueOf(filed.get(question));
                jsonObject.put(name, value);
            }
        } catch (IllegalAccessException e) {
            System.out.println("IllegalAccess");
        }
        String date = LocalDateTime.now().toString();
        jsonObject.put("createdAt",date);
        jsonObject.put("createdBy",creatorName);
        System.out.println(jsonObject.toString());
        return jsonObject.toString();
    }
}
