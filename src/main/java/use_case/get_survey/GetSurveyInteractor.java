package use_case.get_survey;

import entity.Survey;
import use_case.upload_survey.UploadSurveyDataAccessInterface;
import use_case.upload_survey.UploadSurveyOutputBoundary;
import use_case.upload_survey.UploadSurveyOutputData;

public class GetSurveyInteractor implements GetSurveyInputBoundary {

    private final GetSurveyDataAccessInterface surveyDAO;
    private final GetSurveyOutputBoundary getSurveyPresenter;

    public GetSurveyInteractor(GetSurveyDataAccessInterface surveyDAO,
                               GetSurveyOutputBoundary getSurveyPresenter) {
        this.surveyDAO = surveyDAO;
        this.getSurveyPresenter = getSurveyPresenter;
    }

    @Override
    public void execute(GetSurveyInputData inputData) {
        Survey result = surveyDAO.getSurvey(inputData.getRequestId());
        if (result == null) {
            getSurveyPresenter.prepareFailView("No survey found with id " + inputData.getRequestId() + ".");
            return;
        }
        getSurveyPresenter.prepareSuccessView(new GetSurveyOutputData(result));
    }

    @Override
    public void switchToResponseView(GetSurveyInputData inputData, String username) {
        Survey result = surveyDAO.getSurvey(inputData.getRequestId());
        if (result == null) {
            getSurveyPresenter.prepareFailView("No survey found with id " + inputData.getRequestId() + ".");
            return;
        }
        getSurveyPresenter.switchToResponseView(username, new GetSurveyOutputData(result));
    }


}
