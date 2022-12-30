package edu.uga.miage.m1.polygons.gui.whiteboard.commands;

import java.util.LinkedList;
import java.util.List;

public class History {
    private List<Command> commands;
    private int currentCommandIndex;

    public History(){
        this.currentCommandIndex = -1;
        this.commands = new LinkedList<>();
    }

    public void addCommand(Command command){
        if(this.currentCommandIndex < this.commands.size() - 1){
            this.commands = this.commands.subList(0, this.currentCommandIndex + 1);
        }
        this.commands.add(command);
        this.currentCommandIndex++;
    }

    public void undo(){
        if(this.currentCommandIndex >= 0){
            this.commands.get(this.currentCommandIndex).undo();
            this.currentCommandIndex--;
        }
    }

    public void redo() {
        if(this.currentCommandIndex < this.commands.size() - 1){
            this.currentCommandIndex++;
            this.commands.get(this.currentCommandIndex).redo();
        }
    }

    public void clear() {
        this.commands.clear();
        this.currentCommandIndex = -1;
    }
}
