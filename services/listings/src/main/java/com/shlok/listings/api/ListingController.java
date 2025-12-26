package com.shlok.listings.api;

import com.shlok.listings.api.dto.CreateListingRequest;
import com.shlok.listings.api.dto.ListingResponse;
import com.shlok.listings.domain.Listing;
import com.shlok.listings.service.ListingService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/listings")
public class ListingController {

    private final ListingService service;

    public ListingController(ListingService service) {
        this.service = service;
    }

    @PostMapping
    public ListingResponse create(@Valid @RequestBody CreateListingRequest req) {
        return toResponse(service.create(req));
    }

    @PostMapping("/{id}/submit")
    public ListingResponse submit(@PathVariable UUID id) {
        return toResponse(service.submit(id));
    }

    @PostMapping("/{id}/approve")
    public ListingResponse approve(@PathVariable UUID id) {
        return toResponse(service.approve(id));
    }

    private ListingResponse toResponse(Listing l) {
        ListingResponse r = new ListingResponse();
        r.id = l.getId();
        r.title = l.getTitle();
        r.description = l.getDescription();
        r.category = l.getCategory();
        r.status = l.getStatus();
        r.latitude = l.getLatitude();
        r.longitude = l.getLongitude();
        r.createdAt = l.getCreatedAt();
        r.approvedAt = l.getApprovedAt();
        r.expiresAt = l.getExpiresAt();
        return r;
    }
}
