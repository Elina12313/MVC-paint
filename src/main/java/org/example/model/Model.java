package org.example.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

// TODO: 25.10.2024 Сделать singleton класс
public class Model extends Observable {

    private MyShape currentShape;
    private List<MyShape> shapeList = new ArrayList<>();

    public void  createCurrentShape(MyShape shape){
        currentShape = shape;
        shapeList.add(shape);
    }

    public void setMyShape(MyShape myShape) {
        this.currentShape = myShape;
    }

    public void draw(Graphics2D g) {
        for( MyShape shape : shapeList){
            shape.draw(g);
        }

//        currentShape.draw(g);
    }
    public void update()
    {
        this.setChanged();
        this.notifyObservers();
    }
    public void addCurrentShape(MyShape sampleShape){
        shapeList.add(sampleShape);
    }
    public List<MyShape> getShapeList() {
        return shapeList;
    }
}
