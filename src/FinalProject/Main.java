package FinalProject;

import java.util.*;
import java.io.File;

public class Main {
    public static void main(String[] args) {

        PriorityQueue<Professor> professorQueue = new PriorityQueue<>();

        try{
            File professorsFile = new File("Data/professors2.txt");
            Scanner professorScanner = new Scanner(professorsFile);
            while (professorScanner.hasNextLine()) {
                String line = professorScanner.nextLine();
                String[] parts = line.split(":");
                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                double seniority = Double.parseDouble(parts[2].replaceAll("[^0-9.]", "").trim());
                Set<String> disciplines = new HashSet<>();
                if (parts.length > 3 && !parts[3].isEmpty()){
                    String[] disciplinesString = parts[3].replaceAll("[ |-]", "").split(",");
                    Collections.addAll(disciplines, disciplinesString);
                    professorQueue.add(new Professor(id, name, seniority, disciplines));
                    for (String discipline : disciplinesString) {
                        System.out.println(discipline);
                    }
                    System.out.println("-------");
                }
                else {
                    professorQueue.add(new Professor(id, name, seniority, new HashSet<>()));
                }

        }
            professorScanner.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }

        /*Department csDepartment = new Department(professorQueue);

        try{
            File courseFile = new File("Data/ListeDeCours.csv");
            Scanner courseScanner = new Scanner(courseFile);
            courseScanner.nextLine();
            while (courseScanner.hasNextLine()) {
                String line = courseScanner.nextLine();
                String[] parts = line.split(",");

                String discipline = parts[0];
                String courseId = parts[1] + "-" + parts[4];
                String title = parts[2];
                int numberOfHours = Integer.parseInt(parts[3]);
                int numOfGroups = Integer.parseInt(parts[5]);
            }

            courseScanner.close();
        } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
    }*/

    }
}
