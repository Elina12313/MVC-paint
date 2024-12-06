package org.example.view.menu;

import lombok.AllArgsConstructor;
import org.example.controller.state.UndoMachine;

public class SwitchUndo implements AppCommand {
    private UndoMachine undoMachine;

    public SwitchUndo(UndoMachine undoMachine) {
        this.undoMachine = undoMachine;
    }

    public void execute() {
        undoMachine.executeUndo();
        undoMachine.updateButtons();
    }
}
