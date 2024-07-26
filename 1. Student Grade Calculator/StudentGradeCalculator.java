import java.util.Scanner;

public class StudentGradeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: Number of subjects
        System.out.print("Enter the number of subjects: ");
        int numberOfSubjects = scanner.nextInt();

        // Input: Marks obtained in each subject
        int[] marks = new int[numberOfSubjects];
        for (int i = 0; i < numberOfSubjects; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + ": ");
            marks[i] = scanner.nextInt();
        }

        // Calculate Total Marks
        int totalMarks = calculateTotalMarks(marks);

        // Calculate Average Percentage
        double averagePercentage = calculateAveragePercentage(totalMarks, numberOfSubjects);

        // Grade Calculation
        String grade = calculateGrade(averagePercentage);

        // Display Results
        displayResults(totalMarks, averagePercentage, grade);

        scanner.close();
    }

    private static int calculateTotalMarks(int[] marks) {
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        return total;
    }

    private static double calculateAveragePercentage(int totalMarks, int numberOfSubjects) {
        return (double) totalMarks / numberOfSubjects;
    }

    private static String calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return "A";
        } else if (averagePercentage >= 80) {
            return "B";
        } else if (averagePercentage >= 70) {
            return "C";
        } else if (averagePercentage >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    private static void displayResults(int totalMarks, double averagePercentage, String grade) {
        System.out.println("\nResults:");
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + averagePercentage);
        System.out.println("Grade: " + grade);
    }
}
