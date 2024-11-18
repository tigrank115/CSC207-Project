package data_access;

import entity.Question;
import entity.Survey;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SurveySerializer {

    public static JSONObject surveyToJson(Survey survey) {
        JSONArray questions = new JSONArray();
        for (Question question : survey.getQuestions()) {
            // TODO: passing in `question` twice like this perhaps redundant
            JSONObject questionObject =
                    (new QuestionSerializerFactory())
                            .getSerializer(question)
                            .toJson(question);

            questions.put(questionObject);
        }

        JSONObject result = new JSONObject();
        result.put("name", survey.getName());
        result.put("questions", questions);

        String serializedDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .format(survey.getCloseDate());
        result.put("closeDate", serializedDate);

        result.put("isActive", survey.isActive());

        return result;
    }

    public static Survey jsonToSurvey(JSONObject jsonObject) {
        try {
            String name = jsonObject.getString("name");
            String closeDateRaw = jsonObject.getString("closeDate");

            Date resultDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    .parse(closeDateRaw);

            boolean active = jsonObject.getBoolean("isActive");

            // Set everything before Questions
            Survey result = new Survey(name);
            result.setActive(active);
            result.setCloseDate(resultDate);

            JSONArray questionsArray = jsonObject.getJSONArray("questions");
            for (int i = 0; i < questionsArray.length(); i++) {
                JSONObject questionObject = questionsArray.getJSONObject(i);
                String questionType = questionObject.getString("questionType");

                // Get Question from Factory using String
                Question resultQuestion =
                        (new QuestionSerializerFactory())
                                .getSerializer(questionType)
                                .fromJson(questionObject);
                result.addQuestion(resultQuestion);
            }

            return result;
        }
        catch (JSONException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
