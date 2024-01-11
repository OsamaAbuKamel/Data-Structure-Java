package Problems;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SchoolManagementSystem system = new SchoolManagementSystem();
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n---- School Management System ----");
            System.out.println("1. Insert a student");
            System.out.println("2. Find and update a student");
            System.out.println("3. List students by class in lexicographic order");
            System.out.println("4. List all students in lexicographic order");
            System.out.println("5. List all graduated students");
            System.out.println("6. List undergrads by class in lexicographic order");
            System.out.println("7. Delete a student by code");
            System.out.println("8. Delete all graduates");
            System.out.println("9. Save all students to file (student.data)");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            switch (choice) {
                case 1:
                    System.out.println("Enter student details:");
                    System.out.print("Number Id");
                    int numberId = scanner.nextInt();
                    System.out.println("Name");
                    String name = scanner.next();
                    System.out.println("Birth Date");
                    String birthDate = scanner.next();
                    System.out.println("Home Address");
                    String homeAddress = scanner.next();
                    System.out.println("Class Id");
                    int classId = scanner.nextInt();
                    System.out.println("Enrollment Date");
                    String enrollmentDate = scanner.next();
                    System.out.println("Status");
                    String status = scanner.next();
                    Student student = new Student(numberId, name, birthDate, homeAddress, classId, enrollmentDate,
                            status);
                    system.add(student);
                    break;
                case 2:
                    System.out.print("Enter student code to find: ");
                    numberId = scanner.nextInt();
                    student = system.search(numberId);
                    System.out.println(student);
                    if (student != null) {
                    }
                    break;
                case 3:
                    system.displayStuClass();
                    break;
                case 4:
                    system.getStudents().traverseInOrder();
                    break;
                case 5:
                    system.displayGraduates();
                    break;
                case 6:
                    System.out.print("Enter student class id: ");
                    classId = scanner.nextInt();
                    system.displayUndergrads(classId);
                    break;
                case 7:
                    System.out.print("Enter student code to delete: ");
                    numberId = scanner.nextInt();
                    system.delete(numberId);
                    break;
                case 8:
                    break;
                case 9:
                    // Save all students to file (student.data)
                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.data"))) {
                        // Serialize and write the binary tree to the file
                        System.out.println("Students saved to file successfully.");
                    } catch (IOException e) {
                        System.out.println("Error saving students to file: " + e.getMessage());
                    }
                    break;
                case 10:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 10);
        scanner.close();
    }
}
