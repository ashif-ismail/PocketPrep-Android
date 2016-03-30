package me.ashif.pocketprep;
import java.util.ArrayList;
import java.util.List;

/**
 * by ashif
 *
 */
public class Exam {
    private int numRounds;
    private int right=0;
    private int wrong=0;
    private int round=0;

    private List<Question> questions = new ArrayList<Question>();
    public int getRight() {
        return right;
    }
    public void setRight(int right) {
        this.right = right;
    }
    public int getWrong() {
        return wrong;
    }
    public void setWrong(int wrong) {
        this.wrong = wrong;
    }
    public int getRound() {
        return round;
    }
    public void setRound(int round) {
        this.round = round;
    }
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
    public List<Question> getQuestions() {
        return questions;
    }

    public Question getNextQuestion(){
        //get the question
        Question next = questions.get(this.getRound());
        //update the round number to the next round
        this.setRound(this.getRound()+1);
        return next;
    }

    public void incrementRightAnswers(){
        right ++;
    }
    public void incrementWrongAnswers(){
        wrong ++;
    }
    public void setNumRounds(int numRounds) {
        this.numRounds = numRounds;
    }
    public int getNumRounds() {
        return numRounds;
    }
    public boolean isExamOver(){
        return (getRound() >= getNumRounds());
    }


}
