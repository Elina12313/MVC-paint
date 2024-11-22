package org.example.model;

import org.example.model.shape.fill.Fill;
import org.example.model.shape.fill.FillBehavior;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

public class MyShape implements  Cloneable{
    private FillBehavior fb;

    public MyShape() {
    }

    public void setFb(FillBehavior fb) {
        this.fb = fb;
    }

    public void setShape(RectangularShape shape) {
        fb.setShape(shape);
    }

    public void setFrame(Point2D x, Point2D y) {
        fb.shape().setFrameFromDiagonal(x, y);
    }

    void draw(Graphics2D g) {
        fb.draw(g);

    }
    @Override
    public MyShape clone(){
        MyShape clone = new MyShape();
        clone.setFb(fb.clone());
        RectangularShape anotherShape = (RectangularShape) fb.clone();
        clone.setShape(anotherShape);
        clone.fb.setShape(anotherShape);
        return  clone;
    }

    public RectangularShape getShape() {
        return fb.shape();
    }

}
