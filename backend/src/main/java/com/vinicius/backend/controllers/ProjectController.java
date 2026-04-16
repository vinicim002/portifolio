package com.vinicius.backend.controllers;

import com.vinicius.backend.entities.Project;
import com.vinicius.backend.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    //Get /projects - Listar todos
    @GetMapping
    public ResponseEntity<List> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    //Get /projects/{id} - Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Post /projects - Criar novo projeto
    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project createProject = projectService.createProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(createProject);
    }

    //Put /projects/{id} - Atualizar projeto
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project projectDetails) {
        Project updatedProject = projectService.updateProject(id,projectDetails);
        return ResponseEntity.ok(updatedProject);
    }

    //Delete /projects/{id} - deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
