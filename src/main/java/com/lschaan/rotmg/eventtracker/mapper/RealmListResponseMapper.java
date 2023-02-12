package com.lschaan.rotmg.eventtracker.mapper;

import com.lschaan.rotmg.eventtracker.api.response.RealmListResponse;
import com.lschaan.rotmg.eventtracker.api.response.RealmResponse;
import com.lschaan.rotmg.eventtracker.dto.RealmDTO;

import java.util.List;
import java.util.stream.Collectors;

public class RealmListResponseMapper {

    public static RealmListResponse map(List<RealmDTO> realmDTOS) {
        List<RealmResponse> realmResponse = realmDTOS.stream()
            .map(RealmResponseMapper::map)
            .collect(Collectors.toList());

        return RealmListResponse.builder().realms(realmResponse).build();
    }
}
