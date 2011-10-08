import java.util.Scanner;
import java.io.*;
import java.net.*;

class ClientShape implements Runnable {
  private Socket socket;
  private boolean running;
  private int sleep;
  private ShapeCanvas canvas;

  ClientShape() {
    running = false;
  }

  public void init(String hostname, int port) throws UnknownHostException, ConnectException, IOException {
    this.socket = new Socket(hostname, port);
  }

  public void setCanvas(ShapeCanvas canvas) {
    this.canvas = canvas;
  }

  public void run() {
    String line;
    BufferedReader in = null;
    PrintWriter out = null;
    try {
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new PrintWriter(socket.getOutputStream(), true);
    } catch (IOException e) {
      System.out.println("in or out failed");
    }
    
    Scanner input = new Scanner(System.in); 
    String command = "GET";
    running = true;
    while (running) {
      try {
        out.println(command);
        Thread.sleep(100);
        line = (in.readLine()).trim();
        if (line.compareToIgnoreCase("commande>") == 0)
        {
          line = null;
          continue;
        }
        Shape s = ShapeFactory.create(line);
        if (s != null)
          canvas.addShape(s);
        System.out.println(line);
      }
      catch (InterruptedException e) {
        try {
          socket.close();
          System.out.println("Interrupted. Socket closed successfully.");
        }
        catch (IOException ioe) {
          System.out.println("Blocked thread I/O failed due to socket closing");
        }
      }
      catch (IOException e) {
        System.out.println("Read failed");
      }
    }
  }

  public void stop() {
    running = false;
  }

}
