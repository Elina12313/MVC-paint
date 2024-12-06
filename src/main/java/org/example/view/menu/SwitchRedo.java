package org.example.view.menu;
import org.example.controller.state.UndoMachine;

import lombok.AllArgsConstructor;


public class SwitchRedo implements AppCommand {
    private UndoMachine undoMachine;

    public SwitchRedo(UndoMachine undoMachine) {
        this.undoMachine = undoMachine;
    }

    @Override
    public void execute() {
        undoMachine.executeRedo();
        undoMachine.updateButtons();
    }
}

