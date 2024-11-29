package org.example.controller.state;

import org.example.controller.action.AppAction;
import org.example.view.menu.CommandActionListener;

import java.util.LinkedList;

public class UndoMachine {
    private UndoRedoState undoRedoState;
    CommandActionListener redo;
    CommandActionListener undo;

    public UndoMachine() {
        LinkedList<AppAction> undoList = new LinkedList<>();
        LinkedList<AppAction> redoList = new LinkedList<>();
        undoRedoState = new StateDisableUndoDisableRedo(undoList, redoList);
    }

    public void executeRedo() {
        undoRedoState = undoRedoState.redo();
    }

    public void executeUndo() {
        undoRedoState = undoRedoState.undo();
    }

    public boolean isEnableUndo() {
        return undoRedoState.getUndoActivityList().size() > 0;
    }


    public boolean isEnableRedo() {

        return undoRedoState.getRedoActivityList().size() > 0;
    }

    public void add(AppAction action) {
        undoRedoState.clearHistory();
        undoRedoState.addAction(action);
        undoRedoState = new StateDisableUndoDisableRedo(undoRedoState.getUndoActivityList(),undoRedoState.getRedoActivityList());
        //TODO: Определить переход по состоянию
        //undoRedoState = ;
    }

    public void updateButtons() {
        undo.setEnabled(isEnableUndo());
        redo.setEnabled(isEnableRedo());

    }
}
