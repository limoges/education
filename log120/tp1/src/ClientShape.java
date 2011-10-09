import java.util.Scanner;
import java.io.*;
import java.net.*;

class ClientShape/* implements Runnable*/ {
  private static ClientShape instance = null;
  private Socket socket;
  private boolean sendRunning, receiveRunning;;
  private int sleep;
  private ShapeCanvas canvas;

  protected ClientShape() {
    sendRunning = false;
    receiveRunning = false;
  }

  public static ClientShape getInstance() {
    if (instance == null) {
      instance = new ClientShape();
    }
    return instance;
  }

  public void init(String hostname, int port) throws UnknownHostException, ConnectException, IOException {
    this.socket = new Socket(hostname, port);
  }

  public void setCanvas(ShapeCanvas canvas) {
    this.canvas = canvas;
  }

  public void send() {
    String command = "GET";
    PrintWriter out = null;

    try {
      out = new PrintWriter(socket.getOutputStream(), true);
    }
    catch (IOException ioe) {
      System.out.println("out failed");
    }

    sendRunning = true;
    while (sendRunning) {
      out.println(command);
      try {
        Thread.sleep(500);
      }
      catch (InterruptedException ie) {
        ie.printStackTrace();
      }
    }

  }

  public void receive() {
    String command = "";
    BufferedReader in = null;

    try {
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    catch (IOException e) {
      System.out.println("in failed");
    }

    receiveRunning = true;
    while (receiveRunning) {
      try {
        command = (in.readLine()).trim();

        if (command.equals("commande>")) {
          command = null;
          continue;
        }
        executeCommand(command);
        // Not elegant but a way to let other thread execute?
        // TODO find a way to listen to the socket and only execute when
        // new data arrive through the socket
        Thread.sleep(1);
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  private void executeCommand(String command) {
    Shape s = ShapeFactory.create(command);
    
    if (s == null)
      return;

    canvas.addShape(s);
  }

  /*public void run() {
    String line;
    BufferedReader in = null;
    PrintWriter out = null;
    try {
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new PrintWriter(socket.getOutputStream(), true);
    } catch (IOException e) {
      System.out.println("in or out failed");
    }
    
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
  }*/

  public void stop() {
    sendRunning = false;
    receiveRunning = false;
  }

}
