/**
 * File used to recieve data from the Frontend (application) to the Backend and send data from the
 * Backend the Frontend.
 */

package FinalProject;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class DataReceiver {

  private ServerSocket ss;
  private Socket s;
  private ObjectInputStream in;
  private ObjectOutputStream out;
  
  public boolean isConnected = false;

  DataReceiver(int port) {
    try {
      ss = new ServerSocket(port);
    } catch (Exception e) {
      System.out.println("Could not connect to server");
      e.printStackTrace();
      return;
    }
  }

  public boolean makeConnection() {
    try {
      s = ss.accept();
    } catch (Exception e) {
      System.out.println("Could not accept to socket");
      e.printStackTrace();
      return false;
    }
    isConnected = makeInputStream() && makeOutputStream();
    return isConnected;
  }

  private boolean makeInputStream() {
    try {
      in = new ObjectInputStream(s.getInputStream());
    } catch (Exception e) {
      System.out.println("Could not make Input Stream from Socket");
      e.printStackTrace();
      try {
        s.close();
      } catch (Exception ee) {
        System.out.println("Could not close Socket correctly");
        ee.printStackTrace();
      }
      return false;
    }
    return true;
  }

  private boolean makeOutputStream() {
    try {
      out = new ObjectOutputStream(s.getOutputStream());
    } catch (Exception e) {
      System.out.println("Could not make Output Stream from Socket");
      e.printStackTrace();
      try {
        s.close();
      } catch (Exception ee) {
        System.out.println("Could not close Socket correctly");
        ee.printStackTrace();
      }
      return false;
    }
    return true;
  }

  public JSONObject getJson() {
    JSONObject json = new JSONObject();
    JSONParser parser = new JSONParser();
    try {
      json = (JSONObject) parser.parse(in.readObject().toString());
    } catch (EOFException e) {
    } catch (Exception e) {
      isConnected = false;
      System.out.println("Could not receive data");
      e.printStackTrace();
      try {
        s.close();
      } catch (Exception ee) {
        System.out.println("Could not close Socket correctly");
        ee.printStackTrace();
      }
    }
    if (isCloseType(json)) {
      closeSocket();
      json = new JSONObject();
    }
    return json;
  }

  private boolean isCloseType(JSONObject toCheck) {
    return toCheck.get("type").equals("close");
  }

  private boolean closeSocket() {
    isConnected = false;
    try {
      s.close();
    } catch (Exception ee) {
      System.out.println("Could not close Socket correctly");
      ee.printStackTrace();
      return false;
    }
    return true;
  }

  public boolean sendJson(JSONObject toSend) {
    try {
      out.writeObject(toSend);
      out.flush();
    } catch (Exception e) {
      isConnected = false;
      System.out.println("Could not send data");
      e.printStackTrace();
      try {
        s.close();
      } catch (Exception ee) {
        System.out.println("Could not close Socket correctly");
        ee.printStackTrace();
        return false;
      }
      return false;
    }
    return true;
  }
}
