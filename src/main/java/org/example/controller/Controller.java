package org.example.controller;

import org.example.controller.action.ActionDraw;
import org.example.controller.factory.MenuState;
import org.example.controller.factory.MyShapeFactory;
import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.shape.fill.NoFill;
import org.example.view.MyFrame;
import org.example.view.MyPanel;

import java.awt.*;
import java.awt.geom.Ellipse2D;
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
//        menuState = new MenuState();
//        MyShapeFactory shapeCreationFactory = MyShapeFactory.getInstance();
//        shapeCreationFactory.config(menuState);
//
//          model = new Model();
////        menuState.setActionDraw(new ActionDraw(model, shapeCreationFactory));
//
//        MyShape simpleShape = new MyShape(new Rectangle2D.Double());
//        simpleShape.setFb(new NoFill());
//        actionDraw = new ActionDraw(model, simpleShape);
//        model.setMyShape(simpleShape);
//        model.addObserver(panel);
//
//        panel = new MyPanel(this, actionDraw);
//
//
//        frame = new MyFrame();
//        frame.setPanel(panel);
//
//        MenuController menuController = MenuController.getInstance();
//        menuController.setActionDraw(actionDraw);
//        menuController.setState(menuState);
//        frame.setJMenuBar(menuController.createMenuBar());
        menuState = new MenuState();
        MyShapeFactory shapeCreator = MyShapeFactory.getInstance();
        shapeCreator.config(menuState);

        model = new Model();

        MyShape shape = new MyShape(new Rectangle2D.Double()); // новое
        shape.setFb(new NoFill()); //

        menuState.setActionDraw(new ActionDraw(model, shape));

        panel = new MyPanel(this , actionDraw);

        model.setMyShape(simpleShape);
        model.addObserver(panel);

        frame = new MyFrame();
        frame.setPanel(panel);

        MenuController menuController = MenuController.getInstance();
        menuController.setActionDraw(actionDraw);
        menuController.setState(menuState);
        frame.setJMenuBar(menuController.createMenuBar());
        frame.revalidate();


    }

    public void getPointOne(Point2D p){
        ActionDraw actionDraw1 = menuState.getActionDraw();
        actionDraw1.mousePressed(p);
    }
    public void getPointTwo(Point2D p){
        ActionDraw actionDraw1 = menuState.getActionDraw();
        actionDraw1.mouseDragged(p);
    }

    public void draw(Graphics2D g2) {
        model.draw(g2);
    }

}
