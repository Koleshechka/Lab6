package product;

/**
 * Класс координат.
 * @author Koleshechka
 */
public class Coordinates {
    private int x;
    private int y;

    public Coordinates (int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return " x: " + x +
                ",\n y: " + y;
    }
}
