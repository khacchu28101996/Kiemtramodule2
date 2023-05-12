public class Product {
    private final int id;
    private static int INDEX = 1;
    private String code;
    private String name;

    private double price;
    private int quantity;
    private String description;

    public Product(String code, String name, double price, int quantity, String description) {
        this.code = code;
        this.id = INDEX++;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    public int getId() {
        return id;
    }




    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



    public void setDescription(String description) {
        this.description = description;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                '}';
    }

    public String writeFileCsv() {
        return id + "," + code + "," + name + "," + price + "," + quantity + "," + description;
    }
}