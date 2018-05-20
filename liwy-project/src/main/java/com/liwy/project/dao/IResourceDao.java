package com.liwy.project.dao;

import java.util.List;

import com.liwy.project.entity.Resource;

public interface IResourceDao {

    public Resource createResource(Resource resource);
    public Resource updateResource(Resource resource);
    public void deleteResource(Long resourceId);

    Resource findOne(Long resourceId);
    List<Resource> findAll();

}