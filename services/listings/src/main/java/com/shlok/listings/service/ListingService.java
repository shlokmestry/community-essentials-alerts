package com.shlok.listings.service;

import com.shlok.listings.api.dto.CreateListingRequest;
import com.shlok.listings.domain.Listing;
import com.shlok.listings.domain.ListingStatus;
import com.shlok.listings.repo.ListingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class ListingService {

    private final ListingRepository repo;

    public ListingService(ListingRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public Listing create(CreateListingRequest req) {
        Listing l = new Listing();
        l.setTitle(req.title);
        l.setDescription(req.description);
        l.setCategory(req.category);
        l.setLatitude(req.latitude);
        l.setLongitude(req.longitude);
        return repo.save(l);
    }

    @Transactional
    public Listing submit(UUID id) {
        Listing l = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Listing not found"));

        if (l.getStatus() != ListingStatus.DRAFT) {
            throw new IllegalStateException("Only DRAFT listings can be submitted");
        }

        l.setStatus(ListingStatus.SUBMITTED);
        return l;
    }

    @Transactional
    public Listing approve(UUID id) {
        Listing l = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Listing not found"));

        if (l.getStatus() != ListingStatus.SUBMITTED) {
            throw new IllegalStateException("Only SUBMITTED listings can be approved");
        }

        l.setStatus(ListingStatus.APPROVED);
        l.setApprovedAt(Instant.now());
        l.setExpiresAt(Instant.now().plus(7, ChronoUnit.DAYS));

        return l;
    }
}
