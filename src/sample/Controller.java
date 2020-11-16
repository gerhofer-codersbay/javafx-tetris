package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
    private Rotation rotation;
    private Timeline gamePlay;

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
        initializeNewTetromino();
        gamePlay = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    moveDown();
                })
        );
        gamePlay.setCycleCount(Animation.INDEFINITE);
        gamePlay.play();
    }

    private void initializeNewTetromino() {
        double randomTetromino = Math.random() * Tetromino.values().length;
        this.tetromino = Tetromino.values()[(int) randomTetromino];
        double randomRotation = Math.random() * Rotation.values().length;
        this.rotation = Rotation.values()[(int) randomRotation];
        this.position = new Cell(0, 4);
        drawTetromino();
    }

    private void moveDown() {
        List<Integer> positions = this.tetromino.getPositions(rotation, tetrisData.getWidth());
        if (!touchesBottom(positions) && !touchesStuckTetromino(positions, tetrisData.getWidth())) {
            undrawTetromino();
            position.moveDown();
            drawTetromino();
        } else {
            stickTetromino();
            initializeNewTetromino();
        }
    }

    private boolean touchesStuckTetromino(List<Integer> positions, Integer futureMovement) {
        for (Integer pos : positions) {
            Node currentField = tetrisBoard.getChildren()
                    .get(pos + futureMovement + position.getPosition(tetrisData.getWidth()));
            if (currentField.getStyleClass().contains("stuck")) {
                return true;
            }
        }
        return false;
    }

    public void stop() {
        gamePlay.stop();
    }

    public void handleKeypress(KeyEvent keyEvent) {
        List<Integer> positions = this.tetromino.getPositions(rotation, tetrisData.getWidth());
        if (keyEvent.getCode() == KeyCode.A) {
            if (!touchesLeftEdge(positions) && !touchesStuckTetromino(positions, -1)) {
                undrawTetromino();
                position.moveLeft();
                drawTetromino();
            }
        } else if (keyEvent.getCode() == KeyCode.S) {
            moveDown();
        } else if (keyEvent.getCode() == KeyCode.D) {
            if (!touchesRightEdge(positions) && !touchesStuckTetromino(positions, 1)) {
                undrawTetromino();
                position.moveRight();
                drawTetromino();
            }
        } else if (keyEvent.getCode() == KeyCode.W) {
            undrawTetromino();
            rotation = Rotation.next(rotation);
            drawTetromino();
        }
    }

    private boolean touchesBottom(List<Integer> positions) {
        for (Integer pos: positions) {
            if ((pos + position.getPosition(tetrisData.getWidth())) >= 190) {
                return true;
            }
        }
        return false;
    }

    private boolean touchesLeftEdge(List<Integer> positions) {
        for (Integer pos: positions) {
            if ((pos + position.getPosition(tetrisData.getWidth())) % 10 == 0) {
                return true;
            }
        }
        return false;
    }

    private boolean touchesRightEdge(List<Integer> positions) {
        for (Integer pos: positions) {
            if ((pos + position.getPosition(tetrisData.getWidth())) % 10 == 9) {
                return true;
            }
        }
        return false;
    }

    private void drawTetromino() {
        List<Integer> positions = this.tetromino.getPositions(rotation, tetrisData.getWidth());
        for (Integer relativePosition : positions) {
            tetrisBoard.getChildren()
                    .get(relativePosition + position.getPosition(tetrisData.getWidth()))
                    .getStyleClass().add("current");
        }
    }

    private void undrawTetromino() {
        List<Integer> positions = this.tetromino.getPositions(rotation, tetrisData.getWidth());
        for (Integer relativePosition : positions) {
            tetrisBoard.getChildren()
                    .get(relativePosition + position.getPosition(tetrisData.getWidth()))
                    .getStyleClass().remove("current");
        }
    }

    private void stickTetromino() {
        List<Integer> positions = this.tetromino.getPositions(rotation, tetrisData.getWidth());
        for (Integer relativePosition : positions) {
            if ((relativePosition + position.getPosition(tetrisData.getWidth())) < 20) {
                Stage popUpStage = new Stage();
                popUpStage.initModality(Modality.APPLICATION_MODAL);
                Label gameOver = new Label("Game Over");
                Scene scene = new Scene(new StackPane(gameOver));
                popUpStage.setScene(scene);
                popUpStage.show();
                gamePlay.stop();
                return;
            }
            tetrisBoard.getChildren()
                    .get(relativePosition + position.getPosition(tetrisData.getWidth()))
                    .getStyleClass().remove("current");
            tetrisBoard.getChildren()
                    .get(relativePosition + position.getPosition(tetrisData.getWidth()))
                    .getStyleClass().add("stuck");
        }
    }

}
