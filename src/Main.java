package src;

import src.core.controller.Controller;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Controller cli = new Controller();
        cli.command();   // lance la boucle console
        //ModelBuilder main = new ModelBuilder();
        //main.test("/home/shema/Documents/UCL/Software maintenance/Projet/Sowftare-Maintenance-Super-Learning-App/Model.txt");
    }
}


// import core.model.CompositeBuilder;
// import core.model.Feature;
// import feature.achievement.controller.AchievementController;
// import feature.achievement.model.Achievement;
// import feature.achievement.model.AchievementElement;
// import feature.achievement.service.AchievementFacade;
// import java.util.ArrayList;

// public class Main {
//     public static void main(String[] args) {
//         CompositeBuilder compositeBuilder = CompositeBuilder.getInstance();
//         System.out.println(compositeBuilder.toString());
       
//         Feature feature = compositeBuilder.getComposite().getRoot();
//         ArrayList<Feature> features = feature.getChildren();
        
//         System.out.println("Root feature: " + feature.isActive());
//         System.out.println("Leaf feature: " + features.get(0).isActive());
    
//         AchievementFacade facade = new AchievementFacade(compositeBuilder.getComposite());
//         AchievementElement achievement1 = new AchievementElement("Achievement 1", "Description 1", false);
//         AchievementElement achievement2 = new AchievementElement("Achievement 2", "Description 2", false);
//         AchievementElement achievement3 = new AchievementElement("Achievement 3", "Description 3", false);
//         AchievementElement achievement4 = new AchievementElement("Achievement 4", "Description 4", false);
    
//         Achievement a = (Achievement) features.get(0);
//         a.addAchievementElement(achievement1);
//         a.addAchievementElement(achievement2);
//         a.addAchievementElement(achievement3);
//         a.addAchievementElement(achievement4);
//         AchievementController achievementController = new AchievementController(facade, a);
    
//         achievementController.activate(new String[]{}, new String[]{"SuperLearningApp"});    
//         System.out.println("Root feature: " + feature.isActive());
//         System.out.println("Leaf feature: " + features.get(0).isActive());

//         achievementController.activate(new String[]{"SuperLearningApp"}, new String[]{});
//         System.out.println("Root feature: " + feature.isActive());
//         System.out.println("Leaf feature: " + features.get(0).isActive());

//         achievementController.activate(new String[]{}, new String[]{"Achievement"});    
//         System.out.println("Root feature: " + feature.isActive());
//         System.out.println("Leaf feature: " + features.get(0).isActive());

//         achievementController.activate(new String[]{}, new String[]{"SuperLearningApp"});    
//         System.out.println("Root feature: " + feature.isActive());
//         System.out.println("Leaf feature: " + features.get(0).isActive());
        
//         achievementController.activate(new String[]{"Achievement"}, new String[]{});    
//         System.out.println("Root feature: " + feature.isActive());
//         System.out.println("Leaf feature: " + features.get(0).isActive());

//         System.out.println("Logs: ");
//         String[] logs = achievementController.getStateAsLog();
//         for (String log : logs) {
//             System.out.println(log);
//         }

//         System.out.println("Achievement: " + a.toString());

//         achievementController.setAchievementElementAchieved("Achievement 1", true);

//         System.out.println("");
//         System.out.println("Achievement: " + a.toString());
//     }
// }



// import java.awt.Color;
// import java.util.Scanner;

// import javax.swing.JButton;
// import javax.swing.JFrame;
// import javax.swing.JPanel;

// public class Main{
//     public static void main(String[] args){

//         // Create window with title "application" that stop the program when closed
//         JFrame frame = new JFrame("Application");
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
//         // Creating a panel that will hold the buttons
//         JPanel panel = new JPanel();
        
//         // Setup frame with the panel
//         frame.add(panel);
//         frame.setSize(200, 200);
//         frame.setVisible(true); // show the window
//         // Creation of a button that will be shown dynamically 
//         JButton buttonTemp = new JButton("Dynamic click me");
//         buttonTemp.addActionListener((actionEvent) -> {
//             System.out.println("dynamic click");
//         });

//         // Main loop
//         Scanner in = new Scanner(System.in);
//         while (true) {
//             String line = in.nextLine();
//             String[] cutLine = line.split(" ");
            
//             switch (cutLine[0]) {
//                 // Change the title
//                 case "title":
//                     String title = line.replace("title ", "");
//                     frame.setTitle(title);
//                     break;

//                 // Make the button appear
//                 case "add":
//                     panel.add(buttonTemp);
//                     break;

//                 // Remove the button
//                 case "remove":
//                     panel.remove(buttonTemp);
//                     break;

//                 // Enable dark mode
//                 case "dark":
//                     panel.setBackground(Color.BLACK);
//                     break;
                
//                 // Disable dark mode
//                 case "light":
//                     panel.setBackground(Color.WHITE);
//                     break;
            
//                 // Stop the program
//                 case "stop":
//                     in.close();
//                     System.exit(0);
//                     break;

//                 // Unknown command
//                 default:
//                     System.out.println("Unknown command " + cutLine[0]);
//                     break;
//             }

//             /*
//              * These lines update the UI
//              * Repaint holds for all graphical changes (like color changes)
//              * Revalidate holds for UI change (like button add/remove)
//              */
//             frame.repaint();
//             frame.revalidate();
//         }
//     }
// }
