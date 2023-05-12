import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class ProductManage {
    private final ArrayList<Product> products;
    private final Scanner scanner;

    public ProductManage() {
        products = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void displayAll() {
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public void addProduct() {
        System.out.println("Nhập vào mã sản phẩm");
        String code = scanner.nextLine();
        System.out.println("Nhập vào tên sản phẩm");
        String name = scanner.nextLine();
        double price = getValidPrice();
        int quantity = getValidQuantity();
        System.out.println("Nhập vào mô tả của sản phẩm");
        String description = scanner.nextLine();
        Product product = new Product(code, name, price, quantity, description);
        products.add(product);
        System.out.println("Thêm sản phẩm thành công !!");
    }

    private double getValidPrice() {
        double price;
        do {
            try {
                System.out.println("Nhập vào giá sản phẩm");
                price = Double.parseDouble(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
        return price;
    }

    private int getValidQuantity() {
        int quantity;
        do {
            try {
                System.out.println("Nhập vào số lượng");
                quantity = Integer.parseInt(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
        return quantity;
    }

    public Product getProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) return product;
        }
        return null;
    }

    public void updateProduct() {
        displayAll();
        int id = 0;
        try {
            System.out.println("Chọn id sản phẩm bạn muốn sửa");
            id = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Product product = getProductById(id);
        if (product != null) {
            System.out.println("Nhập vào mã sản phẩm");
            String code = scanner.nextLine();
            System.out.println("Nhập vào tên sản phẩm");
            String name = scanner.nextLine();
            double price = getValidPrice();
            int quantity = getValidQuantity();
            System.out.println("Nhập vào mô tả của sản phẩm");
            product.setCode(code);
            product.setName(name);
            product.setPrice(price);
            product.setQuantity(quantity);
            product.setDescription(scanner.nextLine());
            System.out.println("Sửa sản phẩm thành công !!");
        } else {
            System.out.println("Không tìm đc sản phẩm vs mã sản phẩm trên ");
            updateProduct();
        }
    }

    public void deleteById() {
        displayAll();
        System.out.println("Chọn id sản phẩm bạn muốn xóa");
        int id = Integer.parseInt(scanner.nextLine());
        Product product = getProductById(id);
        if (product != null) {
            System.out.println("Bạn có chắc chắn muốn xóa ? Y/N");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("Y")) {
                products.remove(product);
            }
        }
    }

    public void sortByPriceIncrease() {
        products.sort(Comparator.comparingDouble(Product::getPrice));
    }

    public void sortByPriceDecrease() {
        products.sort((Product o1, Product o2) -> {
            if (o1.getPrice() < o2.getPrice()) return 1;
            else if (o1.getPrice() > o2.getPrice()) return -1;
            else return 0;
        });
    }

    public void searchByMaxPrice() {
        double maxPrice = products.get(0).getPrice();
        for (Product product : products) {
            if (product.getPrice() > maxPrice) {
                maxPrice = product.getPrice();
            }
        }
        for (Product product : products) {
            if (product.getPrice() == maxPrice) {
                System.out.println(product);
            }
        }
    }

    public void readFileCsv() {
        File file = new File("C:\\Users\\Admin BVCN88 02\\IdeaProjects\\TestModule2\\src\\product.csv");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                products.add(new Product(data[0], data[1],
                        Double.parseDouble(data[2]),
                        Integer.parseInt(data[3]), data[4]));
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeFileCsv() {
        File file = new File("C:\\Users\\Admin BVCN88 02\\IdeaProjects\\TestModule2\\src\\product.csv");
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            for (Product product : products) {
                bufferedWriter.write(product.writeFileCsv() + "\n");
            }
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
