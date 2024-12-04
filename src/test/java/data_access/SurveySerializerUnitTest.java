package data_access;

import data_access.serialization.SurveySerializer;
import entity.MultipleChoiceQuestion;
import entity.Question;
import entity.Survey;
import entity.TextQuestion;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import use_case.SampleDataGenerator;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SurveySerializerUnitTest {

    @Test
    public void surveyToJsonTest() {
        Survey survey = SampleDataGenerator.newSurvey();

        JSONObject result = SurveySerializer.surveyToJson(survey);

        assertTrue(result.has("name") &&
                result.has("questions") &&
                result.has("closeDate") &&
                result.has("isActive"));

        // Assert all survey properties exist
        assertEquals("2025-01-01T00:00:00",
                result.getString("closeDate"));
        assertEquals("Mood Survey", result.getString("name"));
        assertTrue(result.getBoolean("isActive"));

        // Assert all question properties exist
        JSONArray questions = result.getJSONArray("questions");
        assertEquals(2, questions.length());
        for (int i = 0; i < questions.length(); i++) {
            JSONObject question = questions.getJSONObject(i);
            assertTrue(question.has("isRequired"));
            assertTrue(question.has("prompt"));
            assertTrue(question.has("answerType"));
        }

        // Test individual Question properties
        assertTrue(questions.getJSONObject(0).getBoolean("isRequired"));
        assertEquals("How are you?",
                questions.getJSONObject(0).getString("prompt"));
        assertEquals("SINGLE_CHOICE",
                questions.getJSONObject(0).getString("answerType"));

        assertTrue(questions.getJSONObject(1).getBoolean("isRequired"));
        assertEquals("Describe yourself.",
                questions.getJSONObject(1).getString("prompt"));
        assertEquals("TEXT",
                questions.getJSONObject(1).getString("answerType"));

    }

    @Test
    public void jsonToSurveyTest() {
        String input = "{\"closeDate\":\"2025-01-01T00:00:00\",\"name\":\"Mood Survey\",\"questions\":[{\"isRequired\":true,\"answerType\":\"MULTIPLE_CHOICE\",\"options\":[\"Good\",\"So-so\",\"Bad\"],\"prompt\":\"How are you?\",\"questionType\":\"MultipleChoiceQuestion\"},{\"isRequired\":true,\"answerType\":\"TEXT\",\"prompt\":\"Describe yourself.\",\"questionType\":\"TextQuestion\"}],\"isActive\":true}";

        JSONObject inputObject = new JSONObject(input);
        Survey result = SurveySerializer.jsonToSurvey(inputObject);

        try {
            Date expectedDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    .parse("2025-01-01T00:00:00");
            assertEquals(result.getCloseDate(), expectedDate);
        }
        catch (ParseException e) {
            throw new RuntimeException(e);
        }

        // Survey properties
        assertEquals("Mood Survey", result.getName());
        assertTrue(result.isActive());
        assertEquals(2, result.getQuestions().size());


        Question questionOne = result.getQuestions().get(0);
        Question questionTwo = result.getQuestions().get(1);

        // Question 1
        assertInstanceOf(MultipleChoiceQuestion.class, questionOne);
        assertEquals("MULTIPLE_CHOICE", questionOne.getAnswerType().name());
        List<String> actualOptions = ((MultipleChoiceQuestion)questionOne).getOptions();
        assertEquals(3, actualOptions.size());

        assertEquals("Good", actualOptions.get(0));
        assertEquals("So-so", actualOptions.get(1));
        assertEquals("Bad", actualOptions.get(2));

        assertTrue(questionOne.isRequired());

        // Question 2
        assertInstanceOf(TextQuestion.class, questionTwo);
        assertEquals("TEXT", questionTwo.getAnswerType().name());
        assertTrue(questionTwo.isRequired());

    }
}
