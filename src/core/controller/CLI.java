package src.core.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import src.core.utils.Utils;
import src.feature.achievement.Achievement;
import src.feature.achievement.AchievementElement;
import src.feature.course.CourseContent;
import src.feature.course.QA;
import src.feature.difficulty.Difficulty;

public class CLI {

    private final Controller controller;

    public CLI() {
        controller = Controller.getInstance();
    }

    public String instruction() {

        System.out.print("\n List of Instructions: \n");

        String instruction = "";
        instruction += "ENABLE: SHOWS ALL UI VIEW OF THE USERS (IF THERE IS NOT USER, ENABLE WILL CREATE ONE) \n";
        instruction += "DISABLE: HIDE ALL UI VIEW OF THE USERS \n";
        instruction += "MODEL: SHOW THE CURRENT USER MODEL \n";
        instruction += "STOP: SHUT DOWN THE CONSOLE \n";
        instruction += "LOG: PRINT THE LOG \n";
        instruction += "ACTIVATE: ACTIVATE A SPECIFIC FEATURE \n";
        instruction += "DEACTIVATE: DEACTIVATE A SPECIFIC FEATURE \n";
        instruction += "COURSE: SHOW THE COURSE CONTENT \n";

        return instruction;
    }

    public Controller getController() {
        return controller;
    }

    public void command() {
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.print(instruction());
            String line = in.nextLine();
            switch (line) {
                case "ENABLE" -> {
                    controller.enableUIView();
                    System.out.print("New View enable \n");
                }
                case "DISABLE" -> {
                    controller.disableUIView();
                    System.out.print("View disable \n");
                }

                case "MODEL" ->
                    System.out.print(controller.getComposite().toString() + '\n');

                case "STOP" -> {
                    in.close();
                    System.exit(0);
                }

                case "LOG" ->
                    System.out.print(Arrays.toString(controller.getStateAsLog()));

                case "ACTIVATE" -> {
                    System.out.print("Enter the features to activate (e.g. Premium): ");
                    String[] activations = in.nextLine().split(",");
                    controller.activate(new String[0], activations);
                }

                case "DEACTIVATE" -> {
                    System.out.print("Enter the features to deactivate (e.g. Premium): ");
                    String[] deactivations = in.nextLine().split(",");
                    controller.activate(deactivations, new String[0]);
                }

                case "COURSE" -> {
                    courseCatalog(in);
                }

                case "BADGE" -> {
                    List<AchievementElement> achievementElements = ((Achievement) controller.getComposite().getFeature("Achievement")).getAchievementElements();
                    for (AchievementElement element : achievementElements) {
                        System.out.println("Badge: " + element.getName() + ", Description: " + element.getDescription() + ", Achieved: " + element.isAchieved());
                    }
                }

                default ->
                    System.out.println("Unknown command " + line);

            }
        }
    }

    private void courseCatalog(Scanner in) {
        System.out.print("\n\nCourse Catalog: \n");
        System.out.print("Cooking \n");
        System.out.print("English \n");
        System.out.print("French \n");
        System.out.print("Enter the course name (e.g. Cooking, English, French): \n");
        System.out.print("Write other things to leave the course catalog: \n");

        String line = in.nextLine();

        if ((line.equals("Cooking") || line.equals("English") || line.equals("French"))
                && controller.getComposite().getFeature(line).isActive()) {
            courseMethode(in, line);
        } else {
            System.out.print("Leaving the course catalog \n");
        }

    }

    private void courseMethode(Scanner in, String courseName) {
        Difficulty difficulty = (Difficulty) controller.getComposite().getFeature("Difficulty");

        System.out.print("\n\nCourse: " + courseName + "\n");
        System.out.print("Current Difficulty: " + Difficulty.difficulties[difficulty.getDifficulty(courseName)] + "\n");
        System.out.print("Available Options: \n");
        System.out.print("Workshop \n");
        System.out.print("Lessons \n");
        System.out.print("Difficulty \n");
        System.out.print("Enter the methode name (e.g. Workshop, Lessons): ");
        System.out.print("Write other things to leave the course methode (e.g. Back): ");

        String line = in.nextLine();

        switch (line) {
            case "Difficulty" -> {
                courseDifficulty(in, courseName);
                courseMethode(in, courseName); // Return to courseMethode after setting difficulty
            }
            case "Workshop", "Lessons" ->
                courseLessons(in, courseName);
            default ->
                System.out.print("Leaving the course methode \n");
        }
    }

    private void courseDifficulty(Scanner in, String courseName) {
        System.out.print("\n\nCourse: " + courseName + "\n");
        System.out.print("Current Difficulty: " + Difficulty.getInstance().getDifficultyLevel(courseName) + "\n");
        System.out.print("Available Difficulty Levels: \n");
        System.out.print("0: Easy \n");
        System.out.print("1: Medium \n");
        System.out.print("2: Hard \n");
        System.out.print("Enter the difficulty level (0, 1, or 2): ");
        System.out.print("Write other things to leave the course difficulty (e.g. Back): ");

        String line = in.nextLine();

        if (Utils.isNum(line)) {
            int newDifficulty = Integer.parseInt(line);
            if (newDifficulty >= 0 && newDifficulty <= 2) {
                Difficulty.getInstance().setDifficulty(courseName, newDifficulty);
                System.out.print("Difficulty for " + courseName + " set to " + Difficulty.difficulties[newDifficulty] + "\n");
            } else {
                System.out.print("Invalid difficulty level. Please enter 0, 1, or 2.\n");
                courseDifficulty(in, courseName); // Prompt again for valid input
            }
        } else {
            System.out.print("Leaving the course difficulty \n");
        }
    }

    private void courseLessons(Scanner in, String courseName) {
        CourseContent lesson = (CourseContent) controller.getComposite().getFeature(courseName);
        int currentDifficultyLevel = Difficulty.getInstance().getDifficultyLevel(courseName);
        List<Integer> topics = lesson.getQAList().stream()
                .filter(qa -> qa.getDifficulty()
                        == currentDifficultyLevel)
                .map(QA::getTopic)
                .distinct()
                .collect(Collectors.toList());

        System.out.print("\n\nCourse: " + courseName + " Lessons \n");
        System.out.print("Current Difficulty: " + Difficulty.difficulties[currentDifficultyLevel - 1] + "\n");
        System.out.print("Available Lessons: \n");
        for (int i = 0; i < topics.size(); i++) {
            System.out.print("Topic " + topics.get(i) + "\n");
        }
        System.out.print("Enter the number of the lesson you want to access (e.g. 1 or 2): ");

        String line = in.nextLine();

        if (Utils.isNum(line) && topics.contains(Integer.valueOf(line))) {
            executeLesson(in, lesson, Integer.parseInt(line));
        } else {
            System.out.print("Leaving the course lessons \n");
        }
    }

    private void executeLesson(Scanner in, CourseContent lesson, int topic) {
        List<QA> qaList = lesson.getQAList().stream()
                .filter(q -> q.getTopic() == topic
                        && q.getDifficulty()
                        == Difficulty.getInstance().getDifficultyLevel(lesson.getName()))
                .collect(Collectors.toList());

        System.out.print("\n\nType 'Back' to return to the course lessons: \n");
        if (qaList != null) {
            for (QA qa : qaList) {
                System.out.print("Lesson: " + qa.getExplanation() + "\n");
                System.out.print("Question: " + qa.getQuestion() + "\n");

                String line = in.nextLine();

                while (!line.equals("Back")) {
                    if (!line.equals("Back") && line.equals(qa.getAnswer())) {
                        System.out.print("Correct answer! \n");
                        break;
                    } else if (!line.equals("Back")) {
                        System.out.print("Incorrect answer. Try : " + qa.getAnswer() + "\n\n");
                    }
                    line = in.nextLine();
                }
            }
        } else {
            System.out.print("No lesson found for the selected topic and difficulty.\n\n");
        }

    }
}
