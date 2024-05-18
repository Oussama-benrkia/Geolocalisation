package org.app.backend.rep;

import org.app.backend.model.LocationData;
import org.app.backend.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InrepLocation  extends JpaRepository<LocationData,Long> {
    LocationData findTopByOrderByIdDesc();
    LocationData findTopByVehiculeIdOrderByIdDesc(Long vehiculeId);  // existing method
    List<LocationData> findByVehiculeIdAndDateCrtBetween(Long vehiculeId, LocalDateTime start, LocalDateTime end);
}
