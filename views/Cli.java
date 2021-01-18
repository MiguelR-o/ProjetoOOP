package views;

import controllers.Company;
import controllers.CompanyClass;
import controllers.ReadFile;
import models.Client;
import models.ID;
import models.Item;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Cli {
    public Cli(){
        start();
    }
    public void start() {
        Company companyClass = new CompanyClass();
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
                    String name = nameConcatenation(3, command);
                    if (!companyClass.hasCategory(category)) {
                        System.out.println("Categoria inexistente.");
                    } else if (companyClass.validPermission(category, permission)) {
                        System.out.println("Permissão inexistente.");
                    } else if (companyClass.hasProfessional(name, category)) {
                        System.out.println("Funcionário existente.");
                    } else {
                        int employeeID = companyClass.registerEmployee(category, permission, name);
                        System.out.println("Funcionário registado com o identificador "
                                + employeeID);
                    }
                    break;
                case "RC":
                    String employeeID = command[1];
                    String nameClient = nameConcatenation(2, command);
                    if (companyClass.hasClientName(nameClient)) {
                        System.out.println("Cliente existente.");
                    } else {
                      int clientID = companyClass.registerClient(nameClient, employeeID);
                        System.out.println(
                                "Cliente registado com o identificador " + clientID);
                    }
                    break;

                case "RI":
                    String clientID = command[1];
                    String itemName = nameConcatenation(2, command);
                    line = scanner.nextLine();
                    Boolean validInput = true;
                    if (companyClass.hasClient(clientID)) {
                        String[] itemPermissions = line.split(",");
                        if (itemPermissions[0].equals("")) {
                            String[] defaultPermission = {"N"};
                            int itemID = companyClass.registerItem(itemName, clientID, defaultPermission);
                            System.out.printf("Item registado para o client %s com o identificador %s%n", clientID,
                                    itemID);
                        } else {
                            for (String itemPermission : itemPermissions) {
                                if (!companyClass.hasPermission(itemPermission)) {
                                    validInput = false;
                                }
                            }
                            if (!validInput) {
                                System.out.println("Permissão inválida.");
                            } else {
                                int itemID = companyClass.registerItem(itemName, clientID, itemPermissions);
                                System.out.printf("Item registado para o client %s com o identificador %s%n", clientID,itemID);
                            }
                        }
                    } else {
                        System.out.println("Cliente inexistente.");
                    }
                    break;
                case "RL":
                    String placeName = command[1];
                    if (companyClass.hasPlaceName(placeName)) {
                        System.out.println("Local existente.");
                    } else {
                        int placeID = companyClass.registerPlace(placeName);
                        System.out.printf("Local registado com o identificador %d%n", placeID);
                    }
                    break;
                case "RD":
                    String stringClientID = command[1];
                    String stringPlaceID = command[2];
                    line = scanner.nextLine();
                    String[] employeeIDs = line.split(" ");
                    HashMap<Integer, String> itemsDeposited = new HashMap<Integer, String>(); // ID , Quantity
                    while (true) {
                        line = scanner.nextLine();
                        command = line.split(" ");
                        if (command[0].equals("")) {
                            break;
                        } else {
                            String stringItemID = command[0];
                            String quantity = command[1];
                            itemsDeposited.put(Integer.parseInt(stringItemID), quantity);

                        }
                    }
                    if (companyClass.hasClient(stringClientID)) {
                        if (companyClass.hasPlaceWithID(stringPlaceID)) {
                            if (companyClass.validItems(itemsDeposited,Integer.parseInt(stringClientID))) {
                                if (companyClass.validEmployeesID(employeeIDs)) {
                                    HashMap<String, Set> permissionMap = companyClass.createPermissionMap(employeeIDs);
                                    Set<String> summedPermissions = companyClass.storeItemsDepositedPermissions(
                                            itemsDeposited, Integer.parseInt(stringClientID));
                                    if (companyClass.validDriverPermissions(permissionMap, summedPermissions)) {
                                        if (companyClass.validDelivererPermissions(permissionMap, summedPermissions)) {
                                            int depositID = companyClass.registerDeposit(stringClientID, stringPlaceID,
                                                    employeeIDs, itemsDeposited);
                                            System.out.printf("Depósito registado com o identificador %d%n", depositID);
                                        } else {
                                            System.out.println("Carregador sem permissões.");
                                        }

                                    } else {
                                        System.out.println("Condutor sem permissões");
                                    }

                                } else {
                                    System.out.println("Funcionário inexistente.");
                                }

                            } else {
                                System.out.println("Item inexistente.");
                            }
                        } else {
                            System.out.println("Local inexistente.");
                        }
                    } else {
                        System.out.println("Cliente inexistente.");
                    }

                    break;
                case "RE":
                    /*
                    String stringClientID = command[1];
                    String stringPlaceID = command[2];
                    line = scanner.nextLine();
                    String[] employeeIDs = line.split(" ");
                    HashMap<Integer, String> itemsDelivered = new HashMap<Integer, String>(); // ID , Quantity
                    while (true) {
                        line = scanner.nextLine();
                        command = line.split(" ");
                        if (command[0].equals("")) {
                            break;
                        } else {
                            String stringItemID = command[0];
                            String quantity = command[1];
                            itemsDeposited.put(Integer.parseInt(stringItemID), quantity);

                        }
                    }
                    if (companyClass.hasClient(stringClientID)) {
                        if (companyClass.hasPlaceWithID(stringPlaceID)) {
                            if (companyClass.validItems(itemsDeposited,Integer.parseInt(stringClientID))) {
                                if (companyClass.validEmployeesID(employeeIDs)) {
                                    HashMap<String, Set> permissionMap = companyClass.createPermissionMap(employeeIDs);
                                    Set<String> summedPermissions = companyClass.storeItemsDepositedPermissions(
                                            itemsDeposited, Integer.parseInt(stringClientID));
                                    if (companyClass.validDriverPermissions(permissionMap, summedPermissions)) {
                                        if (companyClass.validDelivererPermissions(permissionMap, summedPermissions)) {
                                            int depositID = companyClass.registerDeposit(stringClientID, stringPlaceID,
                                                    employeeIDs, itemsDeposited);
                                            System.out.printf("Depósito registado com o identificador %d%n", depositID);
                                        } else {
                                            System.out.println("Carregador sem permissões.");
                                        }

                                    } else {
                                        System.out.println("Condutor sem permissões");
                                    }

                                } else {
                                    System.out.println("Funcionário inexistente.");
                                }

                            } else {
                                System.out.println("Item inexistente.");
                            }
                        } else {
                            System.out.println("Local inexistente.");
                        }
                    } else {
                        System.out.println("Cliente inexistente.");
                    }*/
                    break;
                case "CC":
                    String strClientID = command[1];
                    if(companyClass.hasClient(strClientID)){
                        Client client = companyClass.getClientByID(Integer.parseInt(strClientID));
                        System.out.println(client.getName());
                        System.out.println(companyClass.getEmployeeByID(client.getClientManagerID()).getName());
                        System.out.println("Items:");
                        for (int key : client.getItemMap().keySet()){
                           ArrayList<String> output = companyClass.prepareItemOutputCC(client,key);
                            ArrayList<String> permissions = companyClass.itemOutputCCPermissions(client,key);
                            System.out.printf("  %s (%s) %s %s%n",output.get(0),output.get(1),permissions,output.get(2));
                        }
                        System.out.println("Depósitos:");
                        for(int key : client.orderDepositIDs()){
                            String place = companyClass.getPlaceByID(client.getDepositMap().get(key).getPlace()).getName();
                            System.out.printf("  %d (%s)%n",key,place);
                        }
                        System.out.println("Entrgas:");
                        for(int key : client.orderDeliveryIDs()){
                            String place = companyClass.getPlaceByID(client.getDeliveryMap().get(key).getPlace()).getName();
                            System.out.printf("  %d (%s)%n",key,place);
                        }


                    }else{
                        System.out.println("Cliente inexistente.");
                    }
                    break;

                case "CI":
                    String strclientID = command[1];
                    String srtItemID = command[2];
                    if(companyClass.hasClient(strclientID)){
                       if(companyClass.hasItem(companyClass.getClientByID(Integer.parseInt(strclientID)),Integer.parseInt(srtItemID))){
                            Item item = companyClass.getClientByID(Integer.parseInt(strclientID)).getItemByID(Integer.parseInt(srtItemID));
                            ArrayList<String> perms = companyClass.extendPermissions(item.getPermissions());
                           System.out.printf("%d %s %s%n",item.getAmount(),perms,item.getName());
                           System.out.println("Depósitos:");
                           for(int key : item.orderDepositIDs()){
                               System.out.printf("  %d %d%n",key,item.getDeposits().get(key).getItemAmount(key));
                           }
                           System.out.println("Entregas:");
                           for(int key : item.orderDeliveryIDs()){
                               System.out.printf("  %d %d%n",key,item.getDeliveries().get(key).getItemAmount(key));
                           }

                       }else {
                           System.out.println("Item inexistente.");
                       }
                    }else{
                        System.out.println("Cliente inexistente.");
                    }
                    break;

                case "CE":

                    break;

                case "CF":
                    String strEmployeeID = command[1];
                    if(companyClass.hasEmployeeByID(Integer.parseInt(strEmployeeID))){
                       //TODO!!!
                    }else {
                        System.out.println("Funcionário inexistente.");
                    }
                    break;

                case "G":
                    String saveFileName = command[1];
                    companyClass.saveFile(saveFileName);
                    System.out.println("Ficheiro gravado com sucesso");
                    break;

                case "L":
                    String readFileName = command[1];
                    ReadFile rf = new ReadFile();
                    try {
                        companyClass = rf.readFile(readFileName);
                        System.out.println("Ficheiro lido com sucesso.");
                    }
                    catch(Exception e) {
                        System.out.println("Ficheiro inexistente.");
                    }
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

    String nameConcatenation(int nameStart, String[] commands) {
        String fullName = "";
        for (int i = nameStart; i < commands.length; i++) {
            fullName += commands[i] + " ";
        }
        return fullName;
    }
}
