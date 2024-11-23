package data_access;

import entity.Question;
import org.json.JSONObject;

public abstract class QuestionSerializer {

    protected static JSONObject baseToJson(Question question) {
        JSONObject resultObject = new JSONObject();
        resultObject.put("prompt", question.getPrompt());
        resultObject.put("isRequired", question.isRequired());
        resultObject.put("answerType", question.getAnswerType().name());
        return resultObject;
    }

    public abstract JSONObject toJson(Question question);
    public abstract Question fromJson(JSONObject jsonObject);
}
