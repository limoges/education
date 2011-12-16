
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

public class ThumbnailView extends JPanel implements Observer {

  private Perspective model;

  public ThumbnailView(Perspective model) {
    this.model = model;
    
    Dimension dimension = new Dimension(PV_WIDTH, PV_HEIGHT);
    setBorder(BorderFactory.createLineBorder(Color.BLACK));

    setSize(dimension);
    setMinimumSize(dimension);
    setPreferredSize(dimension);

    model.addObserver(this);
  }

  public void update(Observable o, Object arg) {
    repaint();
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Image img = model.getImage();
    img = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_FAST);
    g.drawImage(model.getImage(), 0, 0, getWidth(), getHeight(), null);
  }

  private static int PV_WIDTH = 184;
  private static int PV_HEIGHT = 115;

}

