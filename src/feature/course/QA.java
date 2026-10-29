package src.feature.course;

import java.util.HashMap;

public class QA {

    private final int topic;
    private final String topicExplanation;
    private final int difficulty;
    private final String question;
    private final String answer;
    private Boolean isCompleted;

    public QA(int topic, String topicExplanation, int difficulty, String question, String answer) {
        this.topic = topic;
        this.topicExplanation = topicExplanation;
        this.difficulty = difficulty;
        this.question = question;
        this.answer = answer;
        this.isCompleted = false;
    }

    public QA(HashMap<String, String> data) {
        this.topic = Integer.parseInt(data.get("topic"));
        this.topicExplanation = data.get("topicExplanation");
        this.difficulty = Integer.parseInt(data.get("difficulty"));
        this.question = data.get("question");
        this.answer = data.get("answer");
        this.isCompleted = false;
    }

    public int getDifficulty() {
        return this.difficulty;
    }

    public int getTopic() {
        return topic;
    }

    public String getExplanation() {
        return topicExplanation;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public String toString() {
        return "QA{"
                + "topic=" + topic
                + ", topicExplanation='" + topicExplanation + '\''
                + ", difficulty=" + difficulty
                + ", question='" + question + '\''
                + ", answer='" + answer + '\''
                + '}';
    }
}
