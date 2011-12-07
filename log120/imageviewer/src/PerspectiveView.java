
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

public class PerspectiveView extends /*JLabel*/ JPanel implements Observer {

  private Perspective model;

  public PerspectiveView(Perspective model) {
    this.model = model;
    Image img = model.getImage();
    Dimension dimension = new Dimension(img.getWidth(model), img.getHeight(model));
    //setIcon(new ImageIcon(img));
    setBorder(BorderFactory.createLineBorder(Color.BLACK));
    setSize(dimension);
    setMinimumSize(dimension);
    setPreferredSize(dimension);
    model.addObserver(this);
  }

  public void update(Observable o, Object arg) {
    //System.out.println("Update");
    repaint();
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Image image = model.getImage();
    Point coord = model.getCoordinates();
    float factor = model.getZoom() / Perspective.BASE_FACTOR;
    float height = (float) image.getHeight(model) * factor;
    float width = (float) image.getWidth(model) * factor;
    //System.out.println("x:" + coord.x + " y:" + coord.y + " width:" + width + " height:" + height);
    g.drawImage(image, coord.x, coord.y, (int) width, (int) height, null);
  }

  private static int PV_WIDTH = 800;
  private static int PV_HEIGHT = 600;

}

