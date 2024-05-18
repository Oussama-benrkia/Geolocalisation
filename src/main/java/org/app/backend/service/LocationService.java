package org.app.backend.service;

import lombok.RequiredArgsConstructor;
import org.app.backend.dto.RequestLocation;
import org.app.backend.dto.ResponseLocation;
import org.app.backend.model.LocationData;
import org.app.backend.model.Vehicule;
import org.app.backend.rep.InrepLocation;
import org.app.backend.rep.VehiculeRep;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final InrepLocation repLocation;
    private final VehiculeRep repVehicule;

    public ResponseLocation saveLocation(RequestLocation location) {
        Vehicule v = repVehicule.findById(location.getVehicle()).orElse(null);

        if (v != null) {
            LocationData newLocation = LocationData.builder()
                    .x(location.getLatitude())
                    .y(location.getLongitude())
                    .vehicule(v)
                    .build();
            LocationData savedLocation = repLocation.save(newLocation);
            return new ResponseLocation(savedLocation);
        }
        return null;
    }


    public ResponseLocation getLastLocationByVehicleId(Long vehicleId) {
        LocationData locationData = repLocation.findTopByVehiculeIdOrderByIdDesc(vehicleId);
        if (locationData != null) {
            return new ResponseLocation(locationData);
        }
        return null;
    }

    public List<ResponseLocation> getLocationByVehicleAndDateRange(Long vehicleId, LocalDateTime start, LocalDateTime end) {
        List<LocationData> locationDataList = repLocation.findByVehiculeIdAndDateCrtBetween(vehicleId, start, end);
        return locationDataList.stream().map(ResponseLocation::new).collect(Collectors.toList());
    }
}

