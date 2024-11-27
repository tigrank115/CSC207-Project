package data_access.serialization;

import entity.Question;
import entity.TextQuestion;
import org.json.JSONObject;

public class TextQuestionSerializer extends QuestionSerializer {

    public JSONObject toJson(Question question) {
        if (!(question instanceof TextQuestion)) {
            throw new IllegalArgumentException("Question must be of type TextQuestion");
        }
        TextQuestion textQuestion = (TextQuestion) question;

        JSONObject resultObject = baseToJson(textQuestion);
        resultObject.put("questionType", "TextQuestion");

        // There is no extra info to be serialized here
        return resultObject;
    }

    public Question fromJson(JSONObject jsonObject) {
        String questionType = jsonObject.getString("questionType");
        if (!"TextQuestion".equals(questionType)) {
            throw new IllegalArgumentException("Expected questionType TextQuestion");
        }

        return new TextQuestion(jsonObject.getString("prompt"),
                jsonObject.getBoolean("isRequired"));
    }
}
