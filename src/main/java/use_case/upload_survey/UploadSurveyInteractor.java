package use_case.upload_survey;

import use_case.login.LoginOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;

public class UploadSurveyInteractor implements UploadSurveyInputBoundary {
    private final UploadSurveyDataAccessInterface surveyDAO;
    private final UploadSurveyOutputBoundary createSurveyPresenter;

    public UploadSurveyInteractor(UploadSurveyDataAccessInterface surveyDAO,
                           UploadSurveyOutputBoundary createSurveyPresenter) {
        this.surveyDAO = surveyDAO;
        this.createSurveyPresenter = createSurveyPresenter;
    }

    @Override
    public void execute(UploadSurveyInputData inputData) {
        try {
            String surveyId = surveyDAO.saveSurvey(inputData.getSurvey());
            createSurveyPresenter.prepareSuccessView(new UploadSurveyOutputData(
                    surveyId,
                    false));
        }
        catch (Exception e) {
            createSurveyPresenter.prepareFailView(e.getMessage());
        }
    }
}
