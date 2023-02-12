package com.lschaan.rotmg.eventtracker.api;

import com.lschaan.rotmg.eventtracker.api.response.RealmListResponse;
import com.lschaan.rotmg.eventtracker.mapper.RealmListResponseMapper;
import com.lschaan.rotmg.eventtracker.service.EventTrackerService;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "List realms.")
    private ResponseEntity<RealmListResponse> getRealms() {
        RealmListResponse response = RealmListResponseMapper.map(eventTrackerService.getRealms());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/find/closing")
    @ApiOperation(value = "Finds closing realms", notes = "Finds realms that are somewhat about to close and have at least one open slot, or realms that are very close to closing.\nUseful for O3 and Wine Cellar events.")
    public ResponseEntity<RealmListResponse> getClosingRealms() {
        RealmListResponse response = RealmListResponseMapper.map(eventTrackerService.getClosingRealms());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/find/good")
    @ApiOperation(value = "Finds \"good\" realms.", notes = "Finds realms that have less than 25 rems and less than 15 people.\nUseful for discord raids.")
    public ResponseEntity<RealmListResponse> getGoodRealms() {
        RealmListResponse response = RealmListResponseMapper.map(eventTrackerService.getGoodRealms());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/find/closed")
    @ApiOperation(value = "Finds closed realms.", notes = "Finds realms that have already closed.\nUseful for event that required recently-opened realms.")
    public ResponseEntity<RealmListResponse> getClosedRealms() {
        RealmListResponse response = RealmListResponseMapper.map(eventTrackerService.getClosedRealms());
        return ResponseEntity.ok(response);
    }

}
