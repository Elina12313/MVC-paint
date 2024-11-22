package org.example.controller.action;

import org.example.controller.factory.MyShapeFactory;
import org.example.model.Model;
import org.example.model.MyShape;

import java.awt.*;
import java.awt.geom.Point2D;

public class ActionDraw implements AppAction{
    private Model model;
    private MyShapeFactory myShapeFactory;
    private MyShape shape;
    private Point2D firstPoint;
    private Point2D secondPoint;

    public ActionDraw(Model model, MyShape shape) {
        this.model = model;
        this.shape = shape;
        myShapeFactory = MyShapeFactory.getInstance();
    }
 public MyShape getShape() {
    return shape;
  }

    @Override
    public void mouseDragged(Point point) {
    firstPoint = point;
    shape.setFrame(firstPoint, secondPoint);
    model.update();

    }

    @Override
    public void mousePressed(Point point) {
        secondPoint = point;
        shape = myShapeFactory.createShape();
        model.addCurrentShape(shape);
        model.update();

    }
}
