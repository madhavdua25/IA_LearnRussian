package pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.DB;
import model.Question;

public class UnscoredQuiz {

    private static PriorityQueue<PriorityQuestion> questionQueue;
    private static PriorityQuestion currentQuestion;

    public static void showUnscoredQuiz(Stage stage) {
        VBox bigPane = new VBox();
        bigPane.setSpacing(10);
        bigPane.setStyle("-fx-padding: 20;");

        // Load questions into PriorityQueue
        loadQuestions();

        // UI elements
        Label questionText = new Label();
        VBox answersPane = new VBox();
        answersPane.setSpacing(10);

        Button nextQuestionButton = new Button("Next Question");
        nextQuestionButton.setDisable(true); // Disable until the current question is answered

        // Initial question load
        loadNextQuestion(questionText, answersPane, nextQuestionButton);

        // Next question button action
        nextQuestionButton.setOnAction(e -> {
            if (!questionQueue.isEmpty()) {
                loadNextQuestion(questionText, answersPane, nextQuestionButton);
            } else {
                Alert alert = new Alert(AlertType.INFORMATION, "You've completed all questions!");
                alert.showAndWait();
                MainFrame.loadMenu(stage);
            }
        });

        // Main menu button
        Button mainMenuButton = new Button("Main Menu");
        mainMenuButton.setOnAction(e -> MainFrame.loadMenu(stage));

        // Add components to the root pane
        bigPane.getChildren().addAll(questionText, answersPane, nextQuestionButton, mainMenuButton);

        // Set up the scene
        Scene scene = new Scene(bigPane, 400, 300);
        stage.setScene(scene);
        stage.show();
    }

    private static void loadQuestions() {
        ArrayList<Question> questions = DB.loadQuestions();
        questionQueue = new PriorityQueue<>();

        // Wrap each Question in a PriorityQuestion and add to the PriorityQueue
        for (Question q : questions) {
            questionQueue.add(new PriorityQuestion(q, 1)); // Default priority
        }
    }

    private static void loadNextQuestion(Label questionText, VBox answersPane, Button nextQuestionButton) {
        // Clear previous answers
        answersPane.getChildren().clear();
        nextQuestionButton.setDisable(true);

        // Get the next question
        currentQuestion = questionQueue.poll();
        if (currentQuestion == null) {
            questionText.setText("No more questions available.");
            return;
        }

        Question question = currentQuestion.getQuestion();

        // Update question text
        questionText.setText("Question: " + question.getText());

        // Shuffle and display answer options
        List<String> options = new ArrayList<>();
        options.add(question.getCorrect_answer());
        options.add(question.getIncorrect1());
        options.add(question.getIncorrect2());
        options.add(question.getIncorrect3());
        Collections.shuffle(options);

        for (String option : options) {
            Button answerButton = new Button(option);
            answerButton.setOnAction(e -> {
                // Provide feedback
                boolean isCorrect = option.equals(question.getCorrect_answer());
                Alert feedback = new Alert(isCorrect ? AlertType.INFORMATION : AlertType.ERROR,
                        isCorrect ? "Correct!" : "Incorrect. The correct answer is: " + question.getCorrect_answer());
                feedback.showAndWait();

                // Adjust priority for the question based on the result
                if (isCorrect) {
                    currentQuestion.setPriority(currentQuestion.getPriority() + 10); // Decrease priority
                } else {
                    currentQuestion.setPriority(currentQuestion.getPriority() - 1); // Increase priority
                }

                // Add the question back into the queue for future quizzes
                questionQueue.add(currentQuestion);

                // Enable the Next Question button
                nextQuestionButton.setDisable(false);

                // Disable all answer buttons
                answersPane.getChildren().forEach(node -> node.setDisable(true));
            });
            answersPane.getChildren().add(answerButton);
        }
    }
}
