package views;

import java.util.Scanner;

public class Cli {
    public void start() {

        Scanner scanner = new Scanner(System.in);
        String line;
        while (true) {
            line = scanner.nextLine();
            String[] command = line.split(" ");
            switch (command[0]) {
                case "RF":

                    break;
                case "RC":
                    break;
                case "RI":
                    break;
                case "RL":
                    break;
                case "RD":
                    break;
                case "RE":
                    break;
                case "CC":
                    break;
                case "CI":
                    break;
                case "CE":
                    break;
                case "CF":
                    break;
                default:
                    System.out.println("Intrução inválida.");
            }
        }
    }
}