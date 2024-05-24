package org.app.backend.controller;

import lombok.RequiredArgsConstructor;
import org.app.backend.dto.RequestLocation;
import org.app.backend.dto.ResponseLocation;
import org.app.backend.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
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
            return ResponseEntity.ok(null);
        }
    }
    @GetMapping("/range/{Matricule}")
    public ResponseEntity<List<ResponseLocation>> getLocationByVehicleAndDateRange(
            @RequestParam String start,
            @RequestParam String end,
            @PathVariable String Matricule) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        List<ResponseLocation> responseLocations = locationService.getLocationByVehicleAndDateRange(Matricule, startDate.atStartOfDay(), endDate.atTime(23, 59, 59));
        return ResponseEntity.ok(responseLocations);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodAr(
            MethodArgumentNotValidException exp
    ){
        var errors=new HashMap<String , String>();
        exp.getBindingResult().getAllErrors()
                .forEach(objectError ->
                {
                    var fieldName=((FieldError)objectError).getField();
                    var errorMes=objectError.getDefaultMessage();
                    errors.put(fieldName,errorMes);

                });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
