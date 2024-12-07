package FinalProject;

import java.util.ArrayList;
import java.util.Set;

public class Professor implements Comparable<Professor>{
    public int id;
    public String name;
    public double seniorityLevel;
    public Set<String> setOfDisciplines;
    public ArrayList<Course> listOfAffectedCourses;

    public Professor(int id, String name, double seniority, Set<String> setOfDisciplines) {
        this.id = id;
        this.name = name;
        this.seniorityLevel = seniority;
        this.setOfDisciplines = setOfDisciplines;
        this.listOfAffectedCourses = null;
    }

    public void makeListOfAffectedCourses() {
        if (listOfAffectedCourses == null) {
            listOfAffectedCourses = new ArrayList<>();
        }
    }

    public void addCourse(Course course) {
        if (listOfAffectedCourses == null) {
            makeListOfAffectedCourses();
        }
        listOfAffectedCourses.add(course);
    }

    @Override
    public int compareTo(Professor other) {
        if (this.seniorityLevel > other.seniorityLevel) {
            return 1;
        } else if (this.seniorityLevel < other.seniorityLevel) {
            return -1;
        } else if (this.id > other.id) {
            return 1;
        }
        return -1;
    }
}
