package data_access;

import entity.MultipleChoiceQuestion;
import entity.Survey;
import entity.TextQuestion;
import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class BasicGetSurveyTest {

    @Test
    public void testGetSurvey() {
        DBSurveyDataAccessObject surveyDAO = new DBSurveyDataAccessObject();
        Survey result = surveyDAO.getSurvey("gDKNMUNCz1ZqWsrymkBM");
        Survey result2 = surveyDAO.getSurvey("jGzkCkrCJ8SMT7p1xctt");
        assertNotNull(result);
        assertNotNull(result2);
    }

    @Test
    public void testSetSurvey() {
        Survey survey = new Survey("Mood Survey");
        survey.addQuestion(new MultipleChoiceQuestion(
                "How are you?",
                Arrays.asList("Good", "So-so", "Bad"),
                false,
                true
        ));
        survey.addQuestion(new TextQuestion(
                "Describe yourself.",
                true
        ));
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.JANUARY, 1, 0, 0, 0);
        survey.setCloseDate(calendar.getTime());

        DBSurveyDataAccessObject surveyDAO = new DBSurveyDataAccessObject();
        surveyDAO.saveSurvey(survey);
    }
}
