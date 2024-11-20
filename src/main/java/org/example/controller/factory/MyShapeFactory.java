package org.example.controller.factory;

import org.example.model.MyShape;
import org.example.model.shape.fill.Fill;
import org.example.model.shape.fill.FillBehavior;
import org.example.model.shape.fill.NoFill;

import java.awt.geom.RectangularShape;

public class MyShapeFactory {

    private MenuState state;
    public static MyShapeFactory instance;

    public static  synchronized MyShapeFactory getInstance(){
        if (instance == null){
            instance = new MyShapeFactory();
        }
        return instance;
    }
    public MyShapeFactory(){

    }
    public void config (MenuState state) {
        this.state = state;
    }

    public MyShape createShape(){
        MyShape newShape = new MyShape();
        RectangularShape shape = state.getShapeType().createShape();

        FillBehavior fillBehavior = state.isFill() ? new Fill() : new NoFill( );
        fillBehavior.setShape(shape);
        fillBehavior.setColor(state.getColor());

        newShape.setFb(fillBehavior);

        return newShape;
    }

}
