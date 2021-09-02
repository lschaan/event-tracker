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
        return this.remainingEvents > Constants.REMAINING_EVENTS_ON_CLOSED_REALMS;
    }

    public boolean isAvailable() {
        return this.players < Constants.AVAILABLE_REALM_PLAYER_AMOUNT_THRESHOLD;
    }

    public boolean isClosing() {
        return this.remainingEvents < Constants.REMAINING_EVENTS_ON_CLOSING_REALM_THRESHOLD;
    }

    public boolean isAlmostClosed() {
        return this.remainingEvents < Constants.REMAINING_EVENTS_ON_ALMOST_CLOSED_REALM_THRESHOLD;
    }

    public boolean isGood() {
        return this.remainingEvents < Constants.REMAINING_EVENTS_ON_GOOD_REALM_THRESHOLD &&
                this.players < Constants.GOOD_REALM_PLAYER_AMOUNT_THRESHOLD;
    }
}
