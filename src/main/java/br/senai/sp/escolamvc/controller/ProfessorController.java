package br.senai.sp.escolamvc.controller;

import br.senai.sp.escolamvc.model.Aluno;
import br.senai.sp.escolamvc.model.Professor;
import br.senai.sp.escolamvc.repository.ProfessorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("professor", new Professor());
        return "professor/inserir";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Professor professor, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {

            if(professor.getId() != null) {
                return "redirect:/professor";
            }

            return "aluno/inserir";
        }
        professorRepository.save(professor);
        attributes.addFlashAttribute("mensagem", "Professor salvo com sucesso!");
        return "redirect:/professor";
    }


    @PostMapping("/buscar")
    public String buscar(Model model, @Param("nome") String nome){
        if(nome == null ){
            return "/professor/listagem";
        }

        List<Professor> professoresBuscados = professorRepository.findProfessorByNomeContaining(nome);
        model.addAttribute("professores", professoresBuscados);
        return "/professor/listagem";
    }

    @GetMapping
    public String listar(Model model){
        model.addAttribute("professores", professorRepository.findAll());
        return "/professor/listagem";
    }

    @GetMapping("/alterar/{id}")
    public String alterar(@PathVariable("id") Long id, Model model) {

        // Busca o aluno no banco de dados
        Professor professor = professorRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("ID inválido"));

        // Adiciona o aluno no objeto model para ser carregado no formulário
        model.addAttribute("professor", professor);

        // Retorna o template professor/alterar.html
        return "professor/alterar";
    }


    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id,
                          RedirectAttributes attributes) {

        // Busca o aluno no banco de dados
        Professor professor = professorRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("ID inválido"));

        // Exclui o aluno do banco de dados
        professorRepository.delete(professor);

        // Adiciona uma mensagem que será exibida no template
        attributes.addFlashAttribute("mensagem",
                "Professor excluído com sucesso!");

        // Redireciona para a página de listagem de alunos
        return "redirect:/professor";
    }
}

