package tools;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import product.*;

import java.io.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.NoSuchElementException;


import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Класс парсера для чтения коллекции из json-файла.
 *
 * @author Koleshechka
 */

public class JsonParser {
    private final String path;
    private int i = 0;

    UnitOfMeasure unitOfMeasure = null;
    OrganizationType type = null;

    public JsonParser(String path) {
        this.path = path;
    }

    public LinkedList<Product> reading() {
        LinkedList<Product> minicollection = new LinkedList<>();
        JSONParser parser = new JSONParser();
        JSONObject productsJsonObject;
        JSONArray productJsonArray = null;

        try (FileReader reader = new FileReader(path)) {
            //productsJsonObject = (JSONObject) parser.parse(reader);
            //productJsonArray = (JSONArray) productsJsonObject.get("products");
            productJsonArray = (JSONArray) parser.parse(reader);
        } catch (FileNotFoundException e) {
            System.out.println("Файл с коллекцией не найден.");
        } catch (IOException | NoSuchElementException e) {
            System.out.println(e.toString());
        } catch (ParseException e) {
            System.out.println("Файл не корректен.");
        }

        try {
            if (productJsonArray != null) {
                for (Object it : productJsonArray) {
                    i++;
                    JSONObject productJsonObject = (JSONObject) it;
                    JSONObject coordinatesJSO = (JSONObject) productJsonObject.get("coordinates");
                    int x = 0;
                    int y = 0;
                    if (coordinatesJSO.get("x") == null || coordinatesJSO.get("y") == null || coordinatesJSO.get("x").equals("") || coordinatesJSO.get("y").equals("")) {
                        if (coordinatesJSO.get("x") == null || coordinatesJSO.get("x").equals("")) {
                            x = 0;
                            System.out.println("Product "+ i + "\nЗначение координаты X не верно, присвоено стандартное: X=0");
                        }
                        if (coordinatesJSO.get("y") == null || coordinatesJSO.get("y").equals("")) {
                            y = 0;
                            System.out.println("Product "+ i + "\nЗначение координаты Y не верно, присвоено стандартное: Y=0");
                        }
                    } else {
                        x = (int) (long) coordinatesJSO.get("x");
                        y = (int) (long) coordinatesJSO.get("y");
                    }
                    Coordinates coordinates = new Coordinates(x, y);


                    JSONObject organizationJSO = (JSONObject) productJsonObject.get("manufacturer");
                    long idOfOrg;
                    if (organizationJSO.get("id") == null || organizationJSO.get("id").equals("") || (long) organizationJSO.get("id") <= 0) {
                        idOfOrg = -1;
                    } else {
                        idOfOrg = (long) organizationJSO.get("id");
                    }

                    String nameOfOrg;
                    if (organizationJSO.get("name") == null || organizationJSO.get("name").equals("")) {
                        nameOfOrg = "noname";
                        System.out.println("Product "+ i + "\nИмя организации не верно, присвоено стандартное: noname");
                    } else {
                        nameOfOrg = (String) organizationJSO.get("name");
                    }

                    double annualTurnover;
                    if (organizationJSO.get("annualTurnover") == null || organizationJSO.get("annualTurnover").equals("") || (double) organizationJSO.get("annualTurnover") <= 0) {
                        annualTurnover = 1.0;
                        System.out.println("Product "+ i + "\nЗначение годового оборота  не верно, присвоено стандартное: 1.0");
                    } else {
                        annualTurnover = (double) organizationJSO.get("annualTurnover");
                    }

                    try {
                        String typeString = (String) organizationJSO.get("type");
                        switch (typeString) {
                            case "COMMERCIAL":
                                type = OrganizationType.COMMERCIAL;
                                break;
                            case "PUBLIC":
                                type = OrganizationType.PUBLIC;
                                break;
                            case "GOVERNMENT":
                                type = OrganizationType.GOVERNMENT;
                                break;
                            case "PRIVATE_LIMITED_COMPANY":
                                type = OrganizationType.PRIVATE_LIMITED_COMPANY;
                                break;
                            case "OPEN_JOINT_STOCK_COMPANY":
                                type = OrganizationType.OPEN_JOINT_STOCK_COMPANY;
                                break;
                            default:
                                System.out.println("Product " + i + "\nТип организации задан не верно, мы не можем присвоить ему стандартное значение. Мы не можем добавить этот объект в коллекцию");
                                continue;
                        }
                    }catch (Exception e) {
                        System.out.println("Product " + i + "\nТип организации задан не верно, мы не можем присвоить ему стандартное значение. Мы не можем добавить этот объект в коллекцию");
                        continue;
                    }
                    Organization organization = new Organization(nameOfOrg, annualTurnover, type);
                    organization.setId(idOfOrg);


                    long id;
                    if (productJsonObject.get("id") == null || productJsonObject.get("id").equals("") || (long) productJsonObject.get("id") <= 0) {
                        id = -1;
                    } else {
                        id = (long) productJsonObject.get("id");
                    }

                    String name;
                    try {
                        if (productJsonObject.get("name") == null || productJsonObject.get("name").equals("")) {
                            name = "noname";
                            System.out.println("Product " + i + "\nНазвание продукта не верно, присвоено стандартное: noname");
                        } else {
                            name = (String) productJsonObject.get("name");
                        }
                    }catch (Exception e){
                        System.out.println("don't kill me plz");
                        name = "noname";
                        System.out.println("Product " + i + "\nНазвание продукта не верно, присвоено стандартное: noname");
                    }

                    double price;
                    if (productJsonObject.get("price") == null || productJsonObject.get("price").equals("") || (double) productJsonObject.get("price") <= 0) {
                        price = 1.0;
                        System.out.println("Product "+ i + "\nЗначение цены не верно, присвоено стандартное: 1.0");
                    } else {
                        price = (double) productJsonObject.get("price");
                    }


                    try {
                        String unitOfMeasureString = (String) productJsonObject.get("unitOfMeasure");
                        UnitOfMeasure unitOfMeasure;
                        switch (unitOfMeasureString) {
                            case "KILOGRAMS":
                                unitOfMeasure = UnitOfMeasure.KILOGRAMS;
                                break;
                            case "CENTIMETERS":
                                unitOfMeasure = UnitOfMeasure.CENTIMETERS;
                                break;
                            case "LITERS":
                                unitOfMeasure = UnitOfMeasure.LITERS;
                                break;
                            default:
                                System.out.println("Product " + i + "\nТип единиц измерения задан не верно, мы не можем присвоить ему стандартное значение. Мы не можем добавить этот объект в коллекцию");
                                continue;
                        }
                    }catch (Exception e) {
                        System.out.println("Product " + i + "\nТип единиц измерения задан не верно, мы не можем присвоить ему стандартное значение. Мы не можем добавить этот объект в коллекцию");
                        continue;
                    }

                    Product product = new Product(name, coordinates, price, unitOfMeasure, organization);
                    product.setId(id);
                    try {
                        ZonedDateTime creationDate = ZonedDateTime.parse(productJsonObject.get("creationDate").toString());
                        product.setCreationDate(creationDate);
                    } catch (Exception e) {
                        System.out.println("");
                    }
                    minicollection.add(product);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат числа.");
        } catch (Exception e) {
            System.out.println("were'here?");
        }
        return minicollection;
    }
}
