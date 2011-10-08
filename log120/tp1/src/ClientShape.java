import java.util.Scanner;
import java.io.*;
import java.net.*;

class ClientShape implements Runnable {
  private Socket socket;

  ClientShape() {
  }

  public void init(String hostname, int port) throws UnknownHostException, ConnectException, IOException {
    this.socket = new Socket(hostname, port);
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
    while (true) {
      try {
        out.println(command);
        Thread.sleep(1000);
        line = (in.readLine()).trim();
        if (line.compareToIgnoreCase("commande>") == 0)
        {
          line = null;
          continue;
        }
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

}
