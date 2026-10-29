package src.data;

import java.util.ArrayList;
import java.util.HashMap;

public class CourseData {

    private static final CourseData instance = new CourseData();
    private final ArrayList<HashMap<String, String>> courseData;
    public final String[] keys = {"course", "topic", "topicExplanation", "difficulty", "question", "answer"};

    public static CourseData getInstance() {
        return instance;
    }

    private CourseData() {
        this.courseData = new ArrayList<>();
        getData();
    }

    private void getData() {

        ArrayList<String> values = new ArrayList<>();
        values.add("English");
        values.add("1");
        values.add("Use 'an' before a word that starts with a vowel sound.");
        values.add("1");
        values.add("Complete the sentence: I eat ___ apple every day.");
        values.add("an");

        this.addCourseData(values);

        values = new ArrayList<>();
        values.add("English");
        values.add("1");
        values.add("Use 'an' before a word that starts with a vowel sound.");
        values.add("2");
        values.add("Complete the sentence: She is ___ honest person.");
        values.add("an");

        this.addCourseData(values);

        values = new ArrayList<>();
        values.add("English");
        values.add("1");
        values.add("'A' is used before a consonant sound, while 'an' is used before a vowel sound.");
        values.add("3");
        values.add("Complete the sentence: He studies at ___ European university.");
        values.add("a");

        this.addCourseData(values);

        values = new ArrayList<>();
        values.add("English");
        values.add("2");
        values.add("Use 'am' with the pronoun 'I'.");
        values.add("1");
        values.add("Complete the sentence: I ___ happy.");
        values.add("am");

        this.addCourseData(values);

        values = new ArrayList<>();
        values.add("English");
        values.add("2");
        values.add("Use 'are' with the pronoun 'we'.");
        values.add("2");
        values.add("Complete the sentence: We ___ students.");
        values.add("are");

        this.addCourseData(values);

        values = new ArrayList<>();
        values.add("English");
        values.add("2");
        values.add("The verb 'to be' must agree with the subject of the sentence.");
        values.add("3");
        values.add("Complete the sentence: My friends ___ very kind.");
        values.add("are");

        this.addCourseData(values);

        values = new ArrayList<>();
        values.add("French");
        values.add("1");
        values.add("Le déterminant 'un' s'utilise devant un nom masculin singulier.");
        values.add("1");
        values.add("Complète la phrase : J'ai ___ livre.");
        values.add("un");

        this.addCourseData(values);

        values = new ArrayList<>();
        values.add("French");
        values.add("1");
        values.add("Le déterminant 'une' s'utilise devant un nom féminin singulier.");
        values.add("2");
        values.add("Complète la phrase : Elle a ___ pomme.");
        values.add("une");

        this.addCourseData(values);

        values = new ArrayList<>();
        values.add("French");
        values.add("1");
        values.add("Le déterminant doit correspondre au genre et au nombre du nom.");
        values.add("3");
        values.add("Complète la phrase : Nous visitons ___ ancienne maison.");
        values.add("une");

        values = new ArrayList<>();
        values.add("French");
        values.add("2");
        values.add("Le verbe 'être' se conjugue 'suis' avec le pronom 'je'.");
        values.add("1");
        values.add("Complète la phrase : Je ___ étudiant.");
        values.add("suis");

        this.addCourseData(values);

        values = new ArrayList<>();
        values.add("French");
        values.add("2");
        values.add("Avec le pronom 'nous', le verbe 'être' se conjugue 'sommes'.");
        values.add("2");
        values.add("Complète la phrase : Nous ___ en classe.");
        values.add("sommes");

        this.addCourseData(values);

        values = new ArrayList<>();
        values.add("French");
        values.add("2");
        values.add("Le verbe 'être' s'accorde avec le sujet de la phrase.");
        values.add("3");
        values.add("Complète la phrase : Mes amis ___ très gentils.");
        values.add("sont");

        this.addCourseData(values);

        values = new ArrayList<>();
        values.add("Cooking");
        values.add("1");
        values.add("Wash your hands before handling ingredients to keep food preparation hygienic.");
        values.add("1");
        values.add("What should you do before preparing food?");
        values.add("Wash your hands");

        this.addCourseData(values);

        values = new ArrayList<>();
        values.add("Cooking");
        values.add("1");
        values.add("A clean work surface helps prevent contamination while cooking.");
        values.add("2");
        values.add("Why should you clean your work surface before cooking?");
        values.add("To prevent contamination");

        this.addCourseData(values);

        values = new ArrayList<>();
        values.add("Cooking");
        values.add("1");
        values.add("Raw meat should be kept separate from ready-to-eat food to avoid cross-contamination.");
        values.add("3");
        values.add("Where should raw meat be kept while preparing a salad?");
        values.add("Separate from the salad ingredients");

        this.addCourseData(values);

        values = new ArrayList<>();
        values.add("Cooking");
        values.add("2");
        values.add("Boiling cooks food in water that has reached a high temperature.");
        values.add("1");
        values.add("Which cooking method uses boiling water?");
        values.add("Boiling");

        this.addCourseData(values);

        values = new ArrayList<>();
        values.add("Cooking");
        values.add("2");
        values.add("Sautéing means cooking food quickly in a small amount of oil over medium-high heat.");
        values.add("2");
        values.add("What does sautéing usually require?");
        values.add("A small amount of oil");

        this.addCourseData(values);

        values = new ArrayList<>();
        values.add("Cooking");
        values.add("2");
        values.add("Braising combines searing with slow cooking in a small amount of liquid.");
        values.add("3");
        values.add("Which technique combines searing and slow cooking in liquid?");
        values.add("Braising");

        this.addCourseData(values);

    }

    public void addCourseData(HashMap<String, String> data) {
        courseData.add(data);
    }

    public void addCourseData(ArrayList<String> values) {
        if (values.size() >= 6) {
            HashMap<String, String> data = new HashMap<>();

            for (int i = 0; i < keys.length; i++) {
                data.put(keys[i], values.get(i));
            }
            courseData.add(data);
        }
    }

    public ArrayList<HashMap<String, String>> getCourseData() {
        return courseData;
    }

    public ArrayList<String> getCourseDataByKey(String key) {
        ArrayList<String> values = new ArrayList<>();

        for (HashMap<String, String> data : courseData) {
            if (data.containsKey(key)) {
                values.add(data.get(key));
            }
        }
        return values;
    }

    public int getCourseDataSize() {
        return courseData.size();
    }

}
