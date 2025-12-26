package com.shlok.listings.api.dto;

import com.shlok.listings.domain.ListingCategory;
import com.shlok.listings.domain.ListingStatus;

import java.time.Instant;
import java.util.UUID;

public class ListingResponse {
    public UUID id;
    public String title;
    public String description;
    public ListingCategory category;
    public ListingStatus status;
    public double latitude;
    public double longitude;
    public Instant createdAt;
    public Instant approvedAt;
    public Instant expiresAt;
}
