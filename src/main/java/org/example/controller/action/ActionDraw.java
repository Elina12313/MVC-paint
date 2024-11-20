package org.example.controller.action;

import org.example.controller.factory.MyShapeFactory;
import org.example.model.Model;
import org.example.model.MyShape;

import java.awt.*;
import java.awt.geom.Point2D;

public class ActionDraw {
    private Model model;
    private MyShapeFactory myShapeFactory;
    private MyShape shape;
    private Point2D firstPoint;
    private Point2D secondPoint;

    public ActionDraw(Model model, MyShape shape) {
        this.model = model;
        this.shape = shape; // сохраняем переданный MyShape
        myShapeFactory = MyShapeFactory.getInstance();
    }

    public  void  stretchShape (Point point){
        secondPoint =  point;
        shape.setFrame(firstPoint, secondPoint);
        model.update();

    }
    public void createShape (Point point){
//        firstPoint =  point;
//        shape = shape.clone();
//        model.createCurrentShape(shape);
//        model.update();

        firstPoint = point;
        shape = myShapeFactory.createShape();
        model.createCurrentShape(shape.clone());
        model.update();


    }
 public MyShape getShape() {
    return shape;
  }
//    @Override
    public void mousePressed(Point2D point) {
        secondPoint = point;
        shape = myShapeFactory.createShape();
        model.addCurrentShape(shape);
        model.update();
    }

//    @Override
    public void mouseDragged(Point2D point) {
        firstPoint = point;
        shape.setFrame(firstPoint, secondPoint);
        model.update();
    }
}
