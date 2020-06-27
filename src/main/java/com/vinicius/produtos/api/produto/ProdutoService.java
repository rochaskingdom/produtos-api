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

    public Produto adicionarProduto(@Valid Produto produto) {
        Produto.persist(produto);
        return produto;
    }

    public Produto atualizaProduto(Produto produto) {
        Produto produtoAtualiza = Produto.findById(produto.id);
        produtoAtualiza.setName(produto.getName());
        produtoAtualiza.setPrice(produto.getPrice());
        return produtoAtualiza;
    }

    public void deletaProduto(Long id) {
        Produto produto = Produto.findById(id);
        produto.delete();
    }
}
