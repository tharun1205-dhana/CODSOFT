import java.util.Scanner;

public class GradeEvaluator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== Grade Evaluator ===");

        // Get total number of subjects
        System.out.print("How many subjects do you have? ");
        int subjectCount = input.nextInt();

        int[] scores = new int[subjectCount];
        int sumOfMarks = 0;

        // Get marks for each subject
        for (int i = 0; i < subjectCount; i++) {
            while (true) {
                System.out.print("Enter score for Subject " + (i + 1) + " (0 - 100): ");
                int score = input.nextInt();
                if (score >= 0 && score <= 100) {
                    scores[i] = score;
                    sumOfMarks += score;
                    break;
                } else {
                    System.out.println("Invalid score! It must be between 0 and 100.");
                }
            }
        }

        // Compute average
        double percentage = (double) sumOfMarks / subjectCount;

        // Determine letter grade
        String finalGrade;
        if (percentage >= 90) {
            finalGrade = "A+";
        } else if (percentage >= 80) {
            finalGrade = "A";
        } else if (percentage >= 70) {
            finalGrade = "B";
        } else if (percentage >= 60) {
            finalGrade = "C";
        } else if (percentage >= 50) {
            finalGrade = "D";
        } else {
            finalGrade = "F";
        }

        // Output results
        System.out.println("\n=== Summary ===");
        System.out.println("Total Score: " + sumOfMarks + " out of " + (subjectCount * 100));
        System.out.printf("Average Percentage: %.2f%%\n", percentage);
        System.out.println("Assigned Grade: " + finalGrade);

        input.close();
    }
}
