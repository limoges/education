
import java.util.Observer;
import java.util.Observable;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSlider;

public class PerspectiveView extends JPanel implements Observer {

  private Perspective model;

  public PerspectiveView(Image image, CommandHistory history)  {
    this.model = new Perspective(image);

    setBackground(Color.BLACK);
    Image img = model.getImage();
    Dimension dimension = new Dimension(img.getWidth(this), img.getHeight(this));

    setBorder(BorderFactory.createLineBorder(Color.BLACK));
    setPreferredSize(
        new Dimension(dimension.width * 2, dimension.height * 2)
    );
    setMinimumSize(dimension);
    PerspectiveController controller=new PerspectiveController(model, this, history);
    model.addObserver(this);
  }

  public Perspective getPerspective() {
    return model;
  }

  public void update(Observable o, Object arg) {
    repaint();
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Image image = model.getImage();
    Point coord = model.getCoordinates();
    float base = Perspective.BASE_FACTOR;
    float zoom = model.getZoom();
    float factor = zoom / base;
    int height = (int) (image.getHeight(this) * factor);
    int width = (int) (image.getWidth(this) * factor);
    g.drawImage(image, coord.x, coord.y, width, height, null);
  }

  private static int PV_WIDTH = 800;
  private static int PV_HEIGHT = 600;

}

