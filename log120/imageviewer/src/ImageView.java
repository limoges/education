
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;

import java.util.Observer;
import java.util.Observable;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Dimension;

public class ImageView extends JLabel /*implements ImageObserver, Observer*/ {

  private ImageModel model;

  public ImageView(ImageModel model) {
    this.model = model;

    Image img = this.model.getImage();
    this.setIcon(new ImageIcon(img));
    //this.setPreferredSize(new Dimension(100, 100));
    this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
  }

  public void update(Observable o, Object arg) {
    // Update from the image
  }

  public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
    return (infoflags & ALLBITS) != 0;
  }

}

