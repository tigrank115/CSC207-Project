package interface_adapter.create_survey;

import entity.*;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class QuestionStateFactory {
    private static Map<Class<? extends Question>, Class<? extends QuestionState>> questionStateMap = new HashMap<>();

    static {
        questionStateMap.put(TextQuestion.class, TextQuestionState.class);
        questionStateMap.put(MultipleChoiceQuestion.class, MultipleChoiceQuestionState.class);
    }

    public static QuestionState getQuestionState(Class<? extends Question> questionClass) {
        Class<? extends QuestionState> questionStateClass = questionStateMap.get(questionClass);
        if (questionStateClass == null) {
            return null;
        }
        try {
            return questionStateClass.getDeclaredConstructor().newInstance();
        }
        catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            return null;
        }
    }
}
