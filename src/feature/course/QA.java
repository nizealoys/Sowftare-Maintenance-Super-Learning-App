package src.feature.course;

import java.util.HashMap;

public class QA {

    private final String question;
    private final String answer;
    private HashMap<Integer, String> topic;
    private int difficulty;

    public QA(String question, String answer, HashMap<Integer, String> topic, int difficulty) {
        this.question = question;
        this.answer = answer;
        this.topic = topic;
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
