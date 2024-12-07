package FinalProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        PriorityQueue<Professor> professorQueue = new PriorityQueue<>();
        Department csDepartment = new Department(professorQueue);

        try{
            File professorsFile = new File("Data/professors.txt");
            Scanner professorScanner = new Scanner(professorsFile);
            while (professorScanner.hasNextLine()) {
                String line = professorScanner.nextLine();
                String[] parts = line.split(":");
                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                double seniority = Double.parseDouble(parts[2].replaceAll("[a-zA-z]", "").trim());
                Set<String> disciplines = new HashSet<>();
                if (parts.length > 3 && !parts[3].isEmpty()){
                    String[] disciplinesString = parts[3].replaceAll("[ |-]", "").split(",");
                    Collections.addAll(disciplines, disciplinesString);
                    professorQueue.add(new Professor(id, name, seniority, disciplines));
                }
                else {
                    professorQueue.add(new Professor(id, name, seniority, new HashSet<>()));
                }
        }
            professorScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
        
        try {
            File courseFile = new File("Data/ListeDeCours.csv");
            Scanner courseScanner = new Scanner(courseFile);
            courseScanner.nextLine();
            while (courseScanner.hasNextLine()) {
                String line = courseScanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 6){
                    String discipline = parts[0];
                    String courseId = parts[1] + "-" + parts[4];
                    String title = parts[2];
                    int numberOfHours = Integer.parseInt(parts[3]);
                    int numOfGroups = Integer.parseInt(parts[5]);
                    if (csDepartment.courseMap.containsKey(title)){
                        csDepartment.addGroups(title, numOfGroups);
                    }
                    else {
                        Course course = new Course(courseId, title, discipline, numberOfHours, numOfGroups);
                        csDepartment.courseMap.put(courseId, course);
                    }
                } else if (parts.length > 6){
                    String discipline = parts[0];
                    String courseId = parts[1] + "-" + parts[5];
                    String title = parts[2] + ", " + parts[3];
                    int numberOfHours = Integer.parseInt(parts[4]);
                    int numOfGroups = Integer.parseInt(parts[6]);
                    if (csDepartment.courseMap.containsKey(title)){
                        csDepartment.addGroups(title, numOfGroups);
                    }
                    else {
                        Course course = new Course(courseId, title, discipline, numberOfHours, numOfGroups);
                        csDepartment.courseMap.put(courseId, course);
                    }
                }
                else{
                    System.out.println("Invalid line format: " + line);
                }
            }
            courseScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        Scanner requestScanner;

        for (Professor nextProf : professorQueue)
        {
            try {
                StringBuilder requestName = new StringBuilder();
                requestName.append("Data/").append(nextProf.id).append("_request.txt");
                String requestFileName = requestName.toString();
                File requestFile = new File(requestFileName);
                requestScanner = new Scanner(requestFile);
                String firstLine = requestScanner.nextLine();
                System.out.println(firstLine);

                String[] parts = firstLine.split(" -,");
                int maxHours = 0;
                for (String part : parts) {
                    try {
                        int hours = Integer.parseInt(part);
                        if (hours > maxHours) {
                            maxHours = hours;
                        }
                    } catch (NumberFormatException e) {
                    }
    
                    if (maxHours == 0) {
                        maxHours = 24;
                    }
    
                    System.out.println("Assigned hours for prof with id " + nextProf.id + " is " + maxHours + " hours");
                }
                requestScanner.close();
            } catch (FileNotFoundException e) {
            }
            
        }
            
            /* 
            while (requestScanner.hasNextLine()){
                String line = requestScanner.nextLine();
                String[] parts = line.split(":");
                if (parts[0].contains("NaN")){
                    break;
                }
                else{

                }
            }

            nextProf.listOfAffectedCourses.add(course)*/
    }
}
