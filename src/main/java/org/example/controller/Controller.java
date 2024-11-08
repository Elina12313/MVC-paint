package org.example.controller;

import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.shape.fill.Fill;
import org.example.view.MyFrame;
import org.example.view.MyPanel;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

// TODO: 24.10.2024 Сделать singleton класс
public class Controller {
    private Model model;
    private MyFrame frame;
    private MyPanel panel;
    private Point2D firstPoint;
    private Point2D secondPoint;
    private MyShape simpleShape;
    private ActionDraw actionDraw;
    private static Controller instance;
    public static Controller getInstance(){
        if (instance == null){
            instance = new Controller();
        }
        return instance;
    }
    private Controller() {
        model = new Model();
        simpleShape = new MyShape(new Rectangle2D.Double());

        Fill fill = new Fill();
        fill.setColor(Color.yellow);

        simpleShape.setFb(fill);

         actionDraw = new ActionDraw(model, simpleShape);

        model.setMyShape(simpleShape);

        panel = new MyPanel(this, actionDraw);
        // TODO: 25.10.2024 Поменять наблюдатель на более современную реализацию
        model.addObserver(panel);

        frame = new MyFrame();
        frame.setPanel(panel);
    }

    public void getPointOne(Point2D p){
        actionDraw.createShape((Point) p);
//        model.createCurrentShape(simpleShape.clone());
//        firstPoint = p;
    }
    public void getPointTwo(Point2D p){
        actionDraw.stretchShape((Point) p);
//        secondPoint = p;
//        model.changeShape(firstPoint, secondPoint);
    }

    public void draw(Graphics2D g2) {
        model.draw(g2);
    }

}
