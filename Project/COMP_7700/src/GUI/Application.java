/**
 * COMP 7700
 * Project Prototype
 * @AUTHOR "Matthew Tyler McGlawn <mtm0002@auburn.edu>"
 * @AUTHOR "Rachel <rachel@auburn.edu>"
 * @AUTHOR "Sean <sean@auburn.edu>"
 * @AUTHOR "Sri Ram <sriram@auburn.edu>"
 */

package FinalProject;

public class Application {
  public static void main(String[] args) {
    //launch frontend
    new DataSender("127.0.0.1", 23987);
    new Frontend();
  }
}
