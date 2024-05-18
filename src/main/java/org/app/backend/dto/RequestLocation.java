package org.app.backend.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestLocation {
    @NotEmpty
    double latitude;
    @NotEmpty
    double longitude;
    @NotEmpty
    Long Vehicle;
}
