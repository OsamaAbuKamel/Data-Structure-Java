package Problems;

import src.AVL;

public class SchoolManagementSystem {
    private AVL<Student> students = new AVL<Student>();

    public AVL<Student> getStudents() {
        return this.students;
    }

    public void setStudents(AVL<Student> students) {
        this.students = students;
    }

    public void add(Student student) {
        students.insert(student);
    }

    public Student search(int id) {
        for (int i = 0; i < students.size(); i++) {
            if (id == students.get(i).getNumberId()) {
                return students.get(i);
            }
        }
        return null;
    }

    public void delete(int id) {
        students.delete(search(id));
    }

    public void update(Student student) {
        delete(student.getNumberId());
        add(student);
    }

    public void displayStuClass() {
        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i).getClassId());
        }
    }

    public void displayUndergrads(int classId) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getClassId() == classId) {
                if (students.get(i).getStatus().equalsIgnoreCase("Undergraduate")) {
                    System.out.println(students.get(i).getName());
                }
            }
        }
    }

    public void displayGraduates() {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStatus().equalsIgnoreCase("Graduate")) {
                System.out.println(students.get(i).getName());
            }
        }
    }

    public void displayGraduates(int classId) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getClassId() == classId) {
                if (students.get(i).getStatus().equalsIgnoreCase("Graduate")) {
                    System.out.println(students.get(i).getName());
                }
            }
        }
    }
}
