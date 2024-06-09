import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class RobotExample {

    public static void main(String[] args) {
        try {
            Robot robot = new Robot();

            // Giving a small delay to switch focus to the desired window
            robot.delay(2000);

            // Typing "hello"
            typeHello(robot);

        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private static void typeHello(Robot robot) {
        robot.keyPress(KeyEvent.VK_H);
        robot.keyRelease(KeyEvent.VK_H);

        robot.keyPress(KeyEvent.VK_E);
        robot.keyRelease(KeyEvent.VK_E);

        robot.keyPress(KeyEvent.VK_L);
        robot.keyRelease(KeyEvent.VK_L);

        robot.keyPress(KeyEvent.VK_L);
        robot.keyRelease(KeyEvent.VK_L);

        robot.keyPress(KeyEvent.VK_O);
        robot.keyRelease(KeyEvent.VK_O);
    }
}
