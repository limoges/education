
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

public class PerspectiveView extends /*JLabel*/ JPanel implements Observer {

  private Perspective model;
  private JSlider slider;

  public PerspectiveView(Perspective model) {
    this.model = model;
    setBackground(Color.BLACK);
    Image img = model.getImage();
    Dimension dimension = new Dimension(img.getWidth(this), img.getHeight(this));
    //setIcon(new ImageIcon(img));
    slider = new JSlider((int) Perspective.ZOOM_MIN, (int) Perspective.ZOOM_MAX,
        (int) Perspective.BASE_FACTOR);
    add(slider);
    setBorder(BorderFactory.createLineBorder(Color.BLACK));
    setSize(dimension);
    setMinimumSize(dimension);
    setPreferredSize(dimension);
    model.addObserver(this);
  }

  public void update(Observable o, Object arg) {
    //System.out.println("Update");
    slider.setValue((int)model.getZoom());
    repaint();
  }

  public JSlider getSlider() {
    return slider;
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Image image = model.getImage();
    Point coord = model.getCoordinates();
    float factor = model.getZoom() / Perspective.BASE_FACTOR;
    float height = (float) image.getHeight(this) * factor;
    float width = (float) image.getWidth(this) * factor;
    //System.out.println("x:" + coord.x + " y:" + coord.y + " width:" + width + " height:" + height);
    g.drawImage(image, coord.x, coord.y, (int) width, (int) height, null);
  }

  private static int PV_WIDTH = 800;
  private static int PV_HEIGHT = 600;

}

