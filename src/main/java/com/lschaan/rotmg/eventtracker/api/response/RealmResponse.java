package com.lschaan.rotmg.eventtracker.api.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RealmResponse {

    private final String name;
    private final String server;
    private final Integer players;
    private final Integer remainingEvents;
}
