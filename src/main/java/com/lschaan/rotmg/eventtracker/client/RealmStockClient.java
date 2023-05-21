package com.lschaan.rotmg.eventtracker.client;

import com.lschaan.rotmg.eventtracker.client.response.EventHistoryResponse;
import com.lschaan.rotmg.eventtracker.dto.RealmDTO;
import com.lschaan.rotmg.eventtracker.mapper.RealmDTOMapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RealmStockClient {

    private final Client client;

    public RealmStockClient(Client client) {
        this.client = client;
    }

    public List<RealmDTO> getRealms() {
        return Arrays.stream(client.getEvents().getValue().split("\n"))
            .map(RealmDTOMapper::map)
            .distinct()
            .collect(Collectors.toList());
    }

    @FeignClient(url = "https://realmstock.network/Public", name = "realm-stock")
    public interface Client {

        @GetMapping("/EventHistory")
        EventHistoryResponse getEvents();
    }
}
