package com.lschaan.rotmg.eventtracker.service;

import com.lschaan.rotmg.eventtracker.client.RealmStockClient;
import com.lschaan.rotmg.eventtracker.dto.RealmDTO;
import com.lschaan.rotmg.eventtracker.mapper.RealmDTOMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EventTrackerService {

    private final RealmStockClient realmStockClient;

    public EventTrackerService(RealmStockClient realmStockClient) {
        this.realmStockClient = realmStockClient;
    }

    public List<RealmDTO> getClosingRealms() {
        List<RealmDTO> realms = getRealms();
        List<RealmDTO> openRealms = getOpenRealms(realms);
        List<RealmDTO> closingRealms = getClosingRealms(openRealms);
        List<RealmDTO> closingAvaliableRealms = getAvailableRealms(closingRealms);

        closingAvaliableRealms.sort(Comparator.comparing(RealmDTO::getPlayers));
        closingAvaliableRealms.sort(Comparator.comparing(RealmDTO::getRemainingEvents));
        return closingAvaliableRealms;
    }

    public List<RealmDTO> getGoodRealms() {
        List<RealmDTO> realms = getRealms();
        List<RealmDTO> openRealms = getOpenRealms(realms);
        List<RealmDTO> closingRealms = getClosingRealms(openRealms);
        List<RealmDTO> goodClosingRealms = getGoodRealms(closingRealms);

        goodClosingRealms.sort(Comparator.comparing(RealmDTO::getPlayers));
        goodClosingRealms.sort(Comparator.comparing(RealmDTO::getRemainingEvents));
        return goodClosingRealms;
    }

    public List<RealmDTO> getClosedRealms() {
        log.info("Getting closed realms.");
        List<RealmDTO> realms = getRealms();

        return realms.stream()
                .filter(realm -> !realm.isOpen())
                .collect(Collectors.toList());
    }


    private List<RealmDTO> getOpenRealms(List<RealmDTO> realms) {
        log.info("Getting open realms.");

        return realms.stream()
                .filter(RealmDTO::isOpen)
                .collect(Collectors.toList());
    }

    private List<RealmDTO> getRealms() {
        log.info("Getting realms.");
        List<RealmDTO> realms = realmStockClient.getEvents()
                .stream()
                .map(RealmDTOMapper::map)
                .collect(Collectors.toList());

        return removeDuplicates(realms);
    }

    private List<RealmDTO> getAvailableRealms(List<RealmDTO> realms) {
        log.info("Filtering for available realms.");

        return realms.stream()
                .filter(RealmDTO::isAvailable)
                .collect(Collectors.toList());
    }

    private List<RealmDTO> getClosingRealms(List<RealmDTO> realms) {
        log.info("Filtering for closing realms.");

        return realms.stream()
                .filter(RealmDTO::isClosing)
                .collect(Collectors.toList());
    }

    private List<RealmDTO> getGoodRealms(List<RealmDTO> realms) {
        log.info("Filtering for good realms.");

        return realms.stream()
                .filter(RealmDTO::isGood)
                .collect(Collectors.toList());
    }

    private List<RealmDTO> removeDuplicates(List<RealmDTO> realms) {
        List<RealmDTO> newRealms = new ArrayList<>();
        realms.stream()
                .filter(realm -> !hasRealm(newRealms, realm))
                .forEach(newRealms::add);
        return newRealms;
    }

    private boolean hasRealm(List<RealmDTO> realms, RealmDTO realmToCheck) {
        return realms.stream()
                .anyMatch(realm -> matches(realm, realmToCheck));
    }

    private boolean matches(RealmDTO realm, RealmDTO realm2) {
        return realm.getName().equals(realm2.getName()) &&
                realm.getServer().equals(realm2.getServer());
    }
}
