package use_case;

import entity.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class SampleDataGenerator {
    public static Survey newSurvey() {
        Survey survey = new Survey("Mood Survey");
        survey.addQuestion(new MultipleChoiceQuestion(
                "How are you?",
                Arrays.asList("Good", "So-so", "Bad"),
                true,
                true
        ));
        survey.addQuestion(new TextQuestion(
                "Describe yourself.",
                true
        ));
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.JANUARY, 1, 0, 0, 0);
        survey.setCloseDate(calendar.getTime());
        return survey;
    }

    public static Response newResponse() {
        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer(AnswerType.SINGLE_CHOICE, new String[]{"Good"}));
        answers.add(new Answer(AnswerType.TEXT, new String[]{"Everything is peachy."}));

        return new Response(answers);
    }
}
