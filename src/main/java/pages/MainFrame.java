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

// import javafx.beans.value.ChangeListener;
// import javafx.beans.value.ObservableValue;
// import javafx.event.ActionEvent;
// import javafx.event.EventHandler;

public class MainFrame extends Application {
    public static final Font PAGE_HEADING_FONT = new Font("Arial Bold", 28);
    public static final Font TABLE_HEADING_FONT = new Font("Arial Bold", 24);
    public static final Font TABLE_BODY_FONT = new Font("Arial", 22);

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage)  {
        loadMenu(primaryStage);
}
    public static void loadMenu (Stage stage) {        
        Button btHBox = new Button("Scored Quiz");
        Button btVBox = new Button("Unscored Practice");
        Button btGrid = new Button("Score Report");
        ImageView russiaFlag = new ImageView("file:image/russiaflag.png");
        //Button btUser = new Button("Sign in to track progress!");
        
        ArrayList<Student> students = DB.loadStudents();
        ComboBox<Student> namesComboBox = new ComboBox<>();
        namesComboBox.getItems().addAll(students);

        Label users = new Label("New user: ");
        TextField newUser = new TextField ();
        DB.insertStudent(newUser.getText());


       //Button btBorderPane = new Button("BorderPane Example");
       //Button btMixAndMatch = new Button("Mix and Match Demo");

        btHBox.setOnAction(e-> HBoxExamplePage.showHBoxExample(stage));
        btVBox.setOnAction(e-> VBoxExamplePage.showVBoxExample(stage));
        btGrid.setOnAction(e-> GridExamplePage.showGridExample(stage));
        //btUser.setOnAction(e-> UserSignIn.showUserSignIn(stage));
        //btBorderPane.setOnAction(e-> BorderPaneExamplePage.showBorderPaneExample(stage));
        //btMixAndMatch.setOnAction(e-> MixMatchDemo.showMixMatchDemo(stage));

        GridPane gp = new GridPane();
        gp.setAlignment(Pos.TOP_CENTER);
        gp.add(btHBox, 0, 1);
        gp.add(btVBox, 1, 1);
        gp.add(btGrid, 2, 1);
        gp.add(namesComboBox, 0, 2);
        gp.add(users, 1, 2);
        gp.add(newUser, 3, 2);
        gp.add(russiaFlag, 1, 3);
        //gp.add(btUser, 1, 2);
        //gp.add(btBorderPane, 3, 1);
        //gp.add(btMixAndMatch, 4, 1);
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
        //stage.setTitle("JavaFX Examples");
        stage.setWidth(1100);
        stage.setHeight(600);
        stage.show();
    }




}