
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.*;

public class RoboType {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String run = "0";

        while (run.equals("0")) {

            System.out.println("Enter stuff to be written");
            String text = input.nextLine();

            System.out.println("the text to type is: " + text);
            System.out.println("Switch to another text field to type u have 5 sec");

            // Switch to other Screen
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch blockok
                e.printStackTrace();
            }

            output(text);

            System.out.println("Enter 0 to go again, or anything else to stop");
            run = input.nextLine();

        }
        input.close();
    }

    public static void output(String text) {

        for (int i = 0; i < text.length(); i++) {

            int random = (int) (Math.random() * 100);

            if (random < 80) {
                // type letter
                char ch = text.charAt(i);

                Robot robot;
                try {
                    robot = new Robot();
                    type(robot, ch); // *
                } catch (AWTException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(80);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            } else if (random < 94) {
                // slower typing
                char ch = text.charAt(i);

                Robot robot;
                try {
                    robot = new Robot();
                    type(robot, ch); // *
                } catch (AWTException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else { // Deletes
                if (i > 5) {
                    i--;
                    i--;
                    Robot robot;
                    try {
                        robot = new Robot();
                        robot.keyPress(KeyEvent.VK_BACK_SPACE);
                        robot.keyRelease(KeyEvent.VK_BACK_SPACE);
                    } catch (AWTException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(120);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }

        }
    }

    // This method has all the characters
    private static void type(Robot robot, char ch) {

        if (ch == 'a') {
            robot.keyPress(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_A);
        } else if (ch == 'b') {
            robot.keyPress(KeyEvent.VK_B);
            robot.keyRelease(KeyEvent.VK_B);
        } else if (ch == 'c') {
            robot.keyPress(KeyEvent.VK_C);
            robot.keyRelease(KeyEvent.VK_C);
        } else if (ch == 'd') {
            robot.keyPress(KeyEvent.VK_D);
            robot.keyRelease(KeyEvent.VK_D);
        } else if (ch == 'e') {
            robot.keyPress(KeyEvent.VK_E);
            robot.keyRelease(KeyEvent.VK_E);
        } else if (ch == 'f') {
            robot.keyPress(KeyEvent.VK_F);
            robot.keyRelease(KeyEvent.VK_F);
        } else if (ch == 'g') {
            robot.keyPress(KeyEvent.VK_G);
            robot.keyRelease(KeyEvent.VK_G);
        } else if (ch == 'h') {
            robot.keyPress(KeyEvent.VK_H);
            robot.keyRelease(KeyEvent.VK_H);
        } else if (ch == 'i') {
            robot.keyPress(KeyEvent.VK_I);
            robot.keyRelease(KeyEvent.VK_I);
        } else if (ch == 'j') {
            robot.keyPress(KeyEvent.VK_J);
            robot.keyRelease(KeyEvent.VK_J);
        } else if (ch == 'k') {
            robot.keyPress(KeyEvent.VK_K);
            robot.keyRelease(KeyEvent.VK_K);
        } else if (ch == 'l') {
            robot.keyPress(KeyEvent.VK_L);
            robot.keyRelease(KeyEvent.VK_L);
        } else if (ch == 'm') {
            robot.keyPress(KeyEvent.VK_M);
            robot.keyRelease(KeyEvent.VK_M);
        } else if (ch == 'n') {
            robot.keyPress(KeyEvent.VK_N);
            robot.keyRelease(KeyEvent.VK_N);
        } else if (ch == 'o') {
            robot.keyPress(KeyEvent.VK_O);
            robot.keyRelease(KeyEvent.VK_O);
        } else if (ch == 'p') {
            robot.keyPress(KeyEvent.VK_P);
            robot.keyRelease(KeyEvent.VK_P);
        } else if (ch == 'q') {
            robot.keyPress(KeyEvent.VK_Q);
            robot.keyRelease(KeyEvent.VK_Q);
        } else if (ch == 'r') {
            robot.keyPress(KeyEvent.VK_R);
            robot.keyRelease(KeyEvent.VK_R);
        } else if (ch == 's') {
            robot.keyPress(KeyEvent.VK_S);
            robot.keyRelease(KeyEvent.VK_S);
        } else if (ch == 't') {
            robot.keyPress(KeyEvent.VK_T);
            robot.keyRelease(KeyEvent.VK_T);
        } else if (ch == 'u') {
            robot.keyPress(KeyEvent.VK_U);
            robot.keyRelease(KeyEvent.VK_U);
        } else if (ch == 'v') {
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
        } else if (ch == 'w') {
            robot.keyPress(KeyEvent.VK_W);
            robot.keyRelease(KeyEvent.VK_W);
        } else if (ch == 'x') {
            robot.keyPress(KeyEvent.VK_X);
            robot.keyRelease(KeyEvent.VK_X);
        } else if (ch == 'y') {
            robot.keyPress(KeyEvent.VK_Y);
            robot.keyRelease(KeyEvent.VK_Y);
        } else if (ch == 'z') {
            robot.keyPress(KeyEvent.VK_Z);
            robot.keyRelease(KeyEvent.VK_Z);
        } else if (ch == 'A') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'B') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_B);
            robot.keyRelease(KeyEvent.VK_B);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'C') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_C);
            robot.keyRelease(KeyEvent.VK_C);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'D') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_D);
            robot.keyRelease(KeyEvent.VK_D);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'E') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_E);
            robot.keyRelease(KeyEvent.VK_E);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'F') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_F);
            robot.keyRelease(KeyEvent.VK_F);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'G') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_G);
            robot.keyRelease(KeyEvent.VK_G);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'H') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_H);
            robot.keyRelease(KeyEvent.VK_H);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'I') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_I);
            robot.keyRelease(KeyEvent.VK_I);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'J') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_J);
            robot.keyRelease(KeyEvent.VK_J);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'K') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_K);
            robot.keyRelease(KeyEvent.VK_K);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'L') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_L);
            robot.keyRelease(KeyEvent.VK_L);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'M') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_M);
            robot.keyRelease(KeyEvent.VK_M);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'N') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_N);
            robot.keyRelease(KeyEvent.VK_N);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'O') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_O);
            robot.keyRelease(KeyEvent.VK_O);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'P') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_P);
            robot.keyRelease(KeyEvent.VK_P);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'Q') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_Q);
            robot.keyRelease(KeyEvent.VK_Q);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'R') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_R);
            robot.keyRelease(KeyEvent.VK_R);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'S') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_S);
            robot.keyRelease(KeyEvent.VK_S);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'T') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_T);
            robot.keyRelease(KeyEvent.VK_T);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'U') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_U);
            robot.keyRelease(KeyEvent.VK_U);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'V') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'W') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_W);
            robot.keyRelease(KeyEvent.VK_W);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'X') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_X);
            robot.keyRelease(KeyEvent.VK_X);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'Y') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_Y);
            robot.keyRelease(KeyEvent.VK_Y);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == 'Z') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_Z);
            robot.keyRelease(KeyEvent.VK_Z);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == '0') {
            robot.keyPress(KeyEvent.VK_0);
            robot.keyRelease(KeyEvent.VK_0);
        } else if (ch == '1') {
            robot.keyPress(KeyEvent.VK_1);
            robot.keyRelease(KeyEvent.VK_1);
        } else if (ch == '2') {
            robot.keyPress(KeyEvent.VK_2);
            robot.keyRelease(KeyEvent.VK_2);
        } else if (ch == '3') {
            robot.keyPress(KeyEvent.VK_3);
            robot.keyRelease(KeyEvent.VK_3);
        } else if (ch == '4') {
            robot.keyPress(KeyEvent.VK_4);
            robot.keyRelease(KeyEvent.VK_4);
        } else if (ch == '5') {
            robot.keyPress(KeyEvent.VK_5);
            robot.keyRelease(KeyEvent.VK_5);
        } else if (ch == '6') {
            robot.keyPress(KeyEvent.VK_6);
            robot.keyRelease(KeyEvent.VK_6);
        } else if (ch == '7') {
            robot.keyPress(KeyEvent.VK_7);
            robot.keyRelease(KeyEvent.VK_7);
        } else if (ch == '8') {
            robot.keyPress(KeyEvent.VK_8);
            robot.keyRelease(KeyEvent.VK_8);
        } else if (ch == '9') {
            robot.keyPress(KeyEvent.VK_9);
            robot.keyRelease(KeyEvent.VK_9);
        } else if (ch == '.') {
            robot.keyPress(KeyEvent.VK_PERIOD);
            robot.keyRelease(KeyEvent.VK_PERIOD);
        } else if (ch == ',') {
            robot.keyPress(KeyEvent.VK_COMMA);
            robot.keyRelease(KeyEvent.VK_COMMA);
        } else if (ch == '!') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_1);
            robot.keyRelease(KeyEvent.VK_1);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == '?') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_SLASH);
            robot.keyRelease(KeyEvent.VK_SLASH);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == '-') {
            robot.keyPress(KeyEvent.VK_MINUS);
            robot.keyRelease(KeyEvent.VK_MINUS);
        } else if (ch == '_') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_MINUS);
            robot.keyRelease(KeyEvent.VK_MINUS);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == '=') {
            robot.keyPress(KeyEvent.VK_EQUALS);
            robot.keyRelease(KeyEvent.VK_EQUALS);
        } else if (ch == '+') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_EQUALS);
            robot.keyRelease(KeyEvent.VK_EQUALS);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == ';') {
            robot.keyPress(KeyEvent.VK_SEMICOLON);
            robot.keyRelease(KeyEvent.VK_SEMICOLON);
        } else if (ch == ':') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_SEMICOLON);
            robot.keyRelease(KeyEvent.VK_SEMICOLON);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == '\'') {
            robot.keyPress(KeyEvent.VK_QUOTE);
            robot.keyRelease(KeyEvent.VK_QUOTE);
        } else if (ch == '\"') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_QUOTE);
            robot.keyRelease(KeyEvent.VK_QUOTE);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == '(') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_9);
            robot.keyRelease(KeyEvent.VK_9);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == ')') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_0);
            robot.keyRelease(KeyEvent.VK_0);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == '[') {
            robot.keyPress(KeyEvent.VK_OPEN_BRACKET);
            robot.keyRelease(KeyEvent.VK_OPEN_BRACKET);
        } else if (ch == ']') {
            robot.keyPress(KeyEvent.VK_CLOSE_BRACKET);
            robot.keyRelease(KeyEvent.VK_CLOSE_BRACKET);
        } else if (ch == '{') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_OPEN_BRACKET);
            robot.keyRelease(KeyEvent.VK_OPEN_BRACKET);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == '}') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_CLOSE_BRACKET);
            robot.keyRelease(KeyEvent.VK_CLOSE_BRACKET);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == '\\') {
            robot.keyPress(KeyEvent.VK_BACK_SLASH);
            robot.keyRelease(KeyEvent.VK_BACK_SLASH);
        } else if (ch == '|') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_BACK_SLASH);
            robot.keyRelease(KeyEvent.VK_BACK_SLASH);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == '/') {
            robot.keyPress(KeyEvent.VK_SLASH);
            robot.keyRelease(KeyEvent.VK_SLASH);
        } else if (ch == '<') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_COMMA);
            robot.keyRelease(KeyEvent.VK_COMMA);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == '>') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_PERIOD);
            robot.keyRelease(KeyEvent.VK_PERIOD);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (ch == ' ') {
            robot.keyPress(KeyEvent.VK_SPACE);
            robot.keyRelease(KeyEvent.VK_SPACE);
        }
    }
}