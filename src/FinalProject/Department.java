package FinalProject;

import java.util.HashMap;
import java.util.PriorityQueue;

public class Department {
    public HashMap<String, Course> courseMap;
    public PriorityQueue<Professor> listOfProfs;

    public Department(PriorityQueue<Professor> listOfProfs) {
        this.listOfProfs = listOfProfs;
        this.courseMap = new HashMap<String, Course>();
    }
}


