package br.senai.sp.escolamvc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.br.CPF;

import java.sql.Date;
import java.time.Instant;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "tipo",
        length = 1,
        discriminatorType = DiscriminatorType.STRING
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O campo 'Nome' deve ser preenchido.")
    private String nome;

    @NotEmpty(message = "O campo 'CPF' deve ser preenchido.")
    @CPF(message = "O 'CPF' informado deve ser válido.")
    private String cpf;

    @NotEmpty(message = "O campo 'Email' deve ser preenchido")
    @Email(message = "O campo 'Email' deve ser válido")
    private String email;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @CreationTimestamp(source = SourceType.DB)
    private Instant dataCadastro;

    @UpdateTimestamp(source = SourceType.DB)
    private Instant dataAtualizacao;

    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;
}
