package com.vinicius.backend.controllers;

import com.vinicius.backend.entities.Skill;
import com.vinicius.backend.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillController {
    @Autowired
    private SkillService skillService;

    //Get /skills - Listar todos
    @GetMapping
    public ResponseEntity<List> getAllSkills() {
        List<Skill> skills = skillService.getAllSkills();
        return ResponseEntity.ok(skills);
    }

    //Get /skills/{id} - Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Skill> getSkillById(@PathVariable Long id) {
        return skillService.getSkillById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Post /skills - Criar nova skill
    @PostMapping
    public ResponseEntity<Skill> createSkill(@RequestBody Skill skill) {
        Skill createSkill = skillService.createSkill(skill);
        return ResponseEntity.status(HttpStatus.CREATED).body(createSkill);
    }

    //Put /skills/{id} - Atualizar skill
    @PutMapping("/{id}")
    public ResponseEntity<Skill> updateSkill(@PathVariable Long id, @RequestBody Skill skillDetails) {
        Skill updatedSkill = skillService.updateSkill(id,skillDetails);
        return ResponseEntity.ok(updatedSkill);
    }

    //Delete /skills/{id} - deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id);
        return ResponseEntity.noContent().build();
    }
}
