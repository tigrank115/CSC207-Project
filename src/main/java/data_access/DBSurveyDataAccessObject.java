package data_access;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import data_access.database_connection.FirebaseConnectionFactory;
import data_access.serialization.SurveySerializer;
import entity.Survey;
import org.json.JSONObject;
import use_case.get_survey.GetSurveyDataAccessInterface;
import use_case.upload_survey.UploadSurveyDataAccessInterface;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public class DBSurveyDataAccessObject implements UploadSurveyDataAccessInterface, GetSurveyDataAccessInterface {

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
            String newSurveyId = newSurvey.getId();
            System.out.println("Survey saved to Firebase (AFAIK) -- Document ID " + newSurvey.getId());
            return newSurveyId;
        }
        catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
