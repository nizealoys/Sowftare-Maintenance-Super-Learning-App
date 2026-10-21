package src.feature.course.subfeature.method;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Method{
    public Lessons english;
    public Method () throws IOException{
        this.english = new Lessons("english_lessons.txt");
    }

    public void printLesson(){
        System.out.print(this.english.printLesson());
    }

}

class Lessons{
    private ArrayList<String[]> lessons;
    private String[] daily;

    public Lessons(String coursefile) throws IOException {
        this.lessons = load(coursefile);
        this.daily = null;
    }

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
}