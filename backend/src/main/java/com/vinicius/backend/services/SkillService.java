package com.vinicius.backend.services;

import com.vinicius.backend.entities.Skill;
import com.vinicius.backend.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillService {
    @Autowired
    private SkillRepository skillRepository;

    //Get - Buscar todos as skills
    public List<Skill> getAllSkills() {
        List<Skill> skills = skillRepository.findAll();
        if (!skills.isEmpty()) {
            return skills;
        }
        throw new RuntimeException("Nenhuma skill encontrada.");
    }

    //Get - Buscar por ID
    public Optional<Skill> getSkillById(Long id) {
        Optional<Skill> skill = skillRepository.findById(id);
        if (skill.isPresent()) {
            return skill;
        }
        throw new RuntimeException("Skill não encontrada com ID: " + id);
    }

    //Post - criar nova skill
    public Skill createSkill(Skill skill) {
        if(skill.getName() == null || skill.getName().isBlank()) {
            throw new IllegalArgumentException("O nome da skill é obrigatório.");
        }
        if(skill.getCategory() == null) {
            throw new IllegalArgumentException("A categoria da skill é obrigatória.");
        }
        return skillRepository.save(skill);
    }

    //Put - Atualizar skill
    public Skill updateSkill(Long id, Skill skillDetails) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill não encontrada com ID: " + id));

        if (skillDetails.getName() != null) {
            skill.setName(skillDetails.getName());
        }
        if (skillDetails.getCategory() != null) {
            skill.setCategory(skillDetails.getCategory());
        }

        return skillRepository.save(skill);
    }

    //Delete - Deletar skill
    public void deleteSkill(Long id) {
        if(!skillRepository.existsById(id)) {
            throw new RuntimeException("Skill não encontrada com ID: " + id);
        }
        skillRepository.deleteById(id);
    }
}
