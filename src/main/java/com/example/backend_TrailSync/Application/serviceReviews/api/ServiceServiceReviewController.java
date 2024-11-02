package com.example.backend_TrailSync.Application.serviceReviews.api;


import com.example.backend_TrailSync.Application.serviceReviews.domain.service.ServiceReviewService;
import com.example.backend_TrailSync.Application.serviceReviews.mapping.ServiceReviewMapper;
import com.example.backend_TrailSync.Application.serviceReviews.resource.CreateServiceReviewResource;
import com.example.backend_TrailSync.Application.serviceReviews.resource.ServiceReviewResource;
import com.example.backend_TrailSync.Application.serviceReviews.resource.UpdateServiceReviewResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@Tag(name = "Service / Service reviews", description = "Read, create, update and delete service reviews by service Id")
@RestController
@RequestMapping("api/v1/agency/{agencyId}/sagency-reviews")
public class ServiceServiceReviewController {

    private final ServiceReviewService serviceReviewService;

    private final ServiceReviewMapper mapper;

    public ServiceServiceReviewController(ServiceReviewService serviceReviewService, ServiceReviewMapper mapper) {
        this.serviceReviewService = serviceReviewService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get All agency reviews", description = "Get all service reviews by serviceId stored in database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service reviews found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ServiceReviewResource.class))})
    })
    @GetMapping
    public Page<ServiceReviewResource> getAllByAgencyId(@PathVariable Long agencyId, Pageable pageable) {
        return mapper.modelListPage(serviceReviewService.getAllByAgencyId(agencyId), pageable);
    }

    @Operation(summary = "Create a service review", description = "Create a agency review by serviceId and touristId in database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service review created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ServiceReviewResource.class))})
    })
    @PostMapping("touristId={touristId}")
    public ServiceReviewResource createServiceReview(@PathVariable Long agencyId, @PathVariable Long touristId,
                                                   @RequestBody CreateServiceReviewResource resource) {
        return mapper.toResource(serviceReviewService.create(agencyId, touristId, mapper.toModel(resource)));
    }

    @Operation(summary = "Update a service review", description = "Update a service review in database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service review updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ServiceReviewResource.class))})
    })
    @PutMapping("touristId={touristId}/{serviceReviewId}")
    public ServiceReviewResource updateServiceReview(@PathVariable Long serviceReviewId, @PathVariable Long touristId,
                                                   @PathVariable Long agencyId, @RequestBody UpdateServiceReviewResource resource) {
        return mapper.toResource(serviceReviewService.update(agencyId, touristId, serviceReviewId, mapper.toModel(resource)));
    }

    @Operation(summary = "Delete a service review", description = "Delete a service review from database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service review deleted", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("touristId={touristId}/{serviceReviewId}")
    public ResponseEntity<?> deleteServiceReview(@PathVariable Long serviceReviewId, @PathVariable Long touristId,
                                                @PathVariable Long agencyId) {
        return serviceReviewService.delete(agencyId, touristId, serviceReviewId);
    }
}
