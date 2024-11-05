package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Survey {

    private String name;
    private List<Question> questions;

    private Date closeDate;
    private boolean active;

    public Survey() {
        this.active = true;
        this.questions = new ArrayList<Question>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
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

}
