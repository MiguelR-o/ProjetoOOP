package views;

import controllers.Company;

import java.util.Scanner;

public class Cli {
    public void start() {
        Company company = null;
        Scanner scanner = new Scanner(System.in);
        String line;
        boolean endLoop = false;
        while (!endLoop) {
            line = scanner.nextLine();
            String[] command = line.split(" ");
            switch (command[0]) {
                case "RF":
                    int employeeID = Integer.parseInt(command[1]);
                    String category = command[2];
                    String permission = command[3];
                    String name = command[4];
                    if(!company.hasCategory(category)){
                        System.out.println("Categoria inexistente.");
                    }else if(company.validPermission(permission,category)){
                        System.out.println("Permissão inexistente.");
                    }else if(company.hasProfessionalInCategory(name,category)){
                    System.out.println("Funcionário existente.");
                }else{
                        company.registerEmployee(employeeID,category,permission,name);
                }
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
                case "":
                   endLoop = true;
                   break;
                default:
                    System.out.println("Intrução inválida.");
            }
        }
    }
}