package shipping.queue;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import shipping.model.Cargo;
import shipping.model.Driver;
import shipping.model.Order;
import shipping.model.Wagon;
import org.apache.log4j.Logger;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

public class Loader {

    private static final Logger logger = Logger.getLogger(Loader.class);
    private Client client;
    private ObjectMapper mapper = new ObjectMapper();

    public Loader() {
        client = Client.create();
    }

    public List<Cargo> getCargoes() {
        String result = getResult("http://localhost:8081/FinalTask_Part1_war_exploded/rest/cargo/all");
        List<Cargo> cargoes = null;
        try {
            cargoes = mapper.readValue(result, new TypeReference<List<Cargo>>() {});
        } catch (IOException e) {
            logger.error("Error while trying to get results from /rest/cargo/all  " + e.getMessage());
        }

        return cargoes;
    }

    public List<Wagon> getWagons() {
        String result = getResult("http://localhost:8081/FinalTask_Part1_war_exploded/rest/wagon/all");
        List<Wagon> wagons = null;
        try {
            wagons = mapper.readValue(result, new TypeReference<List<Wagon>>() {});
        } catch (IOException e) {
            logger.error("Error while trying to get results from /rest/wagon/all " + e.getMessage());
        }

        return wagons;
    }

    public List<Order> getOrders() {
        String result = getResult("http://localhost:8081/FinalTask_Part1_war_exploded/rest/order/all");
        List<Order> orders = null;
        try {
            orders = mapper.readValue(result, new TypeReference<List<Order>>() {});
        } catch (IOException e) {
            logger.error("Error while trying to get results from /rest/order/all  " + e.getMessage());
        }
        return orders;
    }

    public List<Driver> getDrivers() {
        String result = getResult("http://localhost:8081/FinalTask_Part1_war_exploded/rest/driver/all");
        List<Driver> drivers = null;
        try {
            drivers = mapper.readValue(result, new TypeReference<List<Driver>>() {});
        } catch (IOException e) {
            logger.error("Error while trying to get results from /rest/driver/all  " + e.getMessage());
        }

        return drivers;
    }

    private String getResult(String url) {
        WebResource webResource = client.resource(url);
        String response = webResource.accept(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class).getEntity(String.class);
        return response;
    }

}
