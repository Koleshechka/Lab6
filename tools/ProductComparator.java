package tools;

import java.util.Comparator;
import product.*;

public class ProductComparator implements Comparator<Product> {
    @Override
    public int compare(Product product1, Product product2) {
        if (product1.getCreationDate().isBefore(product2.getCreationDate())) {
            return -1;
        } else if (product1.getCreationDate().isAfter(product2.getCreationDate())) {
            return 1;
        } else return 0;
    }
}
