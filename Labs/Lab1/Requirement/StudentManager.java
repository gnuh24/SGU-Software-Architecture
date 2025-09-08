import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Student Grade Management System
 * Allows input of student information, calculates average grades,
 * and provides grade classification.
 */
public class StudentManager {
    
    // Constants
    public static final int MAX_STUDENTS = 3;
    private static final double EXCELLENT_THRESHOLD = 90.0;
    private static final double GOOD_THRESHOLD = 80.0;
    private static final double AVERAGE_THRESHOLD = 70.0;
    
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        manager.runGradeManagementSystem();
    }
    
    /**
     * Main method to run the student grade management system
     */
    public void runGradeManagementSystem() {
        System.out.println("=== Student Grade Management System ===");
        
        Scanner scanner = new Scanner(System.in);
        List<String> studentNames = new ArrayList<>();
        List<Double> studentGrades = new ArrayList<>();
        
        // Input student information
        inputStudentData(scanner, studentNames, studentGrades);
        scanner.close();
        
        // Calculate and display results
        displayResults(studentNames, studentGrades);
    }
    
    /**
     * Input student names and grades
     * @param scanner Scanner object for user input
     * @param studentNames List to store student names
     * @param studentGrades List to store student grades
     */
    private void inputStudentData(Scanner scanner, List<String> studentNames, List<Double> studentGrades) {
        while (studentNames.size() < MAX_STUDENTS) {
            System.out.println("Enter student name #" + (studentNames.size() + 1) + ":");
            String name = scanner.nextLine().trim();
            
            if (name.isEmpty()) {
                System.out.println("Name cannot be empty! Try again!");
                continue;
            }
            
            studentNames.add(name);
            
            // Input grade for the student
            if (!inputStudentGrade(scanner, name, studentNames, studentGrades)) {
                // If grade input failed, remove the name and try again
                studentNames.remove(studentNames.size() - 1);
            }
        }
    }
    
    /**
     * Input grade for a specific student
     * @param scanner Scanner object for user input
     * @param studentName Name of the student
     * @param studentNames List of student names (for removal if needed)
     * @param studentGrades List to store grades
     * @return true if grade was successfully added, false otherwise
     */
    private boolean inputStudentGrade(Scanner scanner, String studentName, 
                                    List<String> studentNames, List<Double> studentGrades) {
        System.out.println("Enter grade for " + studentName + " (0-100):");
        String gradeInput = scanner.nextLine();
        
        try {
            double grade = Double.parseDouble(gradeInput);
            
            if (grade < 0 || grade > 100) {
                System.out.println("Grade must be between 0-100! Try again!");
                return false;
            }
            
            studentGrades.add(grade);
            return true;
            
        } catch (NumberFormatException e) {
            System.out.println("Invalid grade format! Try again!");
            return false;
        }
    }
    
    /**
     * Calculate average grade from a list of grades
     * @param grades List of student grades
     * @return Average grade
     */
    private double calculateAverage(List<Double> grades) {
        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }
    
    /**
     * Classify grade based on average score
     * @param average Average grade
     * @return Classification string
     */
    private String classifyGrade(double average) {
        if (average >= EXCELLENT_THRESHOLD) {
            return "Excellent";
        } else if (average >= GOOD_THRESHOLD) {
            return "Good";
        } else if (average >= AVERAGE_THRESHOLD) {
            return "Average";
        } else {
            return "Below Average";
        }
    }
    
    /**
     * Display the final results including individual grades, average, and classification
     * @param studentNames List of student names
     * @param studentGrades List of student grades
     */
    private void displayResults(List<String> studentNames, List<Double> studentGrades) {
        double average = calculateAverage(studentGrades);
        String classification = classifyGrade(average);
        
        System.out.println("\n=== RESULTS ===");
        
        // Display individual student grades
        for (int i = 0; i < studentNames.size(); i++) {
            System.out.println("Student: " + studentNames.get(i) + 
                             " - Grade: " + studentGrades.get(i));
        }
        
        // Display summary
        System.out.println("Average Grade: " + String.format("%.2f", average));
        System.out.println("Classification: " + classification);
    }
}