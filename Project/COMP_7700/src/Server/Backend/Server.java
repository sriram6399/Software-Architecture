/**
 * File used to process information from the frontend.
 */

package FinalProject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

public class Server {
  
  DataReceiver receiver;
  String dbName;
  Connection conn;

  public static void main(String[] args) {
    Server server = new Server();
    server.run();
  }

  Server() {
    receiver = new DataReceiver(23987);
    String dbIP = "database";
    String dbPort = "3306";
    dbName = "store_data";
    String dbUser = "root";
    String dbPwd = "Passw0rd1!";
    try{
      Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
      conn = DriverManager.getConnection("jdbc:mysql://" + dbIP + ":" + dbPort + "/" + dbName + "?user=" + dbUser + "&password=" + dbPwd);
    } catch (SQLException sqlEx) {
      System.out.println("SQLException: " + sqlEx.getMessage());
      System.out.println("SQLState: " + sqlEx.getSQLState());
      System.out.println("VendorError: " + sqlEx.getErrorCode());
    } catch (Exception e) {
      System.out.println("Unknown Error: " + e.getMessage());
    }
  }

  public void run() {
    while (receiver.makeConnection()) {
      while (receiver.isConnected) {
        JSONObject queryAsJson = receiver.getJson();
        if (!queryAsJson.isEmpty()) {
          JSONObject returnJson;
          System.out.println(queryAsJson);
          if (queryAsJson.get("type").equals("mysql_query")) {
            returnJson = queryDatabase(queryAsJson.get("mysql_query").toString());
          } else if (queryAsJson.get("type").equals("mysql_update")) {
            returnJson = updateDatabase(queryAsJson.get("mysql_update").toString());
          } else {
            returnJson = new JSONObject();
            returnJson.put("error", "Unknown type");
          }
          System.out.println(returnJson);
          receiver.sendJson(returnJson);
        }
      }
    }
  }

  public JSONObject queryDatabase(String queryStatement){
    Map<String, JSONArray> outputList = new LinkedHashMap<String, JSONArray>();
    JSONObject output = new JSONObject();
    try{
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(queryStatement);
      ResultSetMetaData md = rs.getMetaData();
      int columnCount = md.getColumnCount();
      while (rs.next()) {
        //MYSQL is indexed starting at 1
        for (int i=1; i <= columnCount; i++){
          JSONArray values = getValues(rs, outputList.get(md.getColumnName(i)), i);
          outputList.put(md.getColumnName(i),values);
        }
      }
      StringWriter out = new StringWriter();
      JSONValue.writeJSONString(outputList,out);
      String jsonText = out.toString();
      output.put("type", "mysql_data");
      output.put("mysql_data", jsonText);
    } catch (SQLException sqlEx) {
      output.put("type", "error");
      JSONArray jarray = new JSONArray();
      jarray.add("SQLException");
      jarray.add("SQLState");
      jarray.add("VendorError");
      output.put("error", jarray);
      output.put("SQLException", sqlEx.getMessage());
      output.put("SQLState", sqlEx.getSQLState());
      output.put("VendorError", sqlEx.getErrorCode());
      System.out.println(output);
    } catch (Exception e) {
      JSONArray jarray = new JSONArray();
      jarray.add("Unknown");
      output.put("Unknown", e.getMessage());
      System.out.println(output);
    }
    return output;
  }

  public JSONObject updateDatabase(String queryStatement){
    Map<String, JSONArray> outputList = new LinkedHashMap<String, JSONArray>();
    JSONObject output = new JSONObject();
    try{
      Statement stmt = conn.createStatement();
      stmt.executeUpdate(queryStatement);
      output.put("type", "mysql_update");
      output.put("mysql_update", "true");
    } catch (SQLException sqlEx) {
      if (sqlEx.getErrorCode() == 1062 && sqlEx.getSQLState().equals("23000")) {
        output.put("type", "mysql_update");
        output.put("mysql_update", "true");
      } else {
        output.put("type", "error");
        JSONArray jarray = new JSONArray();
        jarray.add("SQLException");
        jarray.add("SQLState");
        jarray.add("VendorError");
        output.put("error", jarray);
        output.put("SQLException", sqlEx.getMessage());
        output.put("SQLState", sqlEx.getSQLState());
        output.put("VendorError", sqlEx.getErrorCode());
        System.out.println(output);
      }
    } catch (Exception e) {
      JSONArray jarray = new JSONArray();
      jarray.add("Unknown");
      output.put("Unknown", e.getMessage());
      System.out.println(output);
    }
    return output;
  }

  private JSONArray getValues(ResultSet results, JSONArray values, int columnIndex) throws Exception{
    JSONArray output = new JSONArray();
    if (values == null){
      output.add(results.getString(columnIndex));
    } else{
      output = values;
      output.add(results.getString(columnIndex));
    }
    return output;
  }
}
