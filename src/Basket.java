import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Basket {
    protected int[] prices;
    protected String[] products;
    protected int sumProducts;
    Map<String, Integer> map;

    protected Basket(int[] prices, String[] products) {
        this.prices = prices;
        this.products = products;
        map = new HashMap<>();
    }


    protected void addToCart(int productNum, int amount) {//добавление в корзину
        String k = products[productNum];
        if (map.isEmpty()) {
            map.put(products[productNum], amount);
        } else if (map.containsKey(k)) {
            int v = map.get(k) + amount;
            map.put(products[productNum], v);
        } else {
            map.put(products[productNum], amount);
        }
    }

    protected void printCart() {//вывод корзины
        System.out.println("  <<<<<  Ваша корзина:  >>>>>");

        for (String k : map.keySet()) {
            int v = map.get(k);
            for (int j = 0; j < products.length; j++) {
                if (k.equals(products[j])) {
                    System.out.printf(
                            "%s  %dшт  %d руб/шт  %d руб в сумме\n",
                            k, v, prices[j], prices[j] * v);
                    sumProducts += (prices[j] * v);
                    break;
                }
            }
        }
        System.out.println("Итого " + sumProducts + " руб.");
        System.out.println();
        sumProducts = 0;

    }

    protected void saveTxt(File textFile) throws IOException {
        try (PrintWriter out = new PrintWriter(textFile);) {
            for (Map.Entry<String, Integer> kv : map.entrySet()) {
                String text = kv.getKey() + " " + kv.getValue();
                out.write(text);
                out.write("\n");
                out.flush();
            }
        }
    }

    protected static Map<String, Integer> loadFromTxtFile(String textFile) {
        String s1 = "";
        try (BufferedReader br = new BufferedReader(new FileReader("basket.txt"))) {
            //чтение построчно
            String s;
            Map<String, Integer> map1 = new HashMap<>();
            while ((s = br.readLine()) != null) {
                String[] parts = s.split(" ");
                String product = parts[0];
                int count = Integer.parseInt(parts[1]);
                map1.put(product, count);

            }
            return map1;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

}
