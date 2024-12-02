package pages;

import java.util.PriorityQueue;
import model.Question;

public class PriorityQuestion implements Comparable<PriorityQuestion> {
    private final Question question;
    private int priority;

    public PriorityQuestion(Question question, int priority) {
        this.question = question;
        this.priority = priority;
    }

    public Question getQuestion() {
        return question;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(PriorityQuestion other) {
        return Integer.compare(this.priority, other.priority);
    }
}

