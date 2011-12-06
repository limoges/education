
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ImageViewer extends JFrame {
	
  private static int IV_WIDTH = 580;
  private static int IV_HEIGHT = 660;
  private static String IV_NAME = "ImageViewer";
  private JFrame framePointer;

  public ImageViewer() {
    framePointer = this;
    this.setSize(new Dimension(IV_WIDTH, IV_HEIGHT + 40));
    this.setTitle(IV_NAME);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    this.loadMenuBar();
    this.getContentPane().setLayout(new FlowLayout());
    this.getContentPane().add(new ImagePanel());
    this.getContentPane().add(new ImagePanel());
    this.getContentPane().add(new ImagePanel());
    this.getContentPane().add(new ImagePanel());
    this.getContentPane().setSize(new Dimension(IV_WIDTH, IV_HEIGHT));
    this.validate();
    this.setVisible(true);
	}

  private void loadMenuBar() {
    JMenuBar bar = new JMenuBar();

    JMenu fileMenu = new JMenu("File");
    bar.add(fileMenu);

    JMenuItem exitAction = new JMenuItem("Exit");
    JMenuItem debugAction = new JMenuItem("Debug");
    debugAction.addActionListener(new DebugListener());
    JMenuItem newAction = new JMenuItem("New image");
    newAction.addActionListener(new NewImageListener());
    fileMenu.add(newAction);
    fileMenu.add(debugAction);
    fileMenu.add(exitAction);

    JMenu editMenu = new JMenu("Edit");
    bar.add(editMenu);

    JMenuItem undoAction = new JMenuItem("Undo");
    JMenuItem redoAction = new JMenuItem("Redo");
    editMenu.add(undoAction);
    editMenu.add(redoAction);

    this.setJMenuBar(bar);
  }

  // ActionListener for adding ImageView
  private class NewImageListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {
      framePointer.getContentPane().add(new ImagePanel());
      framePointer.validate();
    }

  }

  private class DebugListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {
      System.out.println("Dimension(" + framePointer.getContentPane().getWidth() + ", "
          + framePointer.getContentPane().getHeight() + ")");
    }

  }

	public static void main(String[] args) {
		ImageViewer frame = new ImageViewer();
	}

}

