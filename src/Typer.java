import java.util.*;

public class Typer {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter");
        String text = input.nextLine();

        output(text);

        // System.out.print(text);
        // System.out.print("\b");
        input.close();

    }

    public static void output(String text) {

        for (int i = 0; i < text.length(); i++) {

            int random = (int) (Math.random() * 100);

            if (random > 50) {
                // type letter
                char ch = text.charAt(i);
                System.out.print(ch);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            } else {
                // backspace letter
                i--;
                try {
                    Thread.sleep(350);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    System.out.print("\b");// sopposedly works in normal console

                }
            }
        }
    }

}