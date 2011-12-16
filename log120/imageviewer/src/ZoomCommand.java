
public class ZoomCommand implements Command {

  Perspective target;
  int before;
  int after;

  public ZoomCommand(Perspective target, int zoom) {
    this.target = target;
    this.before = target.getZoom();
    this.after = zoom;
  }

  public void execute() {
    target.setZoom(after);
  }

  public void undo() {
    target.setZoom(before);
  }

}

