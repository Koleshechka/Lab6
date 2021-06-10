package product;

import java.util.HashSet;

/**
 * Класс организации.
 * @author Koleshechka
 */
public class Organization {

    private static final HashSet<Long> IDs = new HashSet<>();
    private static long currentID = 1;

    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Double annualTurnover; //Поле может быть null, Значение поля должно быть больше 0
    private OrganizationType type; //Поле не может быть null

    public Organization(String name, Double annualTurnover, OrganizationType type) {
        this.id = -1L;
        this.name = name;
        this.annualTurnover = annualTurnover;
        this.type = type;
    }
    public Long getId() {
        if (id == -1) {
            while (IDs.contains(currentID)) {
                currentID++;
            }
            id = currentID;
            IDs.add(id);
        }
        return id;
    }

    public void setId(Long id) {
        if (IDs.contains(id)) {
            //ignore new id
            return;
        }
        IDs.add(id);
        this.id = id;
    }

    @Override
    public String toString() {
        return " id: " + getId() +
                ",\n name: " + name +
                ",\n annualTurnover: "+annualTurnover+
                ",\n type: "+this.type;

    }
}
