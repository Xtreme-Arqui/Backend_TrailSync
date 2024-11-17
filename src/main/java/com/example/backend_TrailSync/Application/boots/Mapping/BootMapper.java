package com.example.backend_TrailSync.Application.boots.Mapping;

import com.example.backend_TrailSync.Application.boots.Domain.Model.Boot;
import com.example.backend_TrailSync.Application.boots.Resource.BootResource;
import com.example.backend_TrailSync.Application.boots.Resource.CreateBootResource;
import com.example.backend_TrailSync.Application.boots.Resource.UpdateBootResource;
import com.example.backend_TrailSync.Mapping.Mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

//FALTA MODIFICAR
@Component
public class BootMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;
    public BootResource toResource(Boot model) { return mapper.map(model, BootResource.class); }
    public Boot toModel(CreateBootResource resource) { return mapper.map(resource, Boot.class); }
    public Boot toModel(UpdateBootResource resource) { return mapper.map(resource, Boot.class); }
    public Page<BootResource> modelListPage (List<Boot> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, BootResource.class), pageable, modelList.size());
    }

}
