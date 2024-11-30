//package data_access.database_connection;
//
//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.cloud.firestore.Firestore;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.FirebaseOptions;
//import com.google.firebase.cloud.FirestoreClient;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//
//public class FirebaseConnectionFactory {
//    private static final Firestore firestore;
//
//    static {
//        try {
//            FileInputStream privateKey = new FileInputStream(
//                    "src/main/java/data_access/database_connection/csc207-group-51-firebase-adminsdk-6dcny-cedf1709d9.json"
//            );
//
//            FirebaseOptions options = FirebaseOptions.builder()
//                    .setCredentials(GoogleCredentials.fromStream(privateKey))
//                    .setDatabaseUrl("https://csc207-group-51.firebaseio.com/")
//                    .build();
//            FirebaseApp.initializeApp(options);
//            firestore = FirestoreClient.getFirestore();
//            System.out.println("[✓] Firestore client initialized");
//
//        }
//        catch(IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    public static Firestore getFirestore() {
//        return firestore;
//    }
//}
