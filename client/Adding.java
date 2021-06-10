package client;

import commands.ReadCommand;
import product.OrganizationType;
import product.UnitOfMeasure;

import java.util.Scanner;
import commands.*;

public class Adding {
    public static AddCommand adding(Scanner scanner) {
        String name = "";
        int x = 0;
        int y = 0;
        double price = 0.0;
        UnitOfMeasure unitOfMeasure = null;
        String nameOfOrg = "";
        Double annualTurnover = 0.0;
        OrganizationType type = null;

        while (true) {
            System.out.println("Введите название продукта:");
            name = ReadCommand.readForAdd(scanner);
            if (name.equals("")) {
                System.out.println("Вы ничего не ввели. Попробуйте еще раз.");
            }
            else break;
        }

        System.out.println("Сейчас будем вводить координаты.");

        while(true) {
            System.out.println("Введите X:");
            try {
                x = Integer.parseInt(ReadCommand.readForAdd(scanner));
                break;
            } catch (NumberFormatException e) {
                System.out.println("X - целое число. Попробуйте еще раз.");
            }
        }

        while(true) {
            System.out.println("Введите Y:");
            try {
                y = Integer.parseInt(ReadCommand.readForAdd(scanner));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Y - целое число. Попробуйте еще раз.");
            }
        }

        while(true) {
            System.out.println("Введите цену:");
            try {
                price = Double.parseDouble(ReadCommand.readForAdd(scanner));
                if (price <= 0) {
                    throw new NumberFormatException("");
                } else break;
            } catch (NumberFormatException e) {
                System.out.println("Цена - вещественное число, строго большее нуля. Попробуйте еще раз.");
            }
        }

        while(true) {
            System.out.println("Введите единицы измерения:\nДоступные варианты: KILOGRAMS, CENTIMETERS, LITERS");
            try {
                String s = ReadCommand.readForAdd(scanner);
                switch (s) {
                    case "KILOGRAMS": unitOfMeasure = UnitOfMeasure.KILOGRAMS;break;
                    case "CENTIMETERS": unitOfMeasure = UnitOfMeasure.CENTIMETERS;break;
                    case "LITERS": unitOfMeasure = UnitOfMeasure.LITERS;break;
                    default:throw new Exception();
                }
                break;
            }catch (Exception e) {
                System.out.println("Обратите внимание на доступные варианты.");
            }
        }

        System.out.println("Сейчас будем вводить данные о производителе.");

        while (true) {
            System.out.println("Введите название организации:");
            nameOfOrg = ReadCommand.readForAdd(scanner);
            if (nameOfOrg.equals("")) {
                System.out.println("Вы ничего не ввели. Попробуйте еще раз.");
            } else break;
        }

        while (true) {
            System.out.println("Введите годовой оборот организации");
            try {
                String annualTurnoverString = ReadCommand.readForAdd(scanner);
                if(annualTurnoverString.equals("")) {
                    annualTurnover = null;
                    break;
                }else {
                    annualTurnover = Double.parseDouble(annualTurnoverString);
                    if (annualTurnover <= 0) {
                        throw new NumberFormatException("");
                    } else break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Годовой оборот организации - вещественное число, строго большее нуля. Попробуйте еще раз.");
            }
        }

        while (true) {
            System.out.println("Введите тип организации:\nДоступные варианты: COMMERCIAL, PUBLIC, GOVERNMENT, PRIVATE_LIMITED_COMPANY, OPEN_JOINT_STOCK_COMPANY");
            try {
                String s = ReadCommand.readForAdd(scanner);
                switch (s) {
                    case "COMMERCIAL": type = OrganizationType.COMMERCIAL;break;
                    case "PUBLIC": type = OrganizationType.PUBLIC;break;
                    case "GOVERNMENT": type = OrganizationType.GOVERNMENT;break;
                    case "PRIVATE_LIMITED_COMPANY": type = OrganizationType.PRIVATE_LIMITED_COMPANY;break;
                    case "OPEN_JOINT_STOCK_COMPANY": type = OrganizationType.OPEN_JOINT_STOCK_COMPANY;break;
                    default: throw new Exception();
                }
                break;
            }catch (Exception e) {
                System.out.println("Обратите внимание на доступные варианты.");
            }
        }

        AddCommand addCommand = new AddCommand(name, x, y, price, unitOfMeasure, nameOfOrg, annualTurnover,type);
        return addCommand;
    }
}
