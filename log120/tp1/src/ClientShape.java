import java.util.Scanner;
import java.io.*;
import java.net.*;

class ClientShape/* implements Runnable*/ {
  private static ClientShape instance = null;
  private Socket socket;
  private boolean sendRunning, receiveRunning;;
  private int sleep;
  private ShapeCanvas canvas;
  private boolean done;
  private PrintWriter out;
  private BufferedReader in;

  protected ClientShape() {
    sendRunning = false;
    receiveRunning = false;
  }

  private void finish() {
    try {                  
      if (done)              
        socket.close();      
      else                   
        done = true;         
    }                      
    catch (Exception e) {  
      System.out.println("exception while in finish()");
      e.printStackTrace(); 
    }                      
  }

  public static ClientShape getInstance() {
    if (instance == null) {
      instance = new ClientShape();
    }
    return instance;
  }

  public void init(String hostname, int port) throws UnknownHostException, ConnectException, IOException {
    this.socket = new Socket(hostname, port);
    done = false;
  }

  public void setCanvas(ShapeCanvas canvas) {
    this.canvas = canvas;
  }

  public void send() {
    String command = "GET";
    out = null;

    try {
      out = new PrintWriter(socket.getOutputStream(), true);
    }
    catch (IOException ioe) {
      ioe.printStackTrace();
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

    // We end the conversation with the server
    out.close();
    finish();
  }

  public void receive() throws Exception {
    String command = "";
    in = null;

    try {
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    catch (IOException ioe) {
      ioe.printStackTrace();
    }

    receiveRunning = true;
    while (receiveRunning) { 
      if (!socket.isConnected() || socket.isInputShutdown()) {
        throw new Exception("Server has disconnected.");
      }
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
      catch (NullPointerException npe) {
        npe.printStackTrace();
        receiveRunning = false;
        finish();
        throw new Exception("Server has disconnected.");
      }
      catch (Exception e) {
        System.out.println("exception while in receiveRunning");
        e.printStackTrace();
        receiveRunning = false;
      }
    }

    try {
      in.close();
    }
    catch (Exception e) {
      System.out.println("exception while closing");
      e.printStackTrace();
    }

    finish();
  }

  private void executeCommand(String command) {
    Shape s = ShapeFactory.create(command);
    
    if (s == null)
      return;

    canvas.addShape(s);
  }

  public void stop() {
    out.println("END");
    sendRunning = false;
    receiveRunning = false;
  }

}
