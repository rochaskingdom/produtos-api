package com.vinicius.produtos.api.produto;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;

@ApplicationScoped
@Transactional(REQUIRED)
public class ProdutoService {

    public List<Produto> buscaTodosProdutos() {
        return Produto.listAll();
    }

    public Produto buscaPorId(Long id) {
        return Produto.findById(id);
    }

    public Produto adicionar(@Valid Produto produto) {
        Produto.persist(produto);
        return produto;
    }
}
