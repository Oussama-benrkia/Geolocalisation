package org.app.backend.controller;

import lombok.RequiredArgsConstructor;
import org.app.backend.dto.RequestLocation;
import org.app.backend.dto.ResponseLocation;
import org.app.backend.service.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/location")
@RequiredArgsConstructor

public class LocationController {
    private final LocationService locationService;
    @PostMapping
    public ResponseEntity<ResponseLocation> saveLocation(@RequestBody RequestLocation requestLocation) {
        ResponseLocation c=locationService.saveLocation(requestLocation);
        return ResponseEntity.ok(c);  // Assuming the save operation does not need to return anything.
    }

    @GetMapping("/last/{vehicleId}")
    public ResponseEntity<ResponseLocation> getLastLocationByVehicleId(@PathVariable Long vehicleId) {
        ResponseLocation responseLocation = locationService.getLastLocationByVehicleId(vehicleId);
        if (responseLocation != null) {
            return ResponseEntity.ok(responseLocation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/range/{vehicleId}")
    public ResponseEntity<List<ResponseLocation>> getLocationByVehicleAndDateRange(
            @RequestParam String start,
            @RequestParam String end,
            @PathVariable Long vehicleId) {
        LocalDateTime startTime = LocalDateTime.parse(start);
        LocalDateTime endTime = LocalDateTime.parse(end);
        List<ResponseLocation> responseLocations = locationService.getLocationByVehicleAndDateRange(vehicleId, startTime, endTime);
        return ResponseEntity.ok(responseLocations);
    }
}
