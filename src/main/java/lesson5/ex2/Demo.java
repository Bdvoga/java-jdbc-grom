package lesson5.ex2;

import org.hibernate.Session;

import javax.management.Query;
import java.util.List;

public class Demo {
    public static void main(String[] args) {

        Product product = new Product();
        product.setId(99);
        product.setName("table");
        product.setDescription("grey & blue");
        product.setPrice(70);

        Product updateProduct = new Product();
        updateProduct.setId(99);
        updateProduct.setName("table2");
        updateProduct.setDescription("black & white");
        updateProduct.setPrice(170);

//        System.out.println(ProductRepository.save(product));

//        ProductRepository.delete(99);

        System.out.println(ProductRepository.update(updateProduct));


    }




}
