package use_case.make_response;

import entity.Answer;
import entity.Question;
import entity.Response;
import entity.Survey;
import use_case.get_survey.GetSurveyDataAccessInterface;

public class MakeResponseInteractor implements MakeResponseInputBoundary {

    private final MakeResponseDataAccessInterface responseDAO;

    private final MakeResponseOutputBoundary presenter;

    public MakeResponseInteractor(MakeResponseDataAccessInterface responseDAO,
                                  MakeResponseOutputBoundary presenter) {
        this.responseDAO = responseDAO;
        this.presenter = presenter;
    }

    private void validate(MakeResponseInputData inputData) {


    }

    @Override
    public void execute(MakeResponseInputData inputData) {
        /*
        1. Validate against Survey
        2. Check all required questions are answered
        3. Interface with the DB; done.
         */

        // These two are supposed to have the same size questions/answers, as per Controller
        Survey survey = inputData.getSurvey();
        Response response = inputData.getResponse();

        for (int i = 0; i < survey.getQuestions().size(); i++) {
            Question question = survey.getQuestions().get(i);
            Answer answer = response.getAnswers().get(i);

            if (question.answerIsEmpty(answer)) {
                presenter.prepareFailView(String.format("Required question %d unanswered.", i + 1));
                return;
            }

            if (!question.validateAnswer(answer)) {
                presenter.prepareFailView(String.format("Question %d answer format invalid.", i + 1));
                return;
            }
        }

        // Finally attempt save...
        try {
            responseDAO.saveResponse(inputData.getResponse(), inputData.getSurveyId());
        }
        catch (RuntimeException e) {
            presenter.prepareFailView(e.getMessage());
        }

        // Done :)
        presenter.prepareSuccessView(new MakeResponseOutputData(false));
    }
}
