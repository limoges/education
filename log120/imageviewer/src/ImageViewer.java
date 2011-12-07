
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ImageViewer extends JFrame {
	
  private JFrame framePointer;

  public ImageViewer() {
    super();
    framePointer = this;

    this.setSize(new Dimension(IV_WIDTH, IV_HEIGHT));
    this.setTitle(IV_NAME);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    this.setJMenuBar(loadMenuBar());
    this.setContentPane(loadContentPane());

    this.validate();
    this.setVisible(true);
	}

  private JPanel loadContentPane() {
    JPanel contentPane = new EditorPane();

    return contentPane;
  }

  private JMenuBar loadMenuBar() {
    JMenu fileMenu = new JMenu("File");
    JMenu editMenu = new JMenu("Edit");

    // Exit
    JMenuItem exitAction = new JMenuItem("Exit");
    exitAction.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("Exit");
      }
    });
    fileMenu.add(exitAction);
    // Debug
    JMenuItem debugAction = new JMenuItem("Debug");
    debugAction.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("Debug");
      }
    });
    fileMenu.add(debugAction);
    // New image
    JMenuItem newAction = new JMenuItem("New image");
    newAction.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("New image");
      }
    });
    fileMenu.add(newAction);
    // Undo
    JMenuItem undoAction = new JMenuItem("Undo");
    undoAction.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("Undo");
      }
    });
    editMenu.add(undoAction);
    // Redo
    JMenuItem redoAction = new JMenuItem("Redo");
    redoAction.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("Redo");
      }
    });
    editMenu.add(redoAction);

    // Populate a menubar
    JMenuBar bar = new JMenuBar();
    bar.add(fileMenu);
    bar.add(editMenu);

    return bar;
  }

  private static int    IV_WIDTH = 800;          
  private static int    IV_HEIGHT = 600;         
  private static String IV_NAME = "ImageViewer"; 

	public static void main(String[] args) {
		ImageViewer frame = new ImageViewer();
	}

}

