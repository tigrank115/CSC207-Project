package entity;

import java.util.ArrayList;
import java.util.List;

// This class is assumed to be a complete, valid response.
public class Response {

    // These asnwers will reflect Survey Questions, guaranteed by the use cases.
    private List<Answer> answers;

    public Response() {
        this.answers = new ArrayList<Answer>();
    }

    public Response(List<Answer> answers) {
        this.answers = answers;
    }

    public Response addAnswer(Answer answer) {
        this.answers.add(answer);
        return this;
    }

    public List<Answer> getAnswers() {
        return new ArrayList<>(answers);
    }
}
