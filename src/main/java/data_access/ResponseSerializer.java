package data_access;

import entity.Answer;
import entity.AnswerType;
import entity.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Temporary class holding the foundational entity JSON conversion.
 */
public class ResponseSerializer {

    public static JSONObject responseToJson(Response response) {

        // Create array of answers with their types
        JSONArray answerArray = new JSONArray();
        for (Answer answer : response.getAnswers()) {
            JSONObject answerObject = new JSONObject();
            answerObject.put("answerType", answer.getAnswerType().name());

            JSONArray subAnswers = new JSONArray(answer.getUserInput());
            answerObject.put("userInput", subAnswers);

            answerArray.put(answerObject);
        }

        // Create master object holding the answers
        JSONObject responseObject = new JSONObject();
        responseObject.put("answers", answerArray);

        return responseObject;
    }

    public static Answer jsonToAnswer(JSONObject answer) {
        try {
            String answerType = answer.getString("answerType");
            JSONArray inputArray = answer.getJSONArray("userInput");

            // Parse/initialize resulting stuff
            AnswerType type = AnswerType.valueOf(answerType);
            String[] userInput = new String[inputArray.length()];

            ArrayList<String> userInputBuffer = new ArrayList<>();
            for (int i = 0; i < inputArray.length(); i++) {
                String nextAnswer = inputArray.getString(i);
                userInputBuffer.add(nextAnswer);
            }

            return new Answer(type, userInputBuffer.toArray(userInput));
        }
        catch (JSONException e) {
            throw new RuntimeException(e);
        }
        catch (IllegalArgumentException e) {
            throw new RuntimeException("Answer: Invalid answer type");
        }
    }

    public static Response jsonToResponse(JSONObject response) {
        Response result = new Response();

        try {
            JSONArray answers = new JSONArray(response.getJSONArray("answers"));
            for (int i = 0; i < answers.length(); i++) {
                JSONObject answerObject = answers.getJSONObject(i);
                result.addAnswer(jsonToAnswer(answerObject));
            }
        }
        catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

}
