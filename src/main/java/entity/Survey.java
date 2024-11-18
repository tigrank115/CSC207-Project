package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Survey {

    private String name;
    private List<Question> questions;

    private Date closeDate;
    private boolean active;

    public Survey(String name) {
        this.name = name;
        this.active = true;
        this.questions = new ArrayList<Question>();
    }

    public String getName() {
        return name;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public List<Question> getQuestions() {
        return new ArrayList<>(questions);
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date date) {
        closeDate = date;
    }

    public boolean isActive() {
        return active;
    }

    // Temporary set method, need to automate on initialization using closeDate!
    public void setActive(boolean active) {
        this.active = active;
    }

}
