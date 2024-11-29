package org.example.controller.action;

import org.example.controller.factory.MyShapeFactory;
import org.example.model.Model;
import org.example.model.MyShape;

import java.awt.*;
import java.awt.geom.Point2D;

public class ActionMove implements AppAction{
    private Model model;
    private MyShapeFactory myShapeFactory;
    private MyShape shape;
    private Point2D firstPoint;
    private Point2D secondPoint;
    private MyShape drawableShape;

    public ActionMove(Model model) {
        this.model = model;
    }

    @Override
    public void mousePressed(Point point) {
        firstPoint = point;
        shape = model.getShapeList()
                .stream()
                .filter(shape -> shape.getShape().contains(point))
                .findFirst()
                .orElse(null);
        model.update();
    }

    @Override
    public void mouseDragged(Point point) {
        secondPoint = point;
        if (shape == null){
            return;
        }
        double deltaX = secondPoint.getX() - firstPoint.getX();
        double deltaY = secondPoint.getY() - firstPoint.getY();

        Point2D newShapeFirstPoint = new Point2D.Double();
        newShapeFirstPoint.setLocation(shape.getShape().getMaxX() + deltaX,
                shape.getShape().getMaxY() + deltaY);

        Point2D newShapeSecondPoint = new Point2D.Double();
        newShapeSecondPoint.setLocation(shape.getShape().getMinX() + deltaX,
                shape.getShape().getMinY() + deltaY);

        shape.getShape().setFrameFromDiagonal(newShapeFirstPoint, newShapeSecondPoint);
        firstPoint = secondPoint;
        model.update();

    }
    @Override
    public void execute() {
        model.addCurrentShape(drawableShape);
        model.update();
    }

    @Override
    public void unexecute() {
        drawableShape = model.getLastShape();
        model.removeLastShape();
        model.update();

    }

    @Override
    public AppAction cloneAction() {
        ActionMove actionMove = new ActionMove(model);
        actionMove.shape = shape.clone();
        actionMove.drawableShape = drawableShape;
        return actionMove;
    }
}
