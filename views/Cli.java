package views;

import controllers.Company;
import models.Employee;

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
                    String category = command[1];
                    String permission = command[2];
                    String name = nameConcatenation(3,command);
                    if(!company.hasCategory(category)){
                        System.out.println("Categoria inexistente.");
                    }else if(company.validPermission(permission,category)){
                        System.out.println("Permissão inexistente.");
                    }else if(company.hasProfessional(name,category)){
                    System.out.println("Funcionário existente.");
                }else{
                        company.registerEmployee(category,permission,name);
                        System.out.println("Funcionário registado com o identificador " + company.getEmployeeID(company.getProfessional(name,category)));
                }
                    break;
                case "RC":
                    //Nome contem espaços e deve ser unico no sistema
                    // IDCliente numero unico atribuido pelo sistema (starts @ 1)
                    String  employeeID = command[1];
                    // String nameClient = command[2];
                    String name = "";
                    for(int i=2 ; i<line.length();i++){
                       name += command[i];
                    }
                    if(company.hasClient(nameClient)){
                        System.out.println("Cliente existente.");
                    }else{
                        company.registerClient(nameClient);
                        System.out.println("Cliente registado com o identificador " + company.getClientID(nameClient));
                    }
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
                    System.out.println("Instrução inválida.");
            }
        }
        scanner.close();
    }
    public String nameConcatenation(int nameStart, String[] commands){
        String fullName = "";
        for(int i = nameStart;i < commands.length; i++) {
            fullName += commands[i] + " ";
        }
        return  fullName;
    }
}