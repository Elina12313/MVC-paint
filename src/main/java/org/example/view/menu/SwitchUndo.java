package org.example.view.menu;

import lombok.AllArgsConstructor;
import org.example.controller.state.UndoMachine;
@AllArgsConstructor
public class SwitchUndo {
    private UndoMachine undoMachine;

    public void execute() {
        undoMachine.executeRedo();
        undoMachine.updateButtons();
    }
}
