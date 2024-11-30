package data_access;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import data_access.database_connection.FirebaseConnectionFactory;
import data_access.serialization.ResponseSerializer;
import data_access.serialization.SurveySerializer;
import entity.Response;
import entity.Survey;
import org.json.JSONObject;
import use_case.get_responses.GetResponsesDataAccessInterface;
import use_case.get_survey.GetSurveyDataAccessInterface;
import use_case.make_response.MakeResponseDataAccessInterface;
import use_case.upload_survey.UploadSurveyDataAccessInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class DBSurveyDataAccessObject implements
        UploadSurveyDataAccessInterface,
        GetSurveyDataAccessInterface,
        MakeResponseDataAccessInterface,
        GetResponsesDataAccessInterface {

    public DBSurveyDataAccessObject() {
    }

    @Override
    public Survey getSurvey(String id) {
        DocumentReference ref = FirebaseConnectionFactory.getFirestore().collection("surveys").document(id);
        try {
            DocumentSnapshot refSnapshot = ref.get().get();
            if (!refSnapshot.exists()) {
                return null;
            }
            JSONObject snapshotObject = new JSONObject(refSnapshot.getData());
            return SurveySerializer.jsonToSurvey(snapshotObject);
        }
        catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String saveSurvey(Survey survey) {
        Map<String, Object> surveyMap = SurveySerializer.surveyToJson(survey).toMap();

        CollectionReference surveys = FirebaseConnectionFactory.getFirestore().collection("surveys");

        ApiFuture<DocumentReference> future = surveys.add(surveyMap);

        try {
            DocumentReference newSurvey = future.get();
            return newSurvey.getId();
        }
        catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveResponse(Response response, String surveyId) {

        if (getSurvey(surveyId) == null) {
            throw new RuntimeException("No open survey with id " + surveyId);
        }

        DocumentReference ref = FirebaseConnectionFactory.getFirestore()
                .collection("surveys")
                .document(surveyId);

        // Create default empty collection if necessary.
        JSONObject responseObject = ResponseSerializer.responseToJson(response);
        ref.collection("responses").add(responseObject);
    }

    @Override
    public List<Response> getResponses(String surveyId) {
        ApiFuture<QuerySnapshot> querySnapshot =
                FirebaseConnectionFactory.getFirestore()
                        .collection("surveys")
                        .document(surveyId)
                        .collection("responses")
                        .get();

        try {
            if (querySnapshot.get().isEmpty()) {
                // Survey isn't popular enough for collection to have been automatically generated
                return new ArrayList<>();
            }

            ArrayList<Response> responses = new ArrayList<>();

            // Extract everything!
            querySnapshot.get().getDocuments().forEach(documentSnapshot -> {
                JSONObject responseObject = new JSONObject(documentSnapshot.getData());
                responses.add(ResponseSerializer.jsonToResponse(responseObject));
            });

            return responses;
        }
        catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

    }
}
