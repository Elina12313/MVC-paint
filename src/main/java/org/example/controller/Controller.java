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
import org.example.view.menu.MenuCreator;
import org.example.controller.state.UndoMachine;


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
    private UndoMachine undoMachine1;

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

        undoMachine1 = new UndoMachine();

        MenuCreator menuCreator = MenuCreator.getInstance();
        menuCreator.setState(menuState);
        menuCreator.setUndoMachine(undoMachine1);
        menuCreator.setModel(model);
        frame.setJMenuBar(menuCreator.createMenuBar());
        frame.add(menuCreator.createToolBar(), BorderLayout.NORTH);

    }

    public void getPointOne(Point p){
        AppAction actionDraw1 = menuState.getAction();
        actionDraw1.mousePressed(p);
        undoMachine1.add(actionDraw1.cloneAction());
        undoMachine1.updateButtons();
    }
    public void getPointTwo(Point p){
        AppAction action = menuState.getAction();
        action.mouseDragged(p);

    }

    public void draw(Graphics2D g2) {
        model.draw(g2);
    }

}
