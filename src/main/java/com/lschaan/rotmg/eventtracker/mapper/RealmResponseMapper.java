package com.lschaan.rotmg.eventtracker.mapper;

import com.lschaan.rotmg.eventtracker.api.response.RealmResponse;
import com.lschaan.rotmg.eventtracker.dto.RealmDTO;

public class RealmResponseMapper {

    public static RealmResponse map(final RealmDTO realmDTO) {
        return RealmResponse.builder()
                .name(realmDTO.getName())
                .players(realmDTO.getPlayers())
                .remainingEvents(realmDTO.getRemainingEvents())
                .server(realmDTO.getServer())
                .build();
    }
}
