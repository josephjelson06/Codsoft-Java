import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private int enrolled;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolled = 0;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public int getAvailableSlots() {
        return capacity - enrolled;
    }

    public boolean enroll() {
        if (enrolled < capacity) {
            enrolled++;
            return true;
        } else {
            return false;
        }
    }

    public boolean drop() {
        if (enrolled > 0) {
            enrolled--;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Course Code: " + courseCode + ", Title: " + title + ", Description: " + description + 
               ", Capacity: " + capacity + ", Schedule: " + schedule + ", Available Slots: " + getAvailableSlots();
    }
}

class Student {
    private String studentId;
    private String name;
    private ArrayList<Course> registeredCourses;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public boolean registerCourse(Course course) {
        if (course.enroll()) {
            registeredCourses.add(course);
            return true;
        } else {
            return false;
        }
    }

    public boolean dropCourse(Course course) {
        if (registeredCourses.contains(course) && course.drop()) {
            registeredCourses.remove(course);
            return true;
        } else {
            return false;
        }
    }
}

public class StudentCourseRegistrationSystem {
    private static HashMap<String, Course> courseDatabase = new HashMap<>();
    private static HashMap<String, Student> studentDatabase = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Sample data for courses
        courseDatabase.put("CS101", new Course("CS101", "Introduction to Computer Science", "Basic concepts of computer science", 30, "MWF 10-11AM"));
        courseDatabase.put("MATH101", new Course("MATH101", "Calculus I", "Introduction to differential calculus", 25, "TTh 9-10:30AM"));

        // Sample data for students
        studentDatabase.put("S001", new Student("S001", "Alice"));
        studentDatabase.put("S002", new Student("S002", "Bob"));

        while (true) {
            System.out.println("1. List Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    listCourses();
                    break;
                case 2:
                    registerForCourse(scanner);
                    break;
                case 3:
                    dropCourse(scanner);
                    break;
                case 4:
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void listCourses() {
        for (Course course : courseDatabase.values()) {
            System.out.println(course);
        }
    }

    private static void registerForCourse(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        Student student = studentDatabase.get(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
        Course course = courseDatabase.get(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        if (student.registerCourse(course)) {
            System.out.println("Course registered successfully.");
        } else {
            System.out.println("Failed to register for the course. It might be full.");
        }
    }

    private static void dropCourse(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        Student student = studentDatabase.get(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
        Course course = courseDatabase.get(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        if (student.dropCourse(course)) {
            System.out.println("Course dropped successfully.");
        } else {
            System.out.println("Failed to drop the course. It might not be registered.");
        }
    }
}
