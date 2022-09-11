public class Student {
    private int score;
    private int studentID;
    // Provide empty constructor
    public Student() {
    }
    // Provide constructor with input variable
    public Student(int score, int studentID) {
        this.score = score;
        this.studentID = studentID;
    }
    // Provide setter and getter for attribute of student
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }
}
