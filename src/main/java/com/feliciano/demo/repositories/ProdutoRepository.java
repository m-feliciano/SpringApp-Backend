package com.feliciano.demo.repositories;

import com.feliciano.demo.resources.domain.Categoria;
import com.feliciano.demo.resources.domain.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, Collection<List<Categoria>> categorias, Pageable pageable);

}
