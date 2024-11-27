package use_case.getmysurveys;

import use_case.change_password.ChangePasswordInputData;

/**
 * The Get My Surveys Use Case.
 */
public interface GetMySurveysInputBoundary {

    void execute(GetMySurveysInputData getmysurveysInputData);
}
