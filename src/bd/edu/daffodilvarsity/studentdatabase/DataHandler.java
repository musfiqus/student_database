package bd.edu.daffodilvarsity.studentdatabase;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author Mushfiqus Salehin
 */
public class DataHandler {
        private ArrayList<Student> students = new ArrayList();
        private Student currentStudent = new Student();
        private int currentStudentIndex = -1;

    public int getCurrentStudentIndex() {
        return currentStudentIndex;
    }

    public void setCurrentStudentIndex(int currentStudentIndex) {
        this.currentStudentIndex = currentStudentIndex;
    }

    public Student getCurrentStudent() {
        return currentStudent;
    }

    public void setCurrentStudent(Student currentStudent) {
        this.currentStudent = currentStudent;
    }

    public DataHandler() {
        loadStudentData();
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    //Adding new student
    public void addStudent(Student student) {
        this.students.add(student);
    }

    //Drop student from database
    public void dropStudent(int index) {
        this.students.remove(index);
    }

    //Saving student data
    public void saveStudentData() {
        try {
            FileOutputStream fos = new FileOutputStream("student.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.students); // write students to ObjectOutputStream
            oos.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    //Load data if available
    public void loadStudentData() {
        if (checkSaveFile()) {
            try {
                FileInputStream fis = new FileInputStream("student.dat");
                ObjectInputStream ois = new ObjectInputStream(fis);
                this.students = (ArrayList<Student>) ois.readObject(); // write  to this.students from ObjectInputStream
                ois.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    //Checking if save file exists
    private boolean checkSaveFile() {
        Path path = Paths.get("student.dat");

        if (Files.exists(path)) {
            System.out.println("Exists");
            return true;
        } else if (Files.notExists(path)) {
            return false;
        } else {
            return false;
        }
    }
}
