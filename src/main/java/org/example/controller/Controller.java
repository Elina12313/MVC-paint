package org.example.controller;

import org.example.controller.factory.MenuState;
import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.shape.fill.Fill;
import org.example.model.shape.fill.NoFill;
import org.example.view.MyFrame;
import org.example.view.MyPanel;
import org.example.controller.factory.MenuState;

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
    private MenuState menuState;
    private static Controller instance;
    public static Controller getInstance(){
        if (instance == null){
            instance = new Controller();
        }
        return instance;
    }
    private Controller() {
        menuState = new MenuState();
        var shapeCreationFactory = ShapeCreationFactory.getInstance();
        shapeCreationFactory.config(menuState);

       model = new Model();
       MyShape simpleShape = new MyShape(new Rectangle2D.Double());
       simpleShape.setFb(new NoFill());
        actionDraw = new ActionDraw(model, simpleShape);
        model.setMyShape(simpleShape);
        panel = new MyPanel(this, actionDraw);

        model.addObserver(panel);

        frame = new MyFrame();
        frame.setPanel(panel);

        MenuController menuController = MenuController.getInstance();
        menuController.setActionDraw(actionDraw);
        frame.setJMenuBar(menuController.createMenuBar());
        frame.revalidate();

    }

    public void getPointOne(Point2D p){
     AppAction action = menuState.getAction();
     action.mousePressed((Point)p);
    }
    public void getPointTwo(Point2D p){
        AppAction action = menuState.getAction();
        action.mouseDragged((Point)p);
    }

    public void draw(Graphics2D g2) {
        model.draw(g2);
    }

}
