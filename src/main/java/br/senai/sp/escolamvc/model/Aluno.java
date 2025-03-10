package br.senai.sp.escolamvc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@DiscriminatorValue(value = "A")
@Entity
public class Aluno extends Pessoa{

    @NotEmpty(message = "O campo 'Matrícula' deve ser preenchido.")
    private String matricula;


    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

}

