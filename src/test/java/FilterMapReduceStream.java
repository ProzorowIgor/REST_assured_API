import java.util.*;
import java.util.stream.Stream;

class Product implements Comparable<Product>{
        int id;
        String name;
        float price;
        public Product(int id, String name, float price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

    @Override
    public int compareTo(Product other) {
        return Float.compare(this.price, other.price);
    }
}
    public class FilterMapReduceStream {
        public static void main(String[] args) {
            List<Product> productsList = new ArrayList<Product>();
            //Adding Products
            productsList.add(new Product(1,"HP Laptop",25000f));
            productsList.add(new Product(2,"Dell Laptop",30000f));
            productsList.add(new Product(3,"Lenevo Laptop",28000f));
            productsList.add(new Product(4,"Sony Laptop",28000f));
            productsList.add(new Product(5,"Apple Laptop",90000f));
            // This is more compact approach for filtering data
           // Collections.max(productsList);
            Float totalPrice = productsList.stream()
                    .map(product->product.price)
                    .reduce(0.0f,(sum, price)->sum+price);   // accumulating price
            System.out.println(totalPrice);
            // More precise code
            float totalPrice2 = productsList.stream()
                    .map(product->product.price)
                    .reduce(0.0f,Float::sum);   // accumulating price, by referring method of Float class
            System.out.println(totalPrice2);

            long count = productsList.stream()
                    .filter(product->product.price<30000)
                    .count(); // counting amount of products with price less than 30000
            System.out.println(count);

            Stream.iterate(1, element->element+1)
                    .map(element->new Random().nextInt(234))
                    .limit(5)
                    .forEach(System.out::println); //simple limited iteration with creating random values

        }
          /*  CONCLUSION:
            map can catch element keep him and convert him
            reduce perform function inside himself
            */
        }

