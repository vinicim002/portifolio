package com.vinicius.backend.entities;

import com.vinicius.backend.enums.ProjectCategory;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity // Indica que esta classe é uma entidade JPA, ou seja, será mapeada para uma tabela no banco de dados.
@Table(name = "tb_project") // Define o nome da tabela no banco de dados como "tb_project".
@Setter // Gera métodos setters automaticamente.
@Getter // Gera métodos getters automaticamente.
@NoArgsConstructor // Gera um construtor sem argumentos.
@AllArgsConstructor // Gera um construtor com argumentos para todos os campos da classe.
public class Project {
    @Id // Indica que o campo "id" é a chave primária da tabela.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define que o valor da chave primária será gerado automaticamente pelo banco de dados.
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT") // Define que o campo "description" será armazenado como um tipo de dado TEXT no banco de dados.
    private String description;
    private String imageUrl;
    private String githubUrl;
    private String liveUrl;
    @Enumerated(EnumType.STRING) // Define que o campo "category" será armazenado como uma string no banco de dados.
    private ProjectCategory category; // Enum que representa as categorias do projeto, como WEB, MOBILE, DESKTOP, etc.

    @ManyToMany
    @JoinTable(
            name = "tb_project_skill",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> skills = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}