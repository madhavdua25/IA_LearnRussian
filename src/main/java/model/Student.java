package model;

public class Student {
    private int student_id; 
    private String name;

    public Student(int student_id, String name) {
        this.student_id = student_id;
        this.name = name;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " (" + student_id + ")";
    }

    
    
}
