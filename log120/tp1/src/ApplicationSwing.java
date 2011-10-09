/*
 Cours :             LOGnnn
 Session :           Saison (été, automne, hiver) 200X
 Groupe :            n
 Projet :            Laboratoire #n
 Étudiant(e)(s) :    Marcel Marceau
                     Edith Piaf
 Code(s) perm. :     MARM987341987
                     PIAE324398724
 Professeur :        Groucho Marx
 Date créée :        2002-05-28
 Date dern. modif. : 2005-05-01
 
*******************************************************
 Historique des modifications
*******************************************************
  2002-05-28	Cris Fuhrman : Version initiale
  
  2004-03-07	Cris Fuhrman : Intégration de SwingWorker 
                requierant la classe additionnelle 
                SwingWorker.java, utilisation des variables 
                constantes, formatage de code source, 
                organisation des imports, etc.

  2005-05-01	Cris Fuhrman : Intégration de ApplicationSupport
  				requierant la classe additionnelle
  				ApplicationSupport.java et les fichiers
  				prefs.properties, app_xx.properties (où xx est le
  				code de la langue, p. ex. fr = français, en = anglais).
  				Suppression de l'interface Shape.
  				
  2006-05-03	Sébastien Adam :
  
                Uniformisation et maintenance du code.

                Ajout des classes pour la gestion des
                items de menu. Un écouteur ajouté pour chaque item 
                (DemarrerListener, ArreterListener, QuitterListener, 
                AProposDeListener).  
                
                La classe ApplicationSwing n'implémente plus ActionListener. 
                Elle délègue la gestion des items.
                
                Plus besion d'un "if else if" dans la methode actionPerformed pour 
                exécuter l'action associée à un item. Le code est plus
                simple à comprendre, lire et maintenir.	

 La distribution originale se trouve à 
 https://cours.ele.etsmtl.ca/academique/log120/notesdecours/exemples/lab/lab1/ApplicationSwing.zip
*/

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;

// Exceptions
import java.lang.NumberFormatException;
import java.net.UnknownHostException;
import java.net.ConnectException; 
import java.lang.ArrayIndexOutOfBoundsException;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;


/**
 * <code>ApplicationSwing</code> est un exemple d'une
 * application en Java qui fournit une interface Swing, avec un simple
 * menu et un dessin.
 *
 * <h4>References</h4> 
 * <ul> 
 *
 * <li>C. Fuhrman, &quot;Notes de cours de LOG120,&quot; &Eacute;cole
 * de technologie sup&eacute;rieure, Montr&eacute;al, Qu&eacute;bec,
 * Canada, 2002
 *
 * <li>Xemacs (for generation of the initial template), <a target="_top" 
 * href="http://www.xemacs.org">www.xemacs.org</a>, 2002 
 *
 * <li><a target="_top" 
 * href="http://java.sun.com/docs/books/tutorial/uiswing/painting/overview.html">Overview
 * of Custom Painting</a>, une partie du tutoriel Java de Sun, 2002.
 *
 * <li>Java Software, <a target="_top" 
 * href="http://java.sun.com/j2se/javadoc/writingdoccomments/index.html">&quot;How
 * to Write Doc Comments for the Javadoc<sup>TM</sup> Tool,&quot;</a>
 * 2002
 *
 * </ul>
 *
 * Distribution originale &agrave; partir du 
 * <a target="_top" href="https://cours.ele.etsmtl.ca/academique/log120/">site Web</a>
 * du cours LOG120.
 * 
 * Created: Tue May 28 11:31:18 2002
 *
 * @author <a href="mailto:christopher.fuhrman@etsmtl.ca">Christopher Fuhrman</a>
 *
 * @version 1.1
 */

public class ApplicationSwing extends JFrame {
	
  private Ellipse2D.Double forme;
	private boolean workerActif;
	private JMenuItem arreterMenuItem, demarrerMenuItem;
  private ShapeCanvas canvas;
  private ClientShape client;
  private JFrame pointer;
	
	/* Traiter l'item "About...". */
	class AProposDeListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			JOptionPane.showMessageDialog(null, ApplicationSupport
					.getResource(MESSAGE_DIALOGUE_A_PROPOS), ApplicationSupport
					.getResource(MENU_AIDE_PROPOS),
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/* Traiter l'item "Stop". */
	class ArreterListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			workerActif = false;
			rafraichirMenus();
		}
	}
	
class DemarrerListener implements ActionListener {
  
  public void actionPerformed(ActionEvent arg0) {
    boolean validServer = false;
    client = ClientShape.getInstance();

    while (!validServer) {
      try {
         // ask user for address
        String address = JOptionPane.showInputDialog("Quel est nom d'hôte et le port du serveur de formes?", "localhost:10000");
  
        if (address == null) {
          // don't like memory leaks even with a gc
          client = null;
          return;
        }
  
        String[] infos = address.split(":");
        String hostname = infos[0];
        int port = Integer.parseInt(infos[1]);
        client.init(hostname, port);
        validServer = true;
       }
       catch (ArrayIndexOutOfBoundsException aiooe) {
         JOptionPane.showMessageDialog(pointer, "L'adresse entrée est invalide. (hostname:port)");
       }
       catch (NumberFormatException nfe) {
         // parsing int error
         JOptionPane.showMessageDialog(pointer, "Le port spécifié est invalide.");
       }
       catch (UnknownHostException uhe) {
         // dns error
         JOptionPane.showMessageDialog(pointer, "Le nom d'hôte spécifié est introuvable.");
       }
       catch (ConnectException ce) {
         // server not present on host
         JOptionPane.showMessageDialog(pointer, "L'hôte ne réponds pas sur le port spécifié.");
       }
       catch (IOException ioe) {
         ioe.printStackTrace();
       }
       catch (Exception e) {
         JOptionPane.showMessageDialog(pointer, "Erreur générique (" + e.getMessage() + ").");
      }
    }

    client.setCanvas(canvas);
    workerActif = false; 
 
    // This worker listens to the socket input for incoming commands
    final SwingWorker workerSender = new SwingWorker() {
      public Object construct() {
        client.receive();
        workerActif = false;
        System.out.println("receive call");
        return new Integer(0);
      }
    };

    // This worker sends GET requests on a timer
    final SwingWorker workerReceiver = new SwingWorker() {
      public Object construct() {
        client.send();
        workerActif = false;
        System.out.println("send call");
        return new Integer(0);
      }
    };
    // Ensure we don't put the class in an invalid state even for a millisecond
 	  workerActif = true;
    // We start the receiver first because it must be ready to receive something before
    // we any request is sent!
    workerReceiver.start();
    workerSender.start();

 	  rafraichirMenus();
  }
}
	
	/* Traiter l'item "Exit". */
	class QuitterListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (workerActif) {
				workerActif = false;
				try {
					Thread.sleep(DELAI_QUITTER_MSEC);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.exit(0);
		}
	}

	public ApplicationSwing() {
    canvas = new ShapeCanvas();
    canvas.setPreferredSize(new Dimension(CANEVAS_LARGEUR, CANEVAS_HAUTEUR));
    getContentPane().add(new JScrollPane(canvas));
    pointer = this;
    init();
	}

  private void init() {
		creerMenuFichier();
		creerMenuDessiner();
		creerMenuAide();
		rafraichirMenus();
		setLocationRelativeTo(null);
  }

	/* Créer le menu "Draw". */
	private JMenu creerMenuDessiner() {
		JMenu menu = ApplicationSupport.addMenu(this, MENU_DESSIN_TITRE,
				new String[] { MENU_DESSIN_DEMARRER, MENU_DESSIN_ARRETER });

		demarrerMenuItem = menu.getItem(0);
		demarrerMenuItem.addActionListener(new DemarrerListener());
		demarrerMenuItem.setAccelerator(KeyStroke.getKeyStroke(
				MENU_DESSIN_DEMARRER_TOUCHE_RACC,
				MENU_DESSIN_DEMARRER_TOUCHE_MASK));

		arreterMenuItem = menu.getItem(1);
		arreterMenuItem.addActionListener(new ArreterListener());
		arreterMenuItem.setAccelerator(KeyStroke.getKeyStroke(
				MENU_DESSIN_ARRETER_TOUCHE_RACC,
				MENU_DESSIN_ARRETER_TOUCHE_MASK));

		return menu;
	}

	/* Créer le menu "File". */
	private JMenu creerMenuFichier() {
		JMenu menu = ApplicationSupport.addMenu(this, MENU_FICHIER_TITRE,
				new String[] { MENU_FICHIER_QUITTER });

		menu.getItem(0).addActionListener(new QuitterListener());
		menu.getItem(0).setAccelerator(
				KeyStroke.getKeyStroke(MENU_FICHIER_QUITTER_TOUCHE_RACC,
						MENU_FICHIER_QUITTER_TOUCHE_MASK));

		return menu;
	}

	/* Créer le menu "Help". */
	private JMenu creerMenuAide() {
		JMenu menu = ApplicationSupport.addMenu(this, MENU_AIDE_TITRE,
				new String[] { MENU_AIDE_PROPOS });

		menu.getItem(0).addActionListener(new AProposDeListener());

		return menu;
	}

	private void rafraichirMenus() {
		demarrerMenuItem.setEnabled(!workerActif);
		arreterMenuItem.setEnabled(workerActif);
	}
	
  /* Constants */
  private static final int CANEVAS_HAUTEUR = 500;                                     
  private static final int CANEVAS_LARGEUR = 500;                                     
  private static final int DELAI_ENTRE_FORMES_MSEC = 1000;                            
  private static final int DELAI_QUITTER_MSEC = 200;                                  
  private static final int FORME_MAX_HAUTEUR = 200;                                   
  private static final int FORME_MAX_LARGEUR = 200;                                   
  private static final int MARGE_H = 50;                                              
  private static final int MARGE_V = 60;                                              
  private static final int MENU_DESSIN_ARRETER_TOUCHE_MASK = ActionEvent.CTRL_MASK;   
  private static final char MENU_DESSIN_ARRETER_TOUCHE_RACC = KeyEvent.VK_A;          
  private static final int MENU_DESSIN_DEMARRER_TOUCHE_MASK = ActionEvent.CTRL_MASK;  
  private static final char MENU_DESSIN_DEMARRER_TOUCHE_RACC = KeyEvent.VK_D;         
  private static final int MENU_FICHIER_QUITTER_TOUCHE_MASK = ActionEvent.CTRL_MASK;  
  private static final char MENU_FICHIER_QUITTER_TOUCHE_RACC = KeyEvent.VK_Q;         
  private static final String                                                         
  		MENU_FICHIER_TITRE = "app.frame.menus.file.title",                              
  		MENU_FICHIER_QUITTER = "app.frame.menus.file.exit",                             
  		MENU_DESSIN_TITRE = "app.frame.menus.draw.title",                               
  		MENU_DESSIN_DEMARRER = "app.frame.menus.draw.start",                            
  		MENU_DESSIN_ARRETER = "app.frame.menus.draw.stop",                              
  		MENU_AIDE_TITRE = "app.frame.menus.help.title",                                 
  		MENU_AIDE_PROPOS = "app.frame.menus.help.about";                                
  private static final String MESSAGE_DIALOGUE_A_PROPOS = "app.frame.dialog.about";   
  private static final int NOMBRE_DE_FORMES = 150;                                    
  private static final long serialVersionUID = 1L;                                    
 
	public static void main(String[] args) {
		ApplicationSwing frame = new ApplicationSwing();

		ApplicationSupport.launch(frame, ApplicationSupport
				.getResource("app.frame.title"), 0, 0, CANEVAS_LARGEUR
				+ MARGE_H, CANEVAS_HAUTEUR + MARGE_V);
	}
}
