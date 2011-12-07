
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.Point;

public class PerspectiveController implements MouseListener, MouseWheelListener, MouseMotionListener {

  private Perspective model;
  private PerspectiveView view;
  // Missing command history

  private Point start;
  private Point current;

  public PerspectiveController(Perspective model, PerspectiveView view) {
    this.model = model;
    this.view = view;
    this.view.addMouseListener(this);
    this.view.addMouseMotionListener(this);
    this.view.addMouseWheelListener(this);
    this.start = new Point(0, 0);
    this.current = new Point(0, 0);
  }

  public void mouseClicked(MouseEvent e) {
    //System.out.println("mouseClicked");
    e.consume();
    return;
  }

  public void mouseDragged(MouseEvent e) {
    //System.out.println("mouseDragged");
    //this.current = e.getPoint();
    //this.model.translate(current.x - start.x, current.y - start.y);
    return;
  }

  public void mouseEntered(MouseEvent e) {
    //System.out.println("mouseEntered");
    e.consume();
    return;
  }

  public void mouseExited(MouseEvent e) {
    //System.out.println("mouseExited");
    e.consume();
    return;
  }

  public void mouseMoved(MouseEvent e) {
    //System.out.println("mouseMoved");
    e.consume();
    return;
  }

  public void mousePressed(MouseEvent e) {
    //System.out.println("mousePressed");
    this.start = e.getPoint();
    this.current = start;
  }

  public void mouseReleased(MouseEvent e) {
    //System.out.println("mouseReleased");
    this.current = e.getPoint();
    this.model.translate(current.x - start.x, current.y - start.y);
  }

  public void mouseWheelMoved(MouseWheelEvent e) {
    //System.out.println("mouseWheelMoved");
    int notches = e.getWheelRotation();
    System.out.println(notches);
    if (notches > 0)
      model.zoomIn();
    else
      model.zoomOut();
    //model.setZoom(model.getZoom() + notches);
  }

}

