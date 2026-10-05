public class Main {

    static Feature init(){
        Feature root = new Feature("Super Learning App", true, null);

        Feature achievement = new Feature("Achievement", true, null);
        root.add(achievement);

        Feature ad = new Feature("Ad", false, null);
        root.add(ad);

        Feature difficulty = new Feature("Difficulty", true, "alternative");
        root.add(difficulty);
        Feature easy = new Feature("Easy", false, null);
        difficulty.add(easy);
        Feature medium = new Feature("Medium", false, null);
        difficulty.add(medium);
        Feature hard = new Feature("Hard", false, null);
        difficulty.add(hard);

        Feature coins = new Feature("Coins", false, null);
        root.add(coins);
        Feature courseSlot = new Feature("Course slot", false, null);
        coins.add(courseSlot);
        Feature purchase = new Feature("Purchase", true, null);
        coins.add(purchase);
        Feature earlyAccess = new Feature("Early Access", false, null);
        coins.add(earlyAccess);
        Feature adFree = new Feature("Ad free", false, null);
        coins.add(adFree);

        Feature course = new Feature("Course", true, null);
        root.add(course);
        Feature type = new Feature("Type", true, "or");
        course.add(type);

        Feature language = new Feature("Language ", false, "or");
        type.add(language);
        Feature french = new Feature("French", false, null);
        language.add(french);
        Feature english = new Feature("English", false, null);
        language.add(english);


        Feature cooking = new Feature("Cooking", false, "or");
        type.add(cooking);

        Feature method = new Feature("Method", true, "or");
        course.add(method);
        Feature lessons = new Feature("Lessons", false, null);
        method.add(lessons);
        Feature exercise = new Feature("Exercise", false, null);
        method.add(exercise);

        Feature workshop = new Feature("Workshop", false, null);
        method.add(workshop);
        Feature create = new Feature("Create", false, null);
        workshop.add(create);

        Feature forum = new Feature("Forum", true, null);
        root.add(forum);
        Feature moderator = new Feature("Moderator", false, null);
        root.add(moderator);

        Feature premium = new Feature("Premium", true, null);
        root.add(premium);
        Feature Administrator = new Feature("Administrator", true, null);
        root.add(Administrator);

        return root;

    }

    public static void main(String[] args) {
        Feature root = init();
        System.out.print(root.toString());
    }
}