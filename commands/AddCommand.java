package commands;

import java.util.Scanner;

import tools.*;
import product.*;

/**
 * Класс-команда Add.
 * @author Koleshechka
 */
public class AddCommand extends Command{

    String name = "";
    int x = 0;
    int y = 0;
    double price = 0.0;
    UnitOfMeasure unitOfMeasure = null;
    String nameOfOrg = "";
    Double annualTurnover = 0.0;
    OrganizationType type = null;

    public AddCommand(String name, int x, int y, double price, UnitOfMeasure unitOfMeasure, String nameOfOrg, Double annualTurnover, OrganizationType type) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.price = price;
        this.unitOfMeasure = unitOfMeasure;
        this.nameOfOrg = nameOfOrg;
        this.annualTurnover = annualTurnover;
        this.type = type;
    }

    public AddCommand() {
        this.name = "";
        this.x = 0;
        this.y = 0;
        this.price = 0.0;
        this.unitOfMeasure = null;
        this.nameOfOrg = "";
        this.annualTurnover = 0.0;
        this.type = null;
    }

    public String getName() {
        return name;
    }

    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }

    public double getPrice() {
        return price;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public String getNameOfOrg() {
        return nameOfOrg;
    }

    public Double getAnnualTurnover() {
        return annualTurnover;
    }

    public OrganizationType getType() {
        return type;
    }

    /**
     * Интерактивный метод добавляения нового элемента в коллекцию.
     * @param console
     * @param scanner
     * @param id
     * @param isFile
     */

    public static void add(Console console, Scanner scanner, Long id, boolean isFile) {
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
                if (isFile) {
                    System.out.println("Неправильный формат, мы не сможем добавить этот элемент в коллекцию.");
                    return;
                }
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
                if (isFile) {
                    System.out.println("Неправильный формат, мы не сможем добавить этот элемент в коллекцию.");
                    return;
                }
                System.out.println("X - целое число. Попробуйте еще раз.");
            }
        }

        while(true) {
            System.out.println("Введите Y:");
            try {
                y = Integer.parseInt(ReadCommand.readForAdd(scanner));
                break;
            } catch (NumberFormatException e) {
                if (isFile) {
                    System.out.println("Неправильный формат, мы не сможем добавить этот элемент в коллекцию.");
                    return;
                }
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
                if (isFile) {
                    System.out.println("Неправильный формат, мы не сможем добавить этот элемент в коллекцию.");
                    return;
                }
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
               if (isFile) {
                   System.out.println("Неправильный формат, мы не сможем добавить этот элемент в коллекцию.");
                   return;
               }
               System.out.println("Обратите внимание на доступные варианты.");
           }
        }

        System.out.println("Сейчас будем вводить данные о производителе.");

        while (true) {
            System.out.println("Введите название организации:");
            nameOfOrg = ReadCommand.readForAdd(scanner);
            if (nameOfOrg.equals("")) {
                if (isFile) {
                    System.out.println("Неправильный формат, мы не сможем добавить этот элемент в коллекцию.");
                    return;
                }
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
                if (isFile) {
                    System.out.println("Неправильный формат, мы не сможем добавить этот элемент в коллекцию.");
                    return;
                }
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
                if (isFile) {
                    System.out.println("Неправильный формат, мы не сможем добавить этот элемент в коллекцию.");
                    return;
                }
                System.out.println("Обратите внимание на доступные варианты.");
            }
        }

        Product product = new Product(name, x, y, price, unitOfMeasure, nameOfOrg, annualTurnover,type);
        if (id != null) {
            product.setId(id);
        }
        try {
            console.addToCollection(product);
            System.out.println("Ваш продукт добавлен в коллекцию.");
        } catch (Exception e) {
            System.out.println("Ваш продукт не добавлен в коллеуцию.");
        }
    }

    public String add(Console console, String name, int x, int y, double price, UnitOfMeasure unitOfMeasure, String nameOfOrg, Double annualTurnover, OrganizationType type) {
        Product product = new Product(name, x, y, price, unitOfMeasure, nameOfOrg, annualTurnover, type);
        try {
            console.addToCollection(product);
            return("Ваш продукт добавлен в коллекцию.");
        } catch (Exception e) {
            return ("Ваш продукт не добавлен в коллеуцию.");
        }
    }

    public String add(Console console, String name, int x, int y, double price, UnitOfMeasure unitOfMeasure, String nameOfOrg, Double annualTurnover, OrganizationType type, long id) {
        Product product = new Product(name, x, y, price, unitOfMeasure, nameOfOrg, annualTurnover, type);
        product.setId(id);
        try {
            console.addToCollection(product);
            return("Ваш продукт добавлен в коллекцию.");
        } catch (Exception e) {
            return ("Ваш продукт не добавлен в коллеуцию.");
        }
    }

    @Override
    public String doit(Console console) {
        return add(console, this.name, this.x, this.y, this.price, this.unitOfMeasure, this.nameOfOrg, this.annualTurnover, this.type);
    }
}
