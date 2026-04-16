package com.vinicius.backend.services;

import com.vinicius.backend.entities.Project;
import com.vinicius.backend.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    //Get - Buscar todos os projetos
    public List<Project> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        if (!projects.isEmpty()) {
            return projects;
        }
        throw new RuntimeException("Nenhum projeto encontrado.");
    }

    //Get - Buscar por ID
    public Optional<Project> getProjectById(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
            return project;
        }
        throw new RuntimeException("Projeto não encontrado com ID: " + id);
    }

    //Post - criar novo projeto
    public Project createProject(Project project) {
        if(project.getTitle() == null || project.getTitle().isBlank()) {
            throw new IllegalArgumentException("O título do projeto é obrigatório.");
        }
        if(project.getDescription() == null || project.getDescription().isBlank()) {
            throw new IllegalArgumentException("A descrição do projeto é obrigatória.");
        }
        return projectRepository.save(project);
    }

    //Put - Atualizar projeto
    public Project updateProject(Long id, Project projectDetails) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Projeto não encontrado com ID: " + id));

        // Atualizar apenas os campos que vieram
        if (projectDetails.getTitle() != null) {
            project.setTitle(projectDetails.getTitle());
        }
        if (projectDetails.getDescription() != null) {
            project.setDescription(projectDetails.getDescription());
        }
        if (projectDetails.getCategory() != null) {
            project.setCategory(projectDetails.getCategory());
        }
        if (projectDetails.getImageUrl() != null) {
            project.setImageUrl(projectDetails.getImageUrl());
        }
        if (projectDetails.getGithubUrl() != null) {
            project.setGithubUrl(projectDetails.getGithubUrl());
        }
        if (projectDetails.getLiveUrl() != null) {
            project.setLiveUrl(projectDetails.getLiveUrl());
        }
        if (projectDetails.getSkills() != null && !projectDetails.getSkills().isEmpty()) {
            project.setSkills(projectDetails.getSkills());
        }
        return projectRepository.save(project);
    }

    //Delete - Deletar projeto
    public void deleteProject(Long id) {
        if(!projectRepository.existsById(id)) {
            throw new RuntimeException("Projeto não encontrado com ID: " + id);
        }
        projectRepository.deleteById(id);
    }
}
