package hibernate.lesson2.ex2;

public class Demo {
    public static void main(String[] args) {

//        System.out.println(ProductDAO.findById(25));

//        System.out.println(ProductDAO.findByName("toy2"));

//        System.out.println(ProductDAO.findByContainedName("toy"));

//        System.out.println(ProductDAO.findByPrice(650, 100));

//        System.out.println(ProductDAO.findByNameSortedAsc("table"));

//        System.out.println(ProductDAO.findByNameSortedDesc("table"));

        System.out.println(ProductDAO.findByPriceSortedDesc(850, 100));
    }
}
