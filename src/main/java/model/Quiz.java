package model;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Quiz {
    private int quiz_id;
    private int score;
    private Date date_taken;
    private int student_id;

    public Quiz(int quiz_id, int score, Date date_taken, int student_id) {
        this.quiz_id = quiz_id;
        this.score = score;
        this.date_taken = date_taken;
        this.student_id = student_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getDate_taken() {
        return date_taken;
    }

    public String getDateString(){
        Date d = date_taken;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String formattedDate = formatter.format(d);
        return formattedDate;
    }

    public void setDate_taken(Date date_taken) {
        this.date_taken = date_taken;
    }

    @Override
    public String toString() {
        return "Last quiz taken on " + date_taken + ". You scored " + score + "/10.";
    }

    

    
}
