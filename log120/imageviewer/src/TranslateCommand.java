
import java.awt.Point;

public class TranslateCommand implements Command {

  Perspective target;
  int dx;
  int dy;

  public TranslateCommand(Perspective target, int dx, int dy) {
    this.target = target;
    this.dx = dx;
    this.dy = dy;
  }

  public void execute() {
    target.translate(dx, dy);
  }

  public void undo() {
    target.translate(-dx, -dy);
  }

}

