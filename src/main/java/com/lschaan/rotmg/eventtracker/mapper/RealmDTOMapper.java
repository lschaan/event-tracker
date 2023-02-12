package com.lschaan.rotmg.eventtracker.mapper;

import com.lschaan.rotmg.eventtracker.dto.RealmDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealmDTOMapper {

    public static final int IP_POSITION = 0;
    public static final int NAME_POSITION = 1;
    public static final int SERVER_POSITION = 2;
    public static final int PLAYER_AMOUNT_POSITION = 3;
    public static final int REMAINING_EVENTS_POSITION = 4;
    public static final int TIME_POSITION = 5;
    public static final int UUID_POSITION = 6;

    public static final String SPLIT_REGEX = "\\|";

    public static final int DEFAULT_REMAINING_EVENTS = 0;
    public static final int DEFAULT_PLAYER_AMOUNT = 0;

    public static RealmDTO map(String realmString) {
        String[] split = realmString.split(SPLIT_REGEX);
        int playerAmount = getPlayerAmount(split);
        int remainingEvents = getRemainingEvents(split);

        return RealmDTO.builder()
            .ip(split[IP_POSITION])
            .name(split[NAME_POSITION])
            .server(split[SERVER_POSITION])
            .players(playerAmount)
            .remainingEvents(remainingEvents)
            .time(split[TIME_POSITION])
            .uuid(split[UUID_POSITION])
            .build();

    }

    private static int getRemainingEvents(String[] split) {
        try {
            return Integer.parseInt(split[REMAINING_EVENTS_POSITION]);
        } catch (NumberFormatException exception) {
            log.error("Unable to get remaining events for {}", split[REMAINING_EVENTS_POSITION]);
        }
        return DEFAULT_REMAINING_EVENTS;
    }

    private static int getPlayerAmount(String[] split) {
        try {
            return Integer.parseInt(split[PLAYER_AMOUNT_POSITION]);
        } catch (NumberFormatException exception) {
            log.error("Unable to get player amount for {}", split[PLAYER_AMOUNT_POSITION]);
        }
        return DEFAULT_PLAYER_AMOUNT;
    }
}
