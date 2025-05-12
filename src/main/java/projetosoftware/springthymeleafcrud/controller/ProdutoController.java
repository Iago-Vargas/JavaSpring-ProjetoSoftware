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
    public String salvar(@ModelAttribute Produto produto){
        produtoRepository.save(produto);
        return "redirect:/produto/listar";
    }

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("produtos", produtoRepository.findAll()); //findAll retorna todos itens
        return "Lista";
    }

    //Método para editar o produto, recebendo id do produto
    @GetMapping("/editar/{id}")
    public String formEditar(@PathVariable int id, Model model){
        //Criamos um novo objeto do tipo produto com informações do banco e do id passado
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Produto invalido:"+id));
        // Adicionamos o objeto produto criado ao modelo (pagina html)
        model.addAttribute("produto", produto);
        return "form";
    }

    //Metodo para deletar o produto
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable int id){
        //Criamos um novo objeto do tipo produto com informações do banco e do id passado
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Produto invalido:"+id));
        // Adicionamos o objeto produto criado ao modelo (pagina html)
        produtoRepository.delete(produto);
        return "redirect:/produto/listar";
    }
}
