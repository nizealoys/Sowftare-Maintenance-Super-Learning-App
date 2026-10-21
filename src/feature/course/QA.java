package src.feature.course;

public class QA {

    private final String question;
    private final String answer;
    private final int topic;
    private final int difficulty;
    private final String topicExplanation;

    public QA(String question, String answer, int topic, int difficulty, String topicExplanation) {
        this.question = question;
        this.answer = answer;
        this.topic = topic;
        this.difficulty = difficulty;
        this.topicExplanation = topicExplanation;
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
}
