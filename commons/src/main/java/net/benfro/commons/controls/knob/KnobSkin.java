package net.benfro.commons.controls.knob;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.SkinBase;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

/**
 * All glory to https://github.com/DeveloperFelix/KnobFx
 *
 * @author F-effect
 */
public class KnobSkin extends SkinBase<Knob> {

    private static final double PREFERRED_WIDTH = 100;
    private static final double PREFERRED_HEIGHT = 100;
    private static final double MINIMUM_WIDTH = 50;
    private static final double MINIMUM_HEIGHT = 50;
    private static final double MAXIMUM_WIDTH = 1024;
    private static final double MAXIMUM_HEIGHT = 1024;

    private double sinValue;
    private double cosValue;
    private double center_radiusX;
    private double center_radiusY;
    private double startAngle;
    private double angleStep;
    private double centerX;
    private double centerY;
    private double radius;
    private double valueAngle;

    private Canvas canvas;
    private GraphicsContext gc;


    public KnobSkin(Knob knob) {
        super(knob);
        init();
        initGraphics();
        registerListeners();
    }

    @Override
    public void dispose() {
        getChildren().clear();
        super.dispose();
    }

    private void init() {
        if (Double.compare(getSkinnable().getPrefWidth(), 0.0) <= 0 || Double.compare(getSkinnable().getPrefHeight(), 0.0) <= 0 ||
                Double.compare(getSkinnable().getWidth(), 0.0) <= 0 || Double.compare(getSkinnable().getHeight(), 0.0) <= 0) {
            if (getSkinnable().getPrefWidth() < 0 && getSkinnable().getPrefHeight() < 0) {
                getSkinnable().setPrefSize(PREFERRED_WIDTH, PREFERRED_HEIGHT);
            }
        }
        if (Double.compare(getSkinnable().getMinWidth(), 0.0) <= 0 || Double.compare(getSkinnable().getMinHeight(), 0.0) <= 0) {
            getSkinnable().setMinSize(MINIMUM_WIDTH, MINIMUM_HEIGHT);
        }

        if (Double.compare(getSkinnable().getMaxWidth(), 0.0) <= 0 || Double.compare(getSkinnable().getMaxHeight(), 0.0) <= 0) {
            getSkinnable().setMaxSize(MAXIMUM_WIDTH, MAXIMUM_HEIGHT);
        }
    }

    private void initGraphics() {

        startAngle = getSkinnable().getStartAngle();
        angleStep = getSkinnable().getAngleStep();
        centerX = getSkinnable().getPrefWidth() * 0.5;
        centerY = getSkinnable().getPrefHeight() * 0.5;
        radius = getSkinnable().getPrefWidth() * 0.02;

        canvas = new Canvas(getSkinnable().getPrefHeight() + radius, getSkinnable().getPrefWidth() + radius);
        gc = canvas.getGraphicsContext2D();

        for (double angle = 0, counter = 0; counter <= getSkinnable().getMaxValue(); angle -= angleStep, counter++) {

            sinValue = Math.sin(Math.toRadians(angle + startAngle));
            cosValue = Math.cos(Math.toRadians(angle + startAngle));

            center_radiusX = (centerX * sinValue) + centerX;
            center_radiusY = (centerY * cosValue) + centerY;

            gc.fillOval(center_radiusX, center_radiusY, radius, radius);
            gc.setFill(getSkinnable().getTickMarkColor());
        }

        drawKnobStroke(centerX, centerY);
        drawKnob(centerX, centerY);

        drawMarkerPoint(gc);
        drawMarkerTickMarks(gc);

        drawMaxLabel(gc);
        drawMinLabel(gc);

        drawValueText(gc);

        getChildren().addAll(canvas);
    }

    private void registerListeners() {
        getSkinnable().valueProperty().addListener(e -> {
            getChildren().clear();
            initGraphics();
        });
    }

    private void drawKnobStroke(double x, double y) {
        Circle knobStroke = new Circle(x * 0.88);
        knobStroke.setStrokeWidth(x * 0.09);
        knobStroke.setStroke(getSkinnable().getKnobStroke());
        knobStroke.setFill(Color.TRANSPARENT);
        knobStroke.setLayoutX(x);
        knobStroke.setLayoutY(y);

        getChildren().addAll(knobStroke);
    }

    private void drawKnob(double x, double y) {
        Circle knobStroke = new Circle(x * 0.81);
        knobStroke.setFill(getSkinnable().getKnobFill());
        knobStroke.setLayoutX(x);
        knobStroke.setLayoutY(y);
        getChildren().addAll(knobStroke);
    }

    private void drawMarkerPoint(GraphicsContext gc) {

        startAngle = getSkinnable().getStartAngle();
        valueAngle = startAngle - (getSkinnable().getValue() - getSkinnable().getMinValue()) * getSkinnable().getAngleStep();

        radius = getSkinnable().getPrefWidth() * 0.035;
        double _radius = getSkinnable().getPrefWidth() * 0.034;

        sinValue = Math.sin(Math.toRadians(valueAngle));
        cosValue = Math.cos(Math.toRadians(valueAngle));

        centerX = getSkinnable().getPrefWidth() * 0.5;
        centerY = getSkinnable().getPrefHeight() * 0.5;

        center_radiusX = (centerX * 0.75) * sinValue + centerX;
        center_radiusY = (centerY * 0.75) * cosValue + centerY;

        gc.setFill(getSkinnable().getMarkerColor());
        gc.fillOval(center_radiusX, center_radiusY, _radius, _radius);
        gc.setStroke(Color.BLACK);
        gc.strokeOval(center_radiusX, center_radiusY, radius, radius);

    }

    private void drawMarkerTickMarks(GraphicsContext gc) {

        startAngle = getSkinnable().getStartAngle();
        angleStep = getSkinnable().getAngleStep();
        centerX = getSkinnable().getPrefWidth() * 0.5;
        centerY = getSkinnable().getPrefHeight() * 0.5;
        radius = getSkinnable().getPrefWidth() * 0.018;

        for (double angle = 0, counter = 0; counter <= getSkinnable().getValue(); angle -= angleStep, counter++) {

            sinValue = Math.sin(Math.toRadians(angle + startAngle));
            cosValue = Math.cos(Math.toRadians(angle + startAngle));

            center_radiusX = (centerX * sinValue) + centerX;
            center_radiusY = (centerY * cosValue) + centerY;

            gc.fillOval(center_radiusX, center_radiusY, radius, radius);
            gc.setFill(getSkinnable().getMarkerColor());
        }

    }

    private void drawMinLabel(GraphicsContext gc) {

        centerX = getSkinnable().getPrefWidth() * 0.5;
        centerY = getSkinnable().getPrefHeight() * 0.56;

        startAngle = getSkinnable().getStartAngle();
        valueAngle = startAngle - ((int) getSkinnable().getMinValue() - getSkinnable().getMinValue()) * getSkinnable().getAngleStep();

        sinValue = Math.sin(Math.toRadians(valueAngle));
        cosValue = Math.cos(Math.toRadians(valueAngle));

        center_radiusX = (centerX * sinValue) + centerX;
        center_radiusY = (centerY * cosValue) + centerY;

        gc.setFont(Font.font(getSkinnable().getPrefWidth() / 25));
        gc.strokeText(getSkinnable().getMinText(), center_radiusX, center_radiusY);
    }

    private void drawMaxLabel(GraphicsContext gc) {

        centerX = getSkinnable().getPrefWidth() * 0.5;
        centerY = getSkinnable().getPrefHeight() * 0.56;

        startAngle = getSkinnable().getStartAngle();
        valueAngle = startAngle - ((int) getSkinnable().getMaxValue() - getSkinnable().getMinValue()) * getSkinnable().getAngleStep();

        sinValue = Math.sin(Math.toRadians(valueAngle));
        cosValue = Math.cos(Math.toRadians(valueAngle));

        center_radiusX = (centerX * sinValue) + centerX;
        center_radiusY = (centerY * cosValue) + centerY;

        gc.setFont(Font.font(getSkinnable().getPrefWidth() / 25));
        gc.strokeText(getSkinnable().getMaxText(), center_radiusX, center_radiusY);
    }

    private void drawValueText(GraphicsContext gc) {
        centerX = getSkinnable().getPrefWidth() * 0.45;
        centerY = getSkinnable().getPrefHeight() * 0.45;

        gc.setFont(Font.font(getSkinnable().getPrefWidth() / 10));
        gc.strokeText(String.valueOf((int) getSkinnable().getValue()), centerX, centerY);

    }
}