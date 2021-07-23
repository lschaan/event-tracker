package com.lschaan.rotmg.eventtracker.api;

import com.lschaan.rotmg.eventtracker.api.response.RealmListResponse;
import com.lschaan.rotmg.eventtracker.mapper.RealmListResponseMapper;
import com.lschaan.rotmg.eventtracker.service.EventTrackerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/realm")
public class EventTrackerController {

    private final EventTrackerService eventTrackerService;

    public EventTrackerController(EventTrackerService eventTrackerService) {
        this.eventTrackerService = eventTrackerService;
    }

    @GetMapping("/find")
    public ResponseEntity<RealmListResponse> getRealms() {
        RealmListResponse response = RealmListResponseMapper.map(eventTrackerService.getClosingAvaliableRealms());
        return ResponseEntity.ok(response);
    }


}
