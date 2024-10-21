package model;

import java.util.Date;

public class Quiz {
    private int quiz_id;
    private int score;
    private Date date_taken;
    
    public Quiz(int quiz_id, int score, Date date_taken) {
        this.quiz_id = quiz_id;
        this.score = score;
        this.date_taken = date_taken;
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

    public void setDate_taken(Date date_taken) {
        this.date_taken = date_taken;
    }

    
}
