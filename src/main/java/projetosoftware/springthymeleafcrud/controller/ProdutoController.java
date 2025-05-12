package projetosoftware.springthymeleafcrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import projetosoftware.springthymeleafcrud.model.Produto;
import projetosoftware.springthymeleafcrud.repository.ProdutoRepository;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }
    @GetMapping("/form")
    public String novoForm(Model model){
        model.addAttribute("produto", new Produto());
        return "form";
    }
    @PostMapping("/salvar")
    public @ResponseBody String salvar(@ModelAttribute Produto produto){
        produtoRepository.save(produto);
        return "Salvo!";
    }

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("produtos", produtoRepository.findAll()); //findAll retorna todos itens
        return "Lista";
    }
}
