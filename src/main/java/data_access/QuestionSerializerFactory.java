package data_access;

import entity.MultipleChoiceQuestion;
import entity.Question;
import entity.TextQuestion;

import java.util.HashMap;
import java.util.Map;

public class QuestionSerializerFactory {
    private final Map<Class<? extends Question>, QuestionSerializer> questionToSerializer;
    private final Map<String, QuestionSerializer> stringToSerializer;

    public QuestionSerializerFactory() {
        questionToSerializer = new HashMap<>();
        stringToSerializer = new HashMap<>();

        // Map Question subclasses to corresponding converters
        addSerializer(TextQuestion.class, new TextQuestionSerializer());
        addSerializer(MultipleChoiceQuestion.class, new MultipleChoiceQuestionSerializer());
    }

    private void addSerializer(Class<? extends Question> questionClass,
                               QuestionSerializer serializer) {
        questionToSerializer.put(questionClass, serializer);
        stringToSerializer.put(questionClass.getSimpleName(), serializer);
    }

    public QuestionSerializer getSerializer(Question question) {
        return questionToSerializer.get(question.getClass());
    }

    public QuestionSerializer getSerializer(String questionType) {
        return stringToSerializer.get(questionType);
    }
}
