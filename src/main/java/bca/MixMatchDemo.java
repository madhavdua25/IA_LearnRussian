package bca;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MixMatchDemo {
    public static final Font PAGE_HEADING_FONT = new Font("Arial Bold", 24);
    public static final Font SUB_HEADING_FONT = new Font("Arial Bold", 20);
    public static final Font CHIPS_LABEL_FONT = new Font("Arial", 18);
    public static final Font BUTTON_FONT = new Font("Arial", 18);

    public static void showMixMatchDemo(Stage stage) {

        BorderPane pane = new BorderPane();

        HBox hbCenter = new HBox();
        VBox vbDealer = new VBox();
        VBox vbPlayer = new VBox();

        pane.setPadding(new Insets(0,10,0,10));
        pane.setStyle("-fx-background-color: lightblue;");

        hbCenter.setPadding(new Insets(10,20,10,20));
        vbDealer.setPadding(new Insets(10,20,10,20));
        vbPlayer.setPadding(new Insets(10,20,10,20));
        hbCenter.getChildren().addAll(vbDealer, vbPlayer);

        Label lblDealer = new Label("Dealer");    
        lblDealer.setFont(SUB_HEADING_FONT);    
        HBox hbDealerCards = new HBox();
        hbDealerCards.getChildren().add(new ImageView("file:image/10.png"));
        hbDealerCards.getChildren().add(new ImageView("file:image/20.png"));
        hbDealerCards.getChildren().add(new ImageView("file:image/35.png"));
        hbDealerCards.getChildren().add(new ImageView("file:image/42.png"));
        hbDealerCards.getChildren().add(new ImageView("file:image/45.png"));
        vbDealer.getChildren().addAll(lblDealer, hbDealerCards);

        Label lblPlayer = new Label("Recent quiz score: x/10");
        lblPlayer.setFont(SUB_HEADING_FONT);    
        HBox hbPlayerCards = new HBox();

        // you can add the hbox to the vbox even before you add the children to the hbox.
        vbPlayer.getChildren().addAll(lblPlayer, hbPlayerCards);    

        hbPlayerCards.getChildren().add(new ImageView("file:image/5.png"));
        hbPlayerCards.getChildren().add(new ImageView("file:image/18.png"));
        hbPlayerCards.getChildren().add(new ImageView("file:image/31.png"));
        hbPlayerCards.getChildren().add(new ImageView("file:image/32.png"));
        hbPlayerCards.getChildren().add(new ImageView("file:image/45.png"));


        pane.setCenter(hbCenter);


        VBox vbDealerChips = new VBox();
        Label lblDealerChips = new Label("Dealer Chips");
        lblDealerChips.setFont(CHIPS_LABEL_FONT);    
        Label lblDealerChipsRemaining = new Label("245");
        vbDealerChips.getChildren().addAll(lblDealerChips, lblDealerChipsRemaining);

        VBox vbPlayerChips = new VBox();
        Label lblPlayerChips = new Label("Player Chips");
        lblPlayerChips.setFont(CHIPS_LABEL_FONT);    
        Label lblPlayerChipsRemaining = new Label("195");
        vbPlayerChips.getChildren().addAll(lblPlayerChips, lblPlayerChipsRemaining);


        Label lblTitle = new Label("Poker Training Academy");
        lblTitle.setFont(PAGE_HEADING_FONT);
        HBox hbActions = new HBox();
        Button btCall = new Button("Call");
        Button btRaise = new Button("Raise");
        Button btMainMenu = new Button("Main Menu");
        btCall.setFont(BUTTON_FONT);
        btRaise.setFont(BUTTON_FONT);
        btMainMenu.setFont(BUTTON_FONT);

        hbActions.getChildren().addAll(btCall, btRaise, btMainMenu);
        hbActions.setAlignment(Pos.CENTER);

        
        // Set what happens when the button is pressed.
        btMainMenu.setOnAction(e -> MainFrame.loadMenu(stage));

        // A border pane has exactly 5 regions. You can add exactly one widget to each region.
        pane.setTop(lblTitle);
        BorderPane.setAlignment(lblTitle,Pos.CENTER);

        pane.setCenter(hbCenter);
        BorderPane.setAlignment(hbCenter,Pos.CENTER);

        pane.setLeft(vbDealerChips);
        pane.setRight(vbPlayerChips);
        pane.setBottom(hbActions);
        BorderPane.setAlignment(hbActions,Pos.CENTER);

        // Add the hb as the root of the scene
        Scene scene = new Scene(pane);

        // display the scene on the screen
        stage.setScene(scene);
        stage.show();
    }

}