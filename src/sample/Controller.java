package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    TilePane tetrisBoard;
    TetrisBoard tetrisData;

    private Tetromino tetromino;
    private Cell position;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tetrisData = new TetrisBoard(10, 20);
        for (Cell cell : tetrisData.getCells()) {
            Rectangle rectangle = new Rectangle(20, 20);
            rectangle.getStyleClass().add("tetromino");
            tetrisBoard.getChildren().add(rectangle);
        }
    }

    public void start() {
        this.tetromino = Tetromino.L;
        this.position = new Cell(0, 4);
        drawTetromino();

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    undrawTetromino();
                    position.moveDown();
                    drawTetromino();
                })
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        //AnimationTimer animationTimer = new AnimationTimer() {
        //    @Override
        //    public void handle(long l) {
        //        undrawTetromino();
        //        position.moveDown();
        //        drawTetromino();
        //    }
        //};
        //animationTimer.start();
    }

    private void drawTetromino() {
        List<Integer> positions = this.tetromino.getPositions(tetrisData.getWidth());
        for (Integer relativePosition : positions) {
            tetrisBoard.getChildren()
                    .get(relativePosition + position.getPosition(tetrisData.getWidth()))
                    .getStyleClass().add("current");
        }
    }

    private void undrawTetromino() {
        List<Integer> positions = this.tetromino.getPositions(tetrisData.getWidth());
        for (Integer relativePosition : positions) {
            tetrisBoard.getChildren()
                    .get(relativePosition + position.getPosition(tetrisData.getWidth()))
                    .getStyleClass().remove("current");
        }
    }

}
