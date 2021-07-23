package com.lschaan.rotmg.eventtracker.dto;

import com.lschaan.rotmg.eventtracker.helper.Constants;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class RealmDTO {

    private String name;
    private String server;
    private Integer players;
    private Integer remainingEvents;
    private String time;
    private String uuid;
    private String ip;

    public boolean isOpen() {
        return this.getRemainingEvents() > Constants.REMAINING_EVENTS_ON_CLOSED_REALMS;
    }

    public boolean isAvailable() {
        return this.getPlayers() <= Constants.PLAYER_AMOUNT_THRESHOLD;
    }

    public boolean isClosing() {
        return this.getRemainingEvents() < Constants.REMAINING_EVENTS_ON_CLOSING_REALM_THRESHOLD;
    }
}
