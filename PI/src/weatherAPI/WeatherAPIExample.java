package weatherAPI;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;


public class WeatherAPIExample {

    public static void main(String[] args) {
        try {
            String apiUrl = "http://api.weatherapi.com/v1/current.json?key=7035064ddd13403581800106232510&q=" + "location";

            // Create a URL object
            URL url = new URL(apiUrl);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to GET
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Create a BufferedReader to read the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                // Read the response line by line
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                // Close the reader
                reader.close();

                // Parse the JSON response
                JSONObject jsonResponse = new JSONObject(response.toString());

                // Extract the current weather data
                JSONObject current = jsonResponse.getJSONObject("current");
                String location = jsonResponse.getJSONObject("location").getString("name");
                String condition = current.getJSONObject("condition").getString("text");
                double temperatureCelsius = current.getDouble("temp_c");
                double temperatureFahrenheit = current.getDouble("temp_f");

                // Print the weather information
                System.out.println("Location: " + location);
                System.out.println("Condition: " + condition);
                System.out.println("Temperature (Celsius): " + temperatureCelsius + "°C");
                System.out.println("Temperature (Fahrenheit): " + temperatureFahrenheit + "°F");
            } else {
                System.out.println("Request failed with response code: " + responseCode);
            }

            // Close the connection
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
