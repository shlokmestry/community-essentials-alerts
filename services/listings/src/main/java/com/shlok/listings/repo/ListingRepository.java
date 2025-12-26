package com.shlok.listings.repo;

import com.shlok.listings.domain.Listing;
import com.shlok.listings.domain.ListingCategory;
import com.shlok.listings.domain.ListingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ListingRepository extends JpaRepository<Listing, UUID> {

    List<Listing> findByStatus(ListingStatus status);

    List<Listing> findByStatusAndCategory(ListingStatus status, ListingCategory category);
}
