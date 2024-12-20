package org.example.model;

import org.example.model.shape.fill.FillBehavior;

import java.awt.*;
import java.awt.geom.Point2D;
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
        return  clone;
    }

    public RectangularShape getShape() {
        return fb.shape();
    }

}
