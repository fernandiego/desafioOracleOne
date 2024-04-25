package org.example;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CurrencyExchangeApi api = new CurrencyExchangeApi();

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. USD to BRL");
            System.out.println("2. EUR to BRL");
            System.out.println("3. JPY to BRL");
            System.out.println("4. GBP to BRL");
            System.out.println("5. AUD to BRL");
            System.out.println("6. CAD to BRL");
            System.out.println("7. CHF to BRL");
            System.out.println("8. Exit");

            int option = scanner.nextInt();

            if (option == 8) {
                break;
            }

            System.out.print("Enter the amount to convert: ");
            double amount = scanner.nextDouble();

            String baseCurrency;
            switch (option) {
                case 1:
                    baseCurrency = "USD";
                    break;
                case 2:
                    baseCurrency = "EUR";
                    break;
                case 3:
                    baseCurrency = "JPY";
                    break;
                case 4:
                    baseCurrency = "GBP";
                    break;
                case 5:
                    baseCurrency = "AUD";
                    break;
                case 6:
                    baseCurrency = "CAD";
                    break;
                case 7:
                    baseCurrency = "CHF";
                    break;
                default:
                    System.out.println("Invalid option");
                    continue;
            }

            try {
                double rate = api.getExchangeRate(baseCurrency, "BRL");
                double convertedAmount = amount * rate;
                System.out.printf("%.2f %s is equal to %.2f BRL\n", amount, baseCurrency, convertedAmount);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
