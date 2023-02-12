package com.lschaan.rotmg.eventtracker.service;

import com.lschaan.rotmg.eventtracker.client.RealmStockClient;
import com.lschaan.rotmg.eventtracker.dto.RealmDTO;
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

    /*
    Realm has more than 1 remaining event,
    Realm has fewer than 15 remaining events,
    Realm has fewer than 85 players OR fewer than 5 remaining events
     */
    public List<RealmDTO> getClosingRealms() {
        log.info("Getting closing realms.");
        return getRealms()
            .stream()
            .filter(realm -> realm.isOpen() && realm.isClosing() && realm.isAvailableOrAboutAboutToClose())
            .sorted(Comparator.comparing(RealmDTO::getPlayers))
            .sorted(Comparator.comparing(RealmDTO::getRemainingEvents))
            .collect(Collectors.toList());
    }

    /*
    Realm has more than 1 remaining event,
    Realm has fewer than 25 remaining events,
    Realm has fewer than 15 players
    */
    public List<RealmDTO> getGoodRealms() {
        log.info("Getting good realms.");
        return getRealms()
            .stream()
            .filter(realm -> realm.isOpen() && realm.isGood())
            .sorted(Comparator.comparing(RealmDTO::getPlayers))
            .sorted(Comparator.comparing(RealmDTO::getRemainingEvents))
            .collect(Collectors.toList());
    }

    public List<RealmDTO> getClosedRealms() {
        log.info("Getting closed realms.");
        return getRealms()
            .stream()
            .filter(realm -> !realm.isOpen())
            .collect(Collectors.toList());
    }

    public List<RealmDTO> getRealms() {
        log.info("Getting all realms.");
        List<RealmDTO> newRealms = new ArrayList<>();
        realmStockClient.getRealms()
            .stream()
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
