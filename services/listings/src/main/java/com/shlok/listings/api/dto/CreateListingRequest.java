package com.shlok.listings.api.dto;

import com.shlok.listings.domain.ListingCategory;
import jakarta.validation.constraints.*;

public class CreateListingRequest {

    @NotBlank
    @Size(max = 120)
    public String title;

    @NotBlank
    @Size(max = 2000)
    public String description;

    @NotNull
    public ListingCategory category;

    @DecimalMin("-90.0") @DecimalMax("90.0")
    public double latitude;

    @DecimalMin("-180.0") @DecimalMax("180.0")
    public double longitude;
}
