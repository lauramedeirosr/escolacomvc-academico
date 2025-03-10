package br.senai.sp.escolamvc.api;

import br.senai.sp.escolamvc.model.Aluno;
import br.senai.sp.escolamvc.model.Professor;
import br.senai.sp.escolamvc.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professor")
public class ProfessorRestController {
    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping("/listar")
    public List<Professor> listar() {
        return professorRepository.findAll();
    }

    @PostMapping("/inserir")
    public void Professor(@RequestBody Professor professor) {
        professorRepository.save(professor);
    }

    @PutMapping("/alterar")
    public void alterar(@RequestBody Professor professor) {
        professorRepository.save(professor);
    }

    @DeleteMapping("/excluir/{id}")
    public void excluir(@PathVariable("id") Long id) {
        professorRepository.deleteById(id);
    }

    @GetMapping("/buscar/{nome}")
    public List<Professor> buscar(@PathVariable("nome") String nome) {
        return professorRepository.findAlunoByNomeContaining(nome);
    }

    @GetMapping("/buscar-por-id/{id}")
    public Professor buscarPorId(@PathVariable("id") Long id) {
        return professorRepository.findById(id).get();
    }

    @GetMapping("/buscar-por-cpf/{cpf}")
    public Professor buscarPorCpf(@PathVariable("cpf") String cpf) {
        return professorRepository.findProfessorsByCpf(cpf);
    }

    @PostMapping("/inserir-varios")
    public void inserirVarios(@RequestBody List<Professor> professores) {
        professorRepository.saveAll(professores);
    }
}
