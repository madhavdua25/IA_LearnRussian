package pages;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.DB;
import model.Student;

public class MainFrame extends Application {
    public static final Font PAGE_HEADING_FONT = new Font("Arial Bold", 28);
    public static final Font TABLE_HEADING_FONT = new Font("Arial Bold", 24);
    public static final Font TABLE_BODY_FONT = new Font("Arial", 22);

    public static int currentUser;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage)  {
        loadMenu(primaryStage);
}
    public static void loadMenu (Stage stage) {     
        
        Button btSQ = new Button("Scored Quiz");
        Button btUQ = new Button("Unscored Practice");
        Button btSR = new Button("Score Report");
        ImageView russiaFlag = new ImageView("file:image/russiaflag.png");
        
        
        ArrayList<Student> students = DB.loadStudents();
        ComboBox<Student> namesComboBox = new ComboBox<>();
        namesComboBox.getItems().addAll(students);

        TextField newUser = new TextField ();
        Button btUser = new Button("That's me!");

        if(currentUser > 0){
            namesComboBox.setDisable(true);
            newUser.setEditable(false);
        }


    
        btSQ.setOnAction(e-> {
            if(currentUser > 0){
                ScoredQuiz.showHBoxExample(stage);
            }
        });
        btUQ.setOnAction(e-> UnscoredQuiz.showVBoxExample(stage));
        btSR.setOnAction(e-> {
            if(currentUser > 0){
                ScoreReport.showGridExample(stage);
            }
        });
        
        
        btUser.setOnAction(e-> {
            if(newUser.getText().length() > 0){
                DB.insertStudent(students.size()+1, newUser.getText());
                currentUser = students.size()+1;
                loadMenu(stage);
            } else {
                Student s = namesComboBox.getValue();
                currentUser = s.getStudent_id();
                loadMenu(stage);
            }
        });
        

        GridPane gp = new GridPane();
        gp.setAlignment(Pos.TOP_CENTER);
        gp.add(btSQ, 0, 1);
        gp.add(btUQ, 1, 1);
        gp.add(btSR, 2, 1);
        gp.add(namesComboBox, 0, 2);
        gp.add(btUser, 2, 2);
        gp.add(newUser, 1, 2);
        gp.add(russiaFlag, 1, 3);
        gp.add(new Label("Current user: " + currentUser), 1, 4);

        gp.setHgap(10);
        gp.setVgap(10);

        Label pageTitle = new Label("Learn Russian! / Учите русский язык!");
        pageTitle.setFont(MainFrame.PAGE_HEADING_FONT);
        pageTitle.setPadding(new Insets(0,0,30,0));
        
        VBox vb = new VBox();
        vb.setAlignment(Pos.TOP_CENTER);
        vb.getChildren().addAll(pageTitle, gp);

        Scene scene = new Scene(vb);

        stage.setScene(scene);
        stage.setWidth(1100);
        stage.setHeight(600);
        stage.show();
    }




}