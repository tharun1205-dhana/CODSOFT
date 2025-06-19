import java.util.*;

public class SimpleQuiz {

    static class QuizQuestion {
        String prompt;
        String[] choices;
        int correctOption;

        QuizQuestion(String prompt, String[] choices, int correctOption) {
            this.prompt = prompt;
            this.choices = choices;
            this.correctOption = correctOption;
        }
    }

    static Scanner input = new Scanner(System.in);
    static int totalScore = 0;
    static List<String> resultLog = new ArrayList<>();
    static boolean isAnswered = false;

    public static void main(String[] args) {
        List<QuizQuestion> quizQuestions = List.of(
            new QuizQuestion("What is the capital city of France?",
                    new String[]{"1. Berlin", "2. Madrid", "3. Paris", "4. Rome"}, 3),
            new QuizQuestion("Which programming language is used in browsers?",
                    new String[]{"1. Java", "2. C", "3. Python", "4. JavaScript"}, 4),
            new QuizQuestion("Calculate: 2 + 2 = ?",
                    new String[]{"1. 3", "2. 4", "3. 5", "4. 22"}, 2)
        );

        System.out.println("=== SIMPLE QUIZ ===");
        System.out.println("You will get 10 seconds for each question.\n");

        for (int i = 0; i < quizQuestions.size(); i++) {
            isAnswered = false;
            presentQuestion(quizQuestions.get(i), i + 1);
        }

        System.out.println("\n=== FINAL RESULT ===");
        System.out.println("Your total score: " + totalScore + "/" + quizQuestions.size());
        System.out.println("\nDetails:");
        resultLog.forEach(System.out::println);
    }

    private static void presentQuestion(QuizQuestion question, int index) {
        System.out.println("Question " + index + ": " + question.prompt);
        for (String choice : question.choices) {
            System.out.println(choice);
        }

        Timer questionTimer = new Timer();
        questionTimer.schedule(new TimerTask() {
            public void run() {
                if (!isAnswered) {
                    System.out.println("\n⏰ Time's up! Moving on.\n");
                    resultLog.add("Q" + index + ": ✘ (Timed out)");
                    isAnswered = true;
                }
                questionTimer.cancel();
            }
        }, 10000);

        int userAnswer = -1;

        try {
            System.out.print("Select an option (1-4): ");
            userAnswer = input.nextInt();
            if (!isAnswered) {
                if (userAnswer == question.correctOption) {
                    System.out.println("✔ Correct!\n");
                    totalScore++;
                    resultLog.add("Q" + index + ": ✔");
                } else {
                    System.out.println("✘ Wrong answer. Correct option: " + question.correctOption + "\n");
                    resultLog.add("Q" + index + ": ✘");
                }
                isAnswered = true;
            }
        } catch (InputMismatchException e) {
            if (!isAnswered) {
                System.out.println("Invalid input! Skipping.\n");
                resultLog.add("Q" + index + ": ✘ (Invalid input)");
                input.next(); // clear bad input
                isAnswered = true;
            }
        }

        try {
            Thread.sleep(500); // slight pause
        } catch (InterruptedException e) {
            // ignore
        }

        questionTimer.cancel();
    }
}
