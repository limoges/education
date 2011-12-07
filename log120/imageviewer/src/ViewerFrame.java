
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Image;

public class ViewerFrame extends JFrame {

  private JFrame framePointer;

  public ViewerFrame() {
    super();
    framePointer = this;
    setTitle(VF_NAME);
    Dimension dimension = new Dimension(VF_WIDTH, VF_HEIGHT);
    setSize(dimension);
    setMinimumSize(dimension);
    setJMenuBar(createMenuBar());
    setContentPane(createContentPane());
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
  }

  private JPanel createContentPane() {
    JPanel contentPane = new JPanel();
    contentPane.setOpaque(true);
    return contentPane;
  }

  private JMenuBar createMenuBar() {
    JMenu fileMenu = new JMenu("File");                         
    JMenu editMenu = new JMenu("Edit");                         
                                                                
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

        Image img = new ImageIcon("images/test.png").getImage();
        Perspective model = new Perspective(img);
        PerspectiveView view = new PerspectiveView(model);
        PerspectiveController controller = new PerspectiveController(model, view);
        framePointer.getContentPane().add(view);
        framePointer.validate();
      }                                                         
    });                                                         
    fileMenu.add(newAction);                                    
    // Exit                                                     
    JMenuItem exitAction = new JMenuItem("Exit");               
    exitAction.addActionListener(new ActionListener() {         
      public void actionPerformed(ActionEvent e) {              
        System.out.println("Exit");                             
      }                                                         
    });                                                         
    fileMenu.add(exitAction);                                   
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
                                                                
    JMenuBar menuBar = new JMenuBar();                              
    menuBar.add(fileMenu);                                          
    menuBar.add(editMenu);                                          

    return menuBar;
  }

  private String VF_NAME = "Viewer";
  private int VF_WIDTH = 800;
  private int VF_HEIGHT = 600;
}

