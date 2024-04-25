package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


@SuppressWarnings("ALL")
public class CurrencyExchangeApi {
    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";
    private static final String API_KEY = "a52615ccfa34f139a098ad81"; // Substitua pelo seu próprio API key

    private final HttpClient httpClient;
    private final Gson gson;

    public CurrencyExchangeApi() {
        this.httpClient = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    public double getExchangeRate(String baseCurrency, String targetCurrency) throws IOException, InterruptedException {
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(API_URL + baseCurrency))
            .header("X-RapidAPI-Key", API_KEY)
            .build();

    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    if (response.statusCode() != 200) {
        throw new IOException("Failed to fetch data from API: " + response.statusCode());
    }

    String responseBody = response.body();
    Map<String, Object> jsonResponse = parseJsonResponse(responseBody);
    if (jsonResponse == null || !jsonResponse.containsKey("rates")) {
        throw new IOException("Invalid response from API");
    }

    Map<String, Double> rates = (Map<String, Double>) jsonResponse.get("rates");
    if (rates == null || !rates.containsKey(targetCurrency)) {
        throw new IOException("Invalid conversion rates in response");
    }

    return rates.get(targetCurrency);
}



    private Map<String, Object> parseJsonResponse(String responseBody) {
        Gson gson = new Gson();
        return gson.fromJson(responseBody, new TypeToken<Map<String, Object>>() {
        }.getType());
    }
}