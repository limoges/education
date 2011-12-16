
import java.util.ArrayList;
import java.util.ListIterator;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseMotionListener;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JSlider;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.Point;

public class PerspectiveController implements MouseListener, MouseWheelListener,
       MouseMotionListener {

  private Perspective model;
  private PerspectiveView view;
  private CommandHistory history;

  private Point start;
  private Point current;

  public PerspectiveController(Perspective model, PerspectiveView view,
      CommandHistory history) {
    this.model = model;
    this.view = view;
    this.view.addMouseListener(this);
    this.view.addMouseMotionListener(this);
    this.view.addMouseWheelListener(this);
    this.start = new Point(0, 0);
    this.current = new Point(0, 0);
    this.history = history;
  }

  public void mouseClicked(MouseEvent e) {
    e.consume();
    return;
  }

  public void mouseDragged(MouseEvent e) {
    e.consume();
    return;
  }

  public void mouseEntered(MouseEvent e) {
    e.consume();
    return;
  }

  public void mouseExited(MouseEvent e) {
    e.consume();
    return;
  }

  public void mouseMoved(MouseEvent e) {
    e.consume();
    return;
  }

  public void mousePressed(MouseEvent e) {
    this.start = e.getPoint();
    this.current = start;
  }

  public void mouseReleased(MouseEvent e) {
    this.current = e.getPoint();
    translate(current.x - start.x, current.y - start.y);
  }

  public void mouseWheelMoved(MouseWheelEvent e) {
    int notches = e.getWheelRotation();
    if (notches > 0)
      zoom(model.getZoom() + Perspective.ZOOM_STEP);
    else
      zoom(model.getZoom() - Perspective.ZOOM_STEP);
  }

  private void zoom(int zoom) {
    ZoomCommand cmd = new ZoomCommand(model, zoom);
    history.execute(cmd);
  }

  private void translate(int dx, int dy) {
    TranslateCommand cmd = new TranslateCommand(model, dx, dy);
    history.execute(cmd);
  }

}

