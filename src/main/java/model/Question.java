package model;

public class Question {
    private int question_id;
    private String text;
    private String correct_answer;
    private String incorrect1;
    private String incorrect2;
    private String incorrect3;

    public Question(int question_id, String text, String correct_answer, String incorrect1, String incorrect2, String incorrect3) {
        this.question_id = question_id;
        this.text = text;
        this.correct_answer = correct_answer;
        this.incorrect1 = incorrect1;
        this.incorrect2 = incorrect2;
        this.incorrect3 = incorrect3;
    }

    

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIncorrect1() {
        return incorrect1;
    }

    public void setIncorrect1(String incorrect1) {
        this.incorrect1 = incorrect1;
    }

    public String getIncorrect2() {
        return incorrect2;
    }

    public void setIncorrect2(String incorrect2) {
        this.incorrect2 = incorrect2;
    }

    public String getIncorrect3() {
        return incorrect3;
    }

    public void setIncorrect3(String incorrect3) {
        this.incorrect3 = incorrect3;
    }



    public String getCorrect_answer() {
        return correct_answer;
    }



    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    
    
}
