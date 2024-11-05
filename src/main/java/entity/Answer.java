package entity;

public class Answer<T> {

    private T userInput;

    public Answer(T userInput) {
        this.userInput = userInput;
    }

    public T getUserInput() {
        return userInput;
    }
}
