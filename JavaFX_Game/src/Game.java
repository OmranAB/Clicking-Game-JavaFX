import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

public class Game extends Application {

    private Button circle;
    private Button ellipse;
    private Button square;
    private Button triangle;
    private Button rectangle;


    private Text ScoreCount = new Text();
    private Integer totalScore = 0;
    private int numberOfShapes = 0;
    private final Integer rectanglePoints = 50;
    private final Integer trianglePoints = 40;
    private final Integer squarePoints = 30;
    private final Integer ellipsePoints = 20;
    private final Integer circlePoints = 10;


    private Text timerCount = new Text();
    private Integer gameTime = 60;

    private Random rand = new Random();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();

        ArrayList<Button> list = new ArrayList<>();

        circle = createButton(1);
        ellipse = createButton(2);
        square = createButton(3);
        triangle = createButton(4);
        rectangle = createButton(5);

        list.add(circle);
        list.add(ellipse);
        list.add(square);
        list.add(triangle);
        list.add(rectangle);

        pane.getChildren().addAll(circle,ellipse,rectangle,triangle,square);


        ScoreCount.setText("  Score : " + totalScore);
        ScoreCount.setStyle("-fx-font-size: 2em;");
        StackPane scorePane = new StackPane();
        scorePane.getChildren().add(ScoreCount);
        scorePane.setAlignment(Pos.TOP_LEFT);
        pane.getChildren().add(scorePane);

        Timeline scoreCountCalculate = new Timeline();
        scoreCountCalculate.setCycleCount(Timeline.INDEFINITE);
        scoreCountCalculate.getKeyFrames().add(new KeyFrame(Duration.millis(50), event -> {
            ScoreCount.setText("  Score : " + totalScore);
        }));
        scoreCountCalculate.play();


        timerCount.setText("Time : " + gameTime.toString() + "  ");
        timerCount.setStyle("-fx-font-size: 2em;");
        StackPane timePane = new StackPane();
        timePane.getChildren().add(timerCount);
        timePane.relocate(500,0);
        pane.getChildren().add(timePane);


        Timeline timerCountDown = new Timeline();
        timerCountDown.setCycleCount(Timeline.INDEFINITE);
        timerCountDown.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> {
            gameTime--;
            timerCount.setText("Time : " +gameTime.toString() + "  ");
            if(gameTime == 0) {
                timerCountDown.stop();
                pane.getChildren().removeAll(circle,square,ellipse,triangle,rectangle);
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                Text boardTitle = new Text();
                boardTitle.setText("Score Board");
                boardTitle.setStyle("-fx-font-size: 3em;");

                Text scoreBoardPane = scoreBoard(totalScore,numberOfShapes,formatter.format(date));
                boardTitle.relocate(200,170);
                scoreBoardPane.relocate(80,200);
                pane.getChildren().addAll(scoreBoardPane,boardTitle);
            }
        }));
        timerCountDown.play();


        circle.setOnMouseClicked(event -> {
            totalScore += circlePoints;
            numberOfShapes++;
            pane.getChildren().remove(circle);
            String colorString = "rgb(" + rand.nextInt(255) + "," + rand.nextInt(255) + "," + rand.nextInt(255) + ");";
            circle.setStyle("-fx-border-color: black ;-fx-background-color:"+ colorString);
            transitionAnimation(circle);
            pane.getChildren().add(circle);
        });
        ellipse.setOnMouseClicked(event -> {
            totalScore += ellipsePoints;
            numberOfShapes++;
            pane.getChildren().remove(ellipse);
            String colorString = "rgb(" + rand.nextInt(255) + "," + rand.nextInt(255) + "," + rand.nextInt(255) + ");";
            ellipse.setStyle("-fx-border-color: black ;-fx-background-color:"+ colorString);
            transitionAnimation(ellipse);
            pane.getChildren().add(ellipse);
        });
        square.setOnMouseClicked(event -> {
            totalScore += squarePoints;
            numberOfShapes++;
            pane.getChildren().remove(square);
            String colorString = "rgb(" + rand.nextInt(255) + "," + rand.nextInt(255) + "," + rand.nextInt(255) + ");";
            square.setStyle("-fx-border-color: black ;-fx-background-color:"+ colorString);
            transitionAnimation(square);
            pane.getChildren().add(square);
        });
        triangle.setOnMouseClicked(event -> {
            totalScore += trianglePoints;
            numberOfShapes++;
            pane.getChildren().remove(triangle);
            String colorString = "rgb(" + rand.nextInt(255) + "," + rand.nextInt(255) + "," + rand.nextInt(255) + ");";
            triangle.setStyle("-fx-border-color: black ;-fx-background-color:"+ colorString);
            transitionAnimation(triangle);
            pane.getChildren().add(triangle);
        });
        rectangle.setOnMouseClicked(event -> {
            totalScore += rectanglePoints;
            numberOfShapes++;
            pane.getChildren().remove(rectangle);
            String colorString = "rgb(" + rand.nextInt(255) + "," + rand.nextInt(255) + "," + rand.nextInt(255) + ");";
            rectangle.setStyle("-fx-border-color: black ;-fx-background-color:"+ colorString);
            transitionAnimation(rectangle);
            pane.getChildren().add(rectangle);
        });


        Scene scene = new Scene(pane, 600, 640);
        primaryStage.setTitle("Game");
        primaryStage.setScene(scene);
        primaryStage.show();
        pane.requestFocus();
    }

    public void transitionAnimation(Button shape){
        PathTransition transition = new PathTransition();

        transition.setNode(shape);
        transition.setPath(generatePath());
        transition.setCycleCount(Timeline.INDEFINITE);
        transition.setRate(0.05);
        transition.setAutoReverse(true);
        transition.setOnFinished(e -> {
            transition.setPath(generatePath());
            transition.play();
        });
        transition.play();
    }

    public Path generatePath(){
        Path path = new Path();

        path.getElements().add(new MoveTo(rand.nextInt(600),rand.nextInt(500)));
        path.getElements().add(new LineTo(rand.nextInt(600),rand.nextInt(570)));
        path.getElements().add(new LineTo(rand.nextInt(600),rand.nextInt(570)));
        path.getElements().add(new LineTo(rand.nextInt(600),rand.nextInt(570)));
        path.getElements().add(new LineTo(rand.nextInt(600),rand.nextInt(570)));
        path.getElements().add(new LineTo(rand.nextInt(600),rand.nextInt(570)));
        path.getElements().add(new LineTo(rand.nextInt(600),rand.nextInt(570)));
        path.getElements().add(new ClosePath());

        return path;
    }

    public Button createButton(int shapeType){
        String colorString = "rgb(" + rand.nextInt(255) + "," + rand.nextInt(255) + "," + rand.nextInt(255) + ");";
        switch (shapeType){
            case 1:
                Button circle = new Button();
                circle.setShape(new Circle(30));
                circle.setMinSize(60,60);
                circle.setMaxSize(60,60);
                circle.setStyle("-fx-border-color: black ;-fx-background-color:"+ colorString);
                transitionAnimation(circle);
                return circle;
            case 2:
                Button ellipse = new Button();
                ellipse.setShape(new Ellipse(40,80));
                ellipse.setMinSize(40,80);
                ellipse.setMaxSize(40,80);
                ellipse.setStyle("-fx-border-color: black ;-fx-background-color:"+ colorString);
                transitionAnimation(ellipse);
                return ellipse;
            case 3:
                Button square = new Button();
                square.setShape(new Rectangle(50,50));
                square.setMinSize(50,50);
                square.setMaxSize(50,50);
                square.setStyle("-fx-border-color: black ;-fx-background-color:"+ colorString);
                transitionAnimation(square);
                return square;
            case 4:
                Button triangle = new Button();
                Polygon shape = new Polygon();
                shape.getPoints().addAll(50.0, 0.0,  0.0, 50.0,
                        100.0, 50.0, 50.0, 0.0);
                triangle.setShape(shape);
                triangle.setMinSize(100,50);
                triangle.setMaxSize(100,50);
                triangle.setStyle("-fx-border-color: black ;-fx-background-color:"+ colorString);
                transitionAnimation(triangle);
                return triangle;
            default:
                Button rectangle = new Button();
                rectangle.setShape(new Rectangle(80,40));
                rectangle.setMinSize(80,40);
                rectangle.setMinSize(80,40);
                rectangle.setStyle("-fx-border-color: black ;-fx-background-color:"+ colorString);
                transitionAnimation(rectangle);
                return rectangle;
        }
    }

    public Text scoreBoard(int newScore, int numberOfShapes, String date) {

        File file = new File("ScoreBoard.txt");
        Scanner input = null;
        try {
            input = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Text scoreBoard = new Text();

        scoreBoard.setFont(new Font("SansSerif", 16));
        String board = "";
        while(input.hasNextLine()){
            board += input.nextLine() + "\n";

        }
        board += "\t" + newScore + "\t\t\t\t" + numberOfShapes + "\t\t\t" + date;
        scoreBoard.setText(board);


        try {
            PrintWriter pw = new PrintWriter(file);
            pw.print(board);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return scoreBoard;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
