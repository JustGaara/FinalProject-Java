package FinalProject;

import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;

public class Main2 {
    public static void main(String[] args) {
        PriorityQueue<Professor> professorQueue = new PriorityQueue<>();

        try {
            File professorsFile = new File("Data/professors.txt");
            Scanner professorScanner = new Scanner(professorsFile);

            while (professorScanner.hasNextLine()) {
                String line = professorScanner.nextLine().trim(); // Normalize whitespace
                System.out.println("Processing line: " + line); // Debug line

                try {
                    String[] parts = line.split(":");

                    // Validate the line structure
                    if (parts.length < 4) {
                        System.out.println("Skipping malformed line: " + line);
                        continue;
                    }

                    int id = Integer.parseInt(parts[0].trim()); // Parse ID
                    String name = parts[1].trim(); // Parse name
                    double seniority = Double.parseDouble(parts[2].replaceAll("[^0-9.]", "").trim()); // Clean seniority

                    Set<String> disciplines = new HashSet<>();
                    if (!parts[3].isEmpty()) {
                        String[] disciplinesString = parts[3].split("[,|/]"); // Split by commas, pipes, or slashes
                        for (String discipline : disciplinesString) {
                            if (!discipline.trim().isEmpty()) {
                                disciplines.add(discipline.trim());
                            }
                        }
                    }

                    // Add professor to the queue
                    professorQueue.add(new Professor(id, name, seniority, disciplines));
                } catch (Exception e) {
                    System.out.println("Error processing line: " + line + " - " + e.getMessage());
                }
            }

            professorScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

    }
}
