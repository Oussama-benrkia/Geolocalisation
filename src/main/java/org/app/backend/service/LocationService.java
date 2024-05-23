package org.app.backend.service;

import lombok.RequiredArgsConstructor;
import org.app.backend.dto.RequestLocation;
import org.app.backend.dto.ResponseLocation;
import org.app.backend.model.LocationData;
import org.app.backend.model.Vehicule;
import org.app.backend.model.enumm.Status;
import org.app.backend.rep.InrepLocation;
import org.app.backend.rep.VehiculeRep;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final InrepLocation repLocation;
    private final VehiculeRep repVehicule;

    private Status getStatus(String input) {
        for (Status status : Status.values()) {
            if (status.name().equalsIgnoreCase(input)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No enum constant found for input: " + input);
    }
    public ResponseLocation saveLocation(RequestLocation location) {
        if (location.getCar() == null) {
            throw new IllegalArgumentException("Vehicle ID cannot be null.");
        }

        Vehicule v = repVehicule.findById(location.getCar()).orElse(null);
        if (v!=null) {
            if (!location.getStatus().isEmpty()) {
                v.setStatus(getStatus(location.getStatus()));
            }
            if (!location.getEtat().isEmpty()) {
                v.setEtat(location.getEtat().equals("1"));
            }
            LocationData newLocation = LocationData.builder()
                    .x(location.getLatitude())
                    .y(location.getLongitude())
                    .vehicule(v)
                    .build();
            repVehicule.save(v); // Save changes to vehicle
            LocationData savedLocation = repLocation.save(newLocation);
            return new ResponseLocation(savedLocation);
        } else {
            throw new IllegalArgumentException("Vehicle with ID " + location.getCar() + " not found.");
        }
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

