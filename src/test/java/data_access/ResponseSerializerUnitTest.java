package data_access;

import data_access.serialization.ResponseSerializer;
import entity.Answer;
import entity.AnswerType;
import entity.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;


public class ResponseSerializerUnitTest {

    @Test
    public void responseToJsonTest() {
        ArrayList<Answer> input = new ArrayList<Answer>();
        input.add(new Answer(AnswerType.TEXT, new String[]{"Hello, World"}));
        input.add(new Answer(AnswerType.TEXT,
                new String[]{"Hello, There", "Foo Bar Baz"}));

        Response response = new Response(input);

        JSONObject actual = ResponseSerializer.responseToJson(response);

        assertEquals("{\"answers\":[{\"answerType\":\"TEXT\",\"userInput\":[\"Hello, World\"]},{\"answerType\":\"TEXT\",\"userInput\":[\"Hello, There\",\"Foo Bar Baz\"]}]}", actual.toString());
    }

    @Test
    public void jsonToAnswerTest() {
        JSONObject input = new JSONObject("{\"answerType\":\"SINGLE_CHOICE\",\"userInput\":[\"21\"]}\"}");
        Answer actual = ResponseSerializer.jsonToAnswer(input);

        assertArrayEquals(new String[]{"21"}, actual.getUserInput());
        assertEquals(AnswerType.SINGLE_CHOICE, actual.getAnswerType());
    }

    @Test
    public void jsonToResponseTest() {
        JSONObject json = new JSONObject("{\"answers\":[{\"answerType\":\"MULTIPLE_CHOICE\",\"userInput\":[\"multiple choice here...\"]},{\"answerType\":\"TEXT\",\"userInput\":[\"Hello, There\",\"Foo Bar Baz\"]}]}");
        Response actual = ResponseSerializer.jsonToResponse(json);

        Answer[] expected = new Answer[]{
            new Answer(AnswerType.MULTIPLE_CHOICE, new String[]{"multiple choice here..."}),
            new Answer(AnswerType.TEXT,
                    new String[]{"Hello, There", "Foo Bar Baz"})
        };

        // Test every Answer manually...
        for (int i = 0; i < expected.length; i++) {
            Answer actualAnswer = actual.getAnswers().get(i);
            assertEquals(expected[i].getAnswerType(), actualAnswer.getAnswerType());
            assertArrayEquals(expected[i].getUserInput(), actualAnswer.getUserInput());
        }
    }
}
