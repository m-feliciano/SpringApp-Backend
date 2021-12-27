package com.feliciano.demo.repositories;

import com.feliciano.demo.resources.domain.Categoria;
import com.feliciano.demo.resources.domain.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

//	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
//	Page<Produto> search(@Param("nome") String nome, 
//			@Param("categorias") List<Categoria> categorias, 
//			Pageable pageRequest);

    Page<Produto> findDistinctByNomeContainingAndCategoriasIn(@Param("nome") String nome,
                                                              @Param("categorias") List<Categoria> categorias, Pageable pageRequest);

}
