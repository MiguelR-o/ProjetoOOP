package views;

import controllers.Company;
import controllers.CompanyClass;
import models.ID;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Cli {
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
                    } else if (companyClass.validPermission(permission, category)) {
                        System.out.println("Permissão inexistente.");
                    } else if (companyClass.hasProfessional(name, category)) {
                        System.out.println("Funcionário existente.");
                    } else {
                        companyClass.registerEmployee(category, permission, name);
                        System.out.println("Funcionário registado com o identificador "
                                + companyClass.getEmployeeID(companyClass.getProfessional(name, category)));
                    }
                    break;

                case "RC":
                    // Nome contem espaços e deve ser unico no sistema
                    // IDCliente numero unico atribuido pelo sistema (starts @ 1)
                    String employeeID = command[1];
                    String nameClient = "";
                    for (int i = 2; i < line.length(); i++) {
                        nameClient += command[i];
                    }
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
                    String itemName = command[2];
                    line = scanner.nextLine();
                    Boolean validInput = true;
                    if (companyClass.hasClient(clientID)) {
                        String[] itemPermissions = line.split(",");
                        if (itemPermissions.length == 0) {
                            int itemID = companyClass.registerItem(itemName, clientID, itemPermissions);
                            System.out.printf("Item registado para o client %d com o identificador %d", clientID,
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
                                System.out.printf("Item registado para o client %d com o identificador %d", clientID,
                                        itemID);
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
                        System.out.printf("Local registado com o identificador %d", placeID, ".");
                    }
                    break;

                case "RD":
                    String stringClientID = command[1];
                    String stringPlaceID = command[2];
                    line = scanner.nextLine();
                    String[] employeeIDs = line.split(" ");
                    HashMap<ID, String> itemsDeposited = new HashMap<ID, String>(); // ID , Quantity
                    while (true) {
                        line = scanner.nextLine();
                        command = line.split(" ");
                        if (command[0].equals("")) {
                            break;
                        } else {
                            String stringItemID = command[0];
                            String quantity = command[1];
                            itemsDeposited.put(companyClass.convertToID(Integer.parseInt(stringItemID)), quantity);

                        }
                    }
                    if (companyClass.hasClient(stringClientID)) {
                        if (companyClass.hasPlaceWithID(stringPlaceID)) {
                            if (companyClass.validItems(itemsDeposited,
                                    companyClass.convertToID(Integer.parseInt(stringClientID)))) {
                                if (companyClass.validEmployeesID(employeeIDs)) {
                                    HashMap<String, Set> permissionMap = companyClass.createPermissionMap(employeeIDs);
                                    Set<String> summedPermissions = companyClass.storeItemsDepositedPermissions(
                                            itemsDeposited, companyClass.convertToID(Integer.parseInt(stringClientID)));
                                    if (companyClass.validDriverPermissions(permissionMap, summedPermissions)) {
                                        if (companyClass.validDelivererPermissions(permissionMap, summedPermissions)) {
                                            int depositID = companyClass.registerDeposit(stringClientID, stringPlaceID,
                                                    employeeIDs, itemsDeposited);
                                            System.out.printf("Depósito registado com o identificador %d", depositID, ".");
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
                    String stringClientID = command[1];
                    String stringPlaceID = command[2];
                    line = scanner.nextLine();
                    String[] employeeIDs = line.split(" ");
                    HashMap<ID, String> itemsDeposited = new HashMap<ID, String>(); // ID , Quantity
                    while (true) {
                        line = scanner.nextLine();
                        command = line.split(" ");
                        if (command[0].equals("")) {
                            break;
                        } else {
                            String stringItemID = command[0];
                            String quantity = command[1];
                            itemsDeposited.put(companyClass.convertToID(Integer.parseInt(stringItemID)), quantity);

                        }
                    }
                    if (companyClass.hasClient(stringClientID)) {
                        if (companyClass.hasPlaceWithID(stringPlaceID)) {
                            if (companyClass.validItems(itemsDeposited,
                                    companyClass.convertToID(Integer.parseInt(stringClientID)))) {
                                if (companyClass.validEmployeesID(employeeIDs)) {
                                    HashMap<String, Set> permissionMap = companyClass.createPermissionMap(employeeIDs);
                                    Set<String> summedPermissions = companyClass.storeItemsDepositedPermissions(
                                            itemsDeposited, companyClass.convertToID(Integer.parseInt(stringClientID)));
                                    if (companyClass.validDriverPermissions(permissionMap, summedPermissions)) {
                                        if (companyClass.validDelivererPermissions(permissionMap, summedPermissions)) {
                                            int deliveryID = companyClass.registerDelivery(stringClientID, stringPlaceID,
                                                    employeeIDs, itemsDeposited);
                                            System.out.printf("Entrega registada com o identificador%d", deliveryID);
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

                case "CC":
                    String stringClientID = command[1];

                    if(!companyClass.hasClient(stringClientID)){
                        System.out.println("Cliente inexistente.");
                    }else{
                        System.out.println(companyClass.;
                    }

                    break;
                case "CI":
                    break;
                case "CE":
                    break;
                case "CF":
                    break;

                case "G":
                    String saveFileName = command[1];

                    try {
                        FileOutputStream fileOutputStream =
                                new FileOutputStream(saveFileName);
                        ObjectOutputStream objectOutputStream =
                                new ObjectOutputStream(fileOutputStream);
                        objectOutputStream.writeObject(companyClass);
                        objectOutputStream.close();
                        fileOutputStream.close();
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                case "L":
                    String readFileName = command[1];

                    try{
                        FileInputStream fileInputStream = new FileInputStream(readFileName);
                        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                        companyClass = (CompanyClass)objectInputStream.readObject();
                        objectInputStream.close();
                        fileInputStream.close();

                    }
                    catch (Exception e){
                        e.printStackTrace();
                        System.out.printf("Ficheiro inexistente.");
                        return;
                    }
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
