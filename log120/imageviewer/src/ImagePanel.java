
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.border.Border;

public class ImagePanel extends JPanel {

  // The panel displaying the image
  private ImageView view;
  // The controls for moving or zooming the image
  private JPanel panelControls;
  private JButton zoomButton;
  private JButton moveUpButton;
  private JButton moveDownButton;

  public ImagePanel() {
    super();

    this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
    this.setPreferredSize(new Dimension(280, 320));
    this.loadControls();
  }

  private void loadControls() {
    Image img = new ImageIcon("images/test.png").getImage();
    ImageModel model = new ImageModel(img);
    ImageView view = new ImageView(model);
    ImageController controller = new ImageController(model, view);

    zoomButton = new JButton("Zoom");     
    moveUpButton = new JButton("Up");     
    moveDownButton = new JButton("Down"); 

    panelControls = new JPanel();
    //panelControls.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
    panelControls.setPreferredSize(new Dimension(260, 40));
    view.setPreferredSize(new Dimension(260, 260));

    this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    this.add(panelControls);
    this.add(view);
    panelControls.add(zoomButton);
    panelControls.add(moveUpButton);
    panelControls.add(moveDownButton);
    panelControls.validate();
  }

  private class ZoomListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      view.zoom(2);
    }
  }

  private class MoveUpListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      view.translate(0, 1);
    }
  }

  private class MoveDownListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      view.translate(0, -1);
    }
  }

}

