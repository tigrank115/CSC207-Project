package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_survey.CreateSurveyController;
import interface_adapter.create_survey.CreateSurveyPresenter;
import interface_adapter.create_survey.CreateSurveyViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;
import use_case.upload_survey.UploadSurveyDataAccessInterface;
import use_case.upload_survey.UploadSurveyInteractor;
import use_case.upload_survey.UploadSurveyOutputBoundary;
import view.CreateSurveyView;
import view.LoginView;

/**
 * This class contains the static factory function for creating the LoginView.
 */
public final class CreateSurveyUseCaseFactory {

    /** Prevent instantiation. */
    private CreateSurveyUseCaseFactory() {

    }

    public static CreateSurveyView create(
            ViewManagerModel viewManagerModel,
            CreateSurveyViewModel createSurveyViewModel,
            LoggedInViewModel loggedInViewModel,
            UploadSurveyDataAccessInterface surveyDAO) {

        final CreateSurveyController createSurveyController = createSurveyUseCase(
                viewManagerModel,
                createSurveyViewModel,
                loggedInViewModel,
                surveyDAO);
        return new CreateSurveyView(createSurveyViewModel, createSurveyController);
    }

    private static CreateSurveyController createSurveyUseCase(
            ViewManagerModel viewManagerModel,
            CreateSurveyViewModel createSurveyViewModel,
            LoggedInViewModel loggedInViewModel,
            UploadSurveyDataAccessInterface surveyDAO) {

        final UploadSurveyOutputBoundary csOutputBoundary =
                new CreateSurveyPresenter(viewManagerModel, createSurveyViewModel, loggedInViewModel);
        final UploadSurveyInteractor csInteractor = new UploadSurveyInteractor(surveyDAO, csOutputBoundary);

        return new CreateSurveyController(csInteractor, (CreateSurveyPresenter) csOutputBoundary);
    }
}
