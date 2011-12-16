
import java.util.ArrayList;
import java.util.ListIterator;

public class CommandHistory {

  private ArrayList<Command> list;
  private ListIterator<Command> commands;

  public CommandHistory() {
    list = new ArrayList<Command>();
    commands = list.listIterator();
  }

  public void execute(Command cmd) {
    // If we have undone changes replace these with the change
    if (commands.hasNext()) {
      commands.next();
      commands.set(cmd);
    }
    else {
      commands.add(cmd);
    }
    cmd.execute();
  }

  public void redo() {
    if (commands.hasNext()) {
      Command cmd = commands.next();
      cmd.execute();
    }
  }

  public void undo() {
    if (commands.hasPrevious()) {
      Command cmd = commands.previous();
      cmd.undo();
    }
  }

  public boolean canUndo() {
    return commands.hasPrevious();
  }

  public boolean canRedo() {
    return commands.hasNext();
  }

}

