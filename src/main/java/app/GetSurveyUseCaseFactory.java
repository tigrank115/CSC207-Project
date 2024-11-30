//package app;
//
//import interface_adapter.ViewManagerModel;
//import interface_adapter.login.LoginController;
//import interface_adapter.respond.RespondToASurveyController;
//import interface_adapter.respond.RespondToASurveyPresenter;
//import interface_adapter.respond.RespondToASurveyViewModel;
//import interface_adapter.surveyresponse.SurveyResponseViewModel;
//import use_case.get_survey.GetSurveyDataAccessInterface;
//import use_case.get_survey.GetSurveyInputBoundary;
//import use_case.get_survey.GetSurveyInteractor;
//import use_case.get_survey.GetSurveyOutputBoundary;
//import view.LoginView;
//import view.RespondToASurveyView;
//
///**
// * This class contains the static factory function for creating the LoginView.
// */
//public final class GetSurveyUseCaseFactory {
//
//    /** Prevent instantiation. */
//    private GetSurveyUseCaseFactory() {
//
//    }
//
//    public static RespondToASurveyView create(
//            ViewManagerModel viewManagerModel,
//            RespondToASurveyViewModel idViewModel,
//            SurveyResponseViewModel respondViewModel,
//            GetSurveyDataAccessInterface surveyDAO) {
//
//        final RespondToASurveyController idController = createIdUseCase(viewManagerModel, idViewModel,
//                respondViewModel, surveyDAO);
//        return new RespondToASurveyView(idViewModel, idController);
//
//    }
//
//    private static RespondToASurveyController createIdUseCase(
//            ViewManagerModel viewManagerModel,
//            RespondToASurveyViewModel idViewModel,
//            SurveyResponseViewModel respondViewModel,
//            GetSurveyDataAccessInterface surveyDAO) {
//
//        // Notice how we pass this method's parameters to the Presenter.
//        final GetSurveyOutputBoundary idPresenter = new RespondToASurveyPresenter(viewManagerModel,
//                idViewModel, respondViewModel);
//        final GetSurveyInputBoundary idInteractor = new GetSurveyInteractor(surveyDAO, idPresenter);
//
//        return new RespondToASurveyController(idInteractor);
//    }
//}
