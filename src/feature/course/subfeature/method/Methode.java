package src.feature.course.subfeature.method;

import src.core.model.Feature;
import src.core.utils.ChildrenType;
import src.feature.course.CourseContent;
import src.feature.course.QA;

import java.io.IOException;
import java.util.ArrayList;

public class Methode extends Feature { //modifer le nom
    public Lessons lessons;
    public Exercice exercices;

    public Methode () {
        super("Method", true, true,ChildrenType.OR);
    }

    public Feature getCourse (String name){
        return getFeature(name);
    }

    public void setCourseData(CourseContent course) {
        this.lessons = new Lessons(course);
        this.exercices = new Exercice(this.lessons);
    }

    public ArrayList<QA> getQAList() {
        return this.lessons.getQAList();
    }

}

class Lessons {
    private ArrayList<QA> qaList;
    private ArrayList<String> lessons;
    private int daily;
    private CourseContent currentcourse;

    public Lessons(CourseContent currentcourse) {
        this.qaList = currentcourse.getQAList();
        this.lessons = new ArrayList<>();
        for (int i = 0; i < currentcourse.getQAList().size(); i++) {
            this.lessons.add(currentcourse.getQAList().get(i).getExplanation());
        }
        this.daily = 0;
        this.currentcourse = currentcourse;
    }

    public ArrayList<String> getLessons() {
        return lessons;
    }

    public CourseContent getCurrentcourse() {
        return currentcourse;
    }

    public void nextDaily() {
        this.daily++;
    }

    public int getDaily() {
        return this.daily;
    }

    public String getDailyContent() {
        return lessons.get(daily);
    }

    public ArrayList<QA> getQAList() {
        return qaList;
    }
}

class Exercice {
    private Lessons lessons;

    public Exercice(Lessons lessons) {
        this.lessons = lessons;
    }

    public ArrayList<QA> getDailyQuestions(){
        ArrayList<QA> questions = new ArrayList<>();
        for (QA question : this.lessons.getQAList()) {
            if (question.getTopic() == this.getDaily()) {
                questions.add(question);
            }
        }
        return questions;
    }

    public CourseContent getCurrentcourse () {
        return lessons.getCurrentcourse();
    }

    public int getDaily() {
        return lessons.getDaily();
    }

    public ArrayList<QA> getQAList () {
        return lessons.getQAList();
    }
}



    /**
    // generé avec Claude (à partir de ligne 42)
    public static ArrayList<String[]> load(String fileName) throws IOException {
        ArrayList<String[]> result = new ArrayList<String[]>();

        Path path = Path.of("src/ressources/" + fileName);
        path = path.toAbsolutePath();

        // lit tout le fichier d'un coup (UTF-8 pour les accents)
        String text = Files.readString(path, StandardCharsets.UTF_8);

        // découpe sur chaque ligne "#LESSON"
        String[] blocks = text.split("(?m)^#LESSON\\s*$");

        for (String block : blocks) {
            block = block.strip();
            if (block.isEmpty()) continue;   // le 1er bloc avant le premier #LESSON est vide

            // 3 morceaux max : titre / difficulté / tout le reste
            String[] parts = block.split("\\R", 3);
            if (parts.length < 3) continue;  // bloc mal formé

            String title = parts[0].strip();
            String difficulty = parts[1].strip();
            String content = parts[2].strip();

            result.add(new String[]{title, difficulty, content});
        }
        return result;
    }

    public ArrayList<String[]> getLessons() {
        return this.lessons;
    }

    //generé avec Claude
    public String printLesson() {
        StringBuilder sb = new StringBuilder();
        for (String[] lesson : this.lessons) {
            sb.append("=== ").append(lesson[0])
                    .append(" (").append(lesson[1]).append(") ===\n")
                    .append(lesson[2])
                    .append("\n\n");
        }
        return sb.toString();
    }

    public String[] getDaily() {
        return this.daily;
    }
     */