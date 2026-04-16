package com.vinicius.backend.entities;

import com.vinicius.backend.enums.ProjectCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity // Indica que esta classe é uma entidade JPA, ou seja, será mapeada para uma tabela no banco de dados.
@Table(name = "tb_project") // Define o nome da tabela no banco de dados como "tb_project".
@Data // Gera automaticamente os métodos getters, setters, toString, equals e hashCode.
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
    private ProjectCategory category;
    private String tecnologias;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
