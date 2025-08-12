// Ryo Kato, 200605831

package com.georgiancollege.finalexam_200605831;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class JsonReader {
    public static List<Customer> loadCustomers() throws IOException {
        try (InputStream is = JsonReader.class.getResourceAsStream("/customers.json")) {
            if (is == null) throw new FileNotFoundException("customers.json not found");
            try (Reader r = new InputStreamReader(is, StandardCharsets.UTF_8)) {
                JsonObject root = JsonParser.parseReader(r).getAsJsonObject();
                JsonArray customersArray = root.getAsJsonArray("Customers");
                Type listType = new TypeToken<List<Customer>>() {}.getType();
                return new Gson().fromJson(customersArray, listType);
            }
        }
    }
}