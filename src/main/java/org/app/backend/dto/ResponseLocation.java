package org.app.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.app.backend.model.LocationData;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseLocation {
    Double latitude;
    Double longitude;
    Long vehicule ;
    LocalDateTime dateTime;
    public ResponseLocation(LocationData locationData) {
        this.dateTime = locationData.getDateCrt();
        this.latitude = locationData.getX();
        this.longitude = locationData.getY();
        this.vehicule = locationData.getVehicule().getId(); // Ensure that you are handling potential nulls appropriately.
    }
}
