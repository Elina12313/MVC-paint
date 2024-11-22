package org.example.controller;

import org.example.controller.action.ActionDraw;
import org.example.controller.action.AppAction;
import org.example.controller.factory.MenuState;
import org.example.controller.factory.MyShapeFactory;
import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.shape.fill.NoFill;
import org.example.view.MyFrame;
import org.example.view.MyPanel;

import java.awt.*;
import java.awt.geom.Point2D;

// TODO: 24.10.2024 Сделать singleton класс
public class Controller {
    private Model model;
    private MyFrame frame;
    private MyPanel panel;
    private Point2D firstPoint;
    private Point2D secondPoint;
    private MyShape simpleShape;

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
        MyShapeFactory shapeCreator = MyShapeFactory.getInstance();
        shapeCreator.config(menuState);

        model = new Model();

        MyShape shape = shapeCreator.createShape();
        shape.setFb(new NoFill()); //


        menuState.setAction(new ActionDraw(model , shape));

        panel = new MyPanel(this);

        model.setMyShape(simpleShape);
        model.addObserver(panel);

        frame = new MyFrame();
        frame.setPanel(panel);

        MenuController menuController = MenuController.getInstance();
//        menuController.setActionDraw(actionDraw);
        menuController.setModel(model);
        menuController.setState(menuState);
        frame.setJMenuBar(menuController.createMenuBar());
        frame.revalidate();

    }

    public void getPointOne(Point p){
        AppAction actionDraw1 = menuState.getAction();
        actionDraw1.mousePressed(p);
    }
    public void getPointTwo(Point p){
        AppAction actionDraw1 = menuState.getAction();
        actionDraw1.mouseDragged(p);
    }

    public void draw(Graphics2D g2) {
        model.draw(g2);
    }

}
