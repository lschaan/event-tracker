package com.lschaan.rotmg.eventtracker.api.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RealmListResponse {

    private final List<RealmResponse> realms;
}
