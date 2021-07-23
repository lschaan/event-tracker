package com.lschaan.rotmg.eventtracker.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Arrays;
import java.util.List;

@Component
public class RealmStockClient {

    private final Client client;

    public RealmStockClient(Client client) {
        this.client = client;
    }

    public List<String> getEvents() {
        return Arrays.asList(client.getEvents().split("\n"));
    }

    @FeignClient(url = "https://realmstock.network/Public", name = "realm-stock")
    public interface Client {

        @GetMapping("/EventHistory")
        String getEvents();
    }
}
