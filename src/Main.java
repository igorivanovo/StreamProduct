import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int productNumber;
        int productCount;
        String[] products = {"Хлеб", "Яблоки", "Молоко"};
        int[] prices = {100, 200, 300};
        System.out.println(
                "Список возможных товаров для покупки\n");

        for (int i = 0; i < products.length; i++) {
            System.out.printf(
                    (i + 1) + ".  %S  %d руб/шт\n", products[i], prices[i]);
        }
        int sumProducts = 0;
        int[] quantityProductCount = new int[3];
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите товар и количество или введите `end`");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                break;
            }
            String[] parts = input.split(" ");

            if (parts.length != 2) {
                System.out.println(" Ввод должен состоять из двух частей через пробел");
                continue;
            }

            try {
                productNumber = Integer.parseInt(parts[0]) - 1;

                productCount = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                System.out.println(" ошибка вводите только числа");
                continue;
            }

            if (productNumber > 2 || productNumber < 0 || productCount < 0) {
                System.out.println(" Не корректный ввод номера или количества товара");
                continue;
            }

            int currentPrice = prices[productNumber];
            sumProducts = sumProducts + currentPrice * productCount;
            quantityProductCount[productNumber] = quantityProductCount[productNumber] + productCount;
        }
        System.out.println("Ваша корзина:");
        for (int i = 0; i < products.length; i++) {
            if (quantityProductCount[i] != 0) {
                System.out.printf(
                        "%s  %dшт  %d руб/шт  %d руб в сумме\n",
                        products[i], quantityProductCount[i], prices[i], prices[i] * quantityProductCount[i]
                );
            }
        }
        System.out.println("Итого " + sumProducts + " руб.");


    }
}
