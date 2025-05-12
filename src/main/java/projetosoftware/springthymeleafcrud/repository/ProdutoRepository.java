package projetosoftware.springthymeleafcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projetosoftware.springthymeleafcrud.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
