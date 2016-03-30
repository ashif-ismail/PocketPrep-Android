package me.ashif.pocketprep;

import android.app.Application;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by almukthar on 14/1/16.
 */

public class PocketPrep extends Application{

    private Exam currentExam;
    public void setCurrentExam(Exam currentExam) {
        this.currentExam = currentExam;
    }
    public Exam getCurrentExam() {
        return currentExam;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Question.class);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "NsvQwd2jpTunUlpsb4SrkzzKOXoPYwiWEOBgg9Ly", "HdSfQm9uDNyCyzxQBlH6jPmy6nvbmxxttuCtAVRz");
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

        ParseQuery<Question> query = ParseQuery.getQuery("question");
        final Application app = this;
        query.findInBackground(new FindCallback<Question>() {
            @Override public void done(List<Question> questions, ParseException e) {
                if (e == null) {
                    //We have updated Questions from the cloud so we will cache them all
                    ParseObject.unpinAllInBackground( "QUESTIONS" );
                    ParseObject.pinAllInBackground( "QUESTIONS", questions);
                } else {
                    //alert the user that unable to fetch Questions -this should be more robust in production apps
                    Toast.makeText(app,
                            "Error updating Questions - please make sure you have internet connection",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
