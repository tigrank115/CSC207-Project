package entity;

import java.util.ArrayList;

public class AnswerFactory {
    public static Answer<?> createAnswer(AnswerType type, Object input) {
        return switch (type) {
            case AnswerType.TEXT -> new Answer<String>((String) input);
            case AnswerType.SINGLE_CHOICE -> new Answer<Integer>((Integer) input);

            // TODO: Need to appropriately handle MC case outside of Factory.
            case AnswerType.MULTIPLE_CHOICE -> new Answer<Integer[]>((Integer[]) input);
        };
    }
}
