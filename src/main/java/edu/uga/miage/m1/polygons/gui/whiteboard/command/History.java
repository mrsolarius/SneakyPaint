package edu.uga.miage.m1.polygons.gui.whiteboard.command;

import java.util.Deque;
import java.util.LinkedList;

public class History {
    private Deque<Command> commands;
    private int currentCommandIndex;

    public History(){
        this.currentCommandIndex = -1;
        this.commands = new LinkedList<>();
    }

    public void addCommand(Command command){
        if(this.currentCommandIndex < this.commands.size() - 1){
            for (int i = this.commands.size() - 1; i >= this.currentCommandIndex; i--){
                this.commands.removeLast();
            }
            this.commands.removeLast();
        }
        this.commands.add(command);
        this.currentCommandIndex++;
    }

    public void undo(){
        if(this.currentCommandIndex >= 0){
            this.commands.removeLast();
            this.currentCommandIndex--;
            this.executeAll();
        }
    }

    public void executeAll(){
        for(Command command : this.commands.stream().toList()){
            command.execute();
        }
    }

}
