package pages;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import model.*;
import java.time.format.DateTimeFormatter;

public class ProgressReportPage {
    public static void showProgressReport(Stage stage) {
        HBox pane = new HBox();

        Label explanation = new Label("On right is a graph of your scores");
        Button btBack = new Button("Back to scores");

        ArrayList<Quiz> quizzes = DB.loadQuizzes(MainFrame.currentUser);
        
        CategoryAxis x = new CategoryAxis();
        x.setLabel("Date");

        NumberAxis y = new NumberAxis();
        y.setLabel("Score");

        BarChart<String, Number> barChart = new BarChart<>(x, y);
        barChart.setTitle("Recent Performance");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Scores");

        for(Quiz q : quizzes){
            String formattedDate = q.getDateString();
            series.getData().add(new XYChart.Data<>(formattedDate, q.getScore()));
        }

        barChart.getData().add(series);

        pane.getChildren().addAll(explanation, btBack, barChart);

        btBack.setOnAction(e -> ScoreReport.showGridExample(stage));

        // Add the hb as the root of the scene
        Scene scene = new Scene(pane);

        // display the scene on the screen
        stage.setScene(scene);
        stage.show(); 
    }
}
