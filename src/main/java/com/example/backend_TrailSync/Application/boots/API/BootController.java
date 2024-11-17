package com.example.backend_TrailSync.Application.boots.API;

import com.example.backend_TrailSync.Application.boots.Domain.Service.BootService;
import com.example.backend_TrailSync.Application.boots.Mapping.BootMapper;
import com.example.backend_TrailSync.Application.boots.Resource.BootResource;
import com.example.backend_TrailSync.Application.boots.Resource.CreateBootResource;
import com.example.backend_TrailSync.Application.boots.Resource.UpdateBootResource;
import com.example.backend_TrailSync.Application.hiredServices.resource.HiredServiceResource;
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
@Tag(name = "Boot Service", description = "Read, create, update and delete boots by service Id")
@RestController
@RequestMapping("api/v1/boots")
public class BootController {

    private final BootService bootService;
    private final BootMapper mapper;

    public BootController(BootService bootService, BootMapper mapper) {
        this.bootService = bootService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get All Boots", description = "Get all boots by serviceId stored in database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Boots found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BootResource.class))})
    })
    @GetMapping
    public Page<BootResource> getAll(Pageable pageable) {
        return mapper.modelListPage(bootService.getAll(), pageable);
    }

    @Operation(summary = "Get Boot by Id", description = "Get a boot by its Id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Boot found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BootResource.class))})
    })
    @GetMapping("/{bootId}")
    public BootResource getById(@PathVariable Long bootId) {
        return mapper.toResource(bootService.getById(bootId));
    }

    @Operation(summary = "Get Boot by Code", description = "Get a boot by its unique code.")
    @GetMapping("/code/{code}")
    public BootResource getByCode(@PathVariable Long code) {
        return mapper.toResource(bootService.getByCode(code));
    }

    @Operation(summary = "Get Boot by State", description = "Get a boot by its state.")
    @GetMapping("/state/{state}")
    public BootResource getByState(@PathVariable String state) {
        return mapper.toResource(bootService.getByState(state));
    }

    @Operation(summary = "Get Boot by Latitude and Longitude", description = "Get a boot by its coordinates.")
    @GetMapping("/location")
    public BootResource getByLongitudeAndLatitude(@RequestParam Double longitude, @RequestParam Double latitude) {
        return mapper.toResource(bootService.getByLongitudeAndLatitude(longitude, latitude));
    }

    @Operation(summary = "Get Boot by Distance", description = "Get a boot by its distance.")
    @GetMapping("/distance/{distance}")
    public BootResource getByDistance(@PathVariable Double distance) {
        return mapper.toResource(bootService.getByDistance(distance));
    }

    @Operation(summary = "Create a Boot", description = "Create a boot by serviceId and touristId in database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Boot created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BootResource.class))})
    })
    @PostMapping("touristId={touristId}")
    public BootResource createBoot(@PathVariable Long serviceId, @PathVariable Long touristId,
                                   @RequestBody CreateBootResource resource) {
        return mapper.toResource(bootService.create(serviceId, touristId, mapper.toModel(resource)));
    }

    @Operation(summary = "Update a Boot", description = "Update a boot in database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hired service updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UpdateBootResource.class))})
    })
    @PutMapping("touristId={touristId}/{bootId}")
    public BootResource updateBoot(@PathVariable Long bootId, @PathVariable Long touristId,
                                   @PathVariable Long serviceId, @RequestBody UpdateBootResource resource) {
        return mapper.toResource(bootService.update(serviceId, touristId, bootId, mapper.toModel(resource)));
    }

    @Operation(summary = "Delete a Boot", description = "Delete a boot from database.")
    @DeleteMapping("touristId={touristId}/{bootId}")
    public ResponseEntity<?> deleteBoot(@PathVariable Long bootId, @PathVariable Long touristId,
                                        @PathVariable Long serviceId) {
        return bootService.delete(serviceId, touristId, bootId);
    }
}
