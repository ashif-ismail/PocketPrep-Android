package me.ashif.pocketprep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuestionsActivity extends AppCompatActivity implements View.OnClickListener {

    private Question currentQ;
    private Exam currentExam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);


        currentExam = ((PocketPrep) getApplication()).getCurrentExam();
        currentQ = currentExam.getNextQuestion();
        Button nextBtn = (Button) findViewById(R.id.btnNextQuestion);
        nextBtn.setOnClickListener(this);

        /**
         * Update the current question and answer options..
         */
        setQuestions();

    }


    /**
     * Method to set the text for the question and answers from the current games
     * current question
     */
    private void setQuestions() {
        //set the question text from current question
        String question = currentQ.getQuestion() + "?";
        TextView qText = (TextView) findViewById(R.id.question);
        qText.setText(question);

        //set the available options
        TextView option1 = (TextView) findViewById(R.id.option1);
        option1.setText(currentQ.getOptionOne());

        TextView option2 = (TextView) findViewById(R.id.option2);
        option2.setText(currentQ.getOptionTwo());

        TextView option3 = (TextView) findViewById(R.id.option3);
        option3.setText(currentQ.getOptionThree());

        TextView option4 = (TextView) findViewById(R.id.option4);
        option4.setText(currentQ.getOptionFour());
    }


    @Override
    public void onClick(View v) {
        //validate a checkbox has been selected
        if (!checkAnswer()) return;

        // check if end of game
        if (currentExam.isExamOver()) {

            finish();
        } else {
            Intent i = new Intent(this, QuestionsActivity.class);
            startActivity(i);
            finish();
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    /**
     * Check if a checkbox has been selected, and if it
     * has then check if its correct and update gamescore
     */
    private boolean checkAnswer() {
        RadioGroup options = (RadioGroup) findViewById(R.id.group1);
        Integer selected = options.getCheckedRadioButtonId();
        if (selected < 0) {
            return false;
        } else {
            if (currentQ.getCorrectAnswer() == selected + 1) {
                currentExam.incrementRightAnswers();
            } else {
                currentExam.incrementWrongAnswers();
            }
            return true;
        }
    }
}
