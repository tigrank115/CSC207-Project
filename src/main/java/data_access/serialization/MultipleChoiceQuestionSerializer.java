package data_access.serialization;

import entity.MultipleChoiceQuestion;
import entity.Question;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MultipleChoiceQuestionSerializer extends QuestionSerializer {

    public JSONObject toJson(Question question) {
        if (!(question instanceof MultipleChoiceQuestion)) {
            throw new IllegalArgumentException("Question must be of type MultipleChoiceQuestion");
        }

        JSONObject resultObject = MultipleChoiceQuestionSerializer.baseToJson(question);
        resultObject.put("questionType", "MultipleChoiceQuestion");

        // Serialize options. Whether it's single-choice is determined by its AnswerType
        JSONArray optionsArray = new JSONArray();
        for (String option : ((MultipleChoiceQuestion) question).getOptions()) {
            optionsArray.put(option);
        }
        resultObject.put("options", optionsArray);

        return resultObject;
    }

    public Question fromJson(JSONObject jsonObject) {
        String questionType = jsonObject.getString("questionType");
        if (!"MultipleChoiceQuestion".equals(questionType)) {
            throw new IllegalArgumentException("Expected questionType TextQuestion");
        }

        ArrayList<String> resultOptions = new ArrayList<>();
        JSONArray optionsArray = jsonObject.getJSONArray("options");
        for (int i = 0; i < optionsArray.length(); i++) {
            String option = optionsArray.getString(i);
            resultOptions.add(option);
        }

        boolean resultSingleAnswer = "SINGLE_CHOICE".equals(jsonObject.getString("answerType"));
        return new MultipleChoiceQuestion(jsonObject.getString("prompt"),
                resultOptions,
                resultSingleAnswer,
                jsonObject.getBoolean("isRequired"));
    }
}
