package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.InMemorySurveyDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import interface_adapter.ResetPassword.ResetPasswordViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_survey.CreateSurveyViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.respond.RespondToASurveyController;
import interface_adapter.respond.RespondToASurveyPresenter;
import interface_adapter.respond.RespondToASurveyViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.surveyresponse.SurveyResponseViewModel;
import use_case.get_survey.GetSurveyInputBoundary;
import use_case.get_survey.GetSurveyInteractor;
import use_case.get_survey.GetSurveyOutputBoundary;
import view.*;

/**
 * The version of Main with an external database used to persist user data.
 */
public class MainWithInMemory {

    /**
     * The main method for starting the program with an external database used to persist user data.
     * @param args input to main
     */
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        final JFrame application = new JFrame("Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        final JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        final ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are "observable", and will
        // be "observed" by the Views.
        final LoginViewModel loginViewModel = new LoginViewModel();
        final LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        final SignupViewModel signupViewModel = new SignupViewModel();
        final ResetPasswordViewModel resetPasswordViewModel = new ResetPasswordViewModel();
        final CreateSurveyViewModel createSurveyViewModel = new CreateSurveyViewModel();

        final InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();

        final SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel,
                                                                  signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.getViewName());

        final LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel,
                signupViewModel, userDataAccessObject);
        views.add(loginView, loginView.getViewName());

        InMemorySurveyDataAccessObject dbSurveyDAO = new InMemorySurveyDataAccessObject();
        SurveyResponseViewModel responseVM = new SurveyResponseViewModel();
        final RespondToASurveyViewModel idVM = new RespondToASurveyViewModel();
        final GetSurveyOutputBoundary idPres = new RespondToASurveyPresenter(viewManagerModel, responseVM, idVM);
        final GetSurveyInputBoundary getSurveyInteractor = new GetSurveyInteractor(dbSurveyDAO, idPres);
        final RespondToASurveyController idController = new RespondToASurveyController(getSurveyInteractor);
        final RespondToASurveyView idView = new RespondToASurveyView(idController, idVM);

        final LoggedInView loggedInView = ChangePasswordUseCaseFactory.create(viewManagerModel, loggedInViewModel,
                resetPasswordViewModel, userDataAccessObject, userDataAccessObject, loginViewModel, idVM, createSurveyViewModel);
        views.add(loggedInView, loggedInView.getViewName());

        viewManagerModel.setState(signupView.getViewName());
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
