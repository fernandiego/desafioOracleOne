package org.example;

import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        CurrencyExchangeApi api = new CurrencyExchangeApi();
        try {
            double rate = api.getExchangeRate("USD", "EUR");
            System.out.println("Exchange rate from USD to EUR: " + rate);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}