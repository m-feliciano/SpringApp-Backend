package com.feliciano.demo.dto;

import com.feliciano.demo.resources.domain.Categoria;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CategoriaDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;

    @NotEmpty(message = "Preenchiento obrigatório")
    @Length(min = 5, max = 14, message = "A categoria '${validatedValue}' deve ter entre {min} e {max} characteres")
    private String nome;

    public CategoriaDTO() {
        super();
    }

    public CategoriaDTO(Categoria obj) {
        super();
        id = obj.getId();
        nome = obj.getNome();
    }

    public CategoriaDTO(Integer id, String nome) {
        super();
        this.id = id;
        this.nome = nome;
    }

    /**
     * @return the serialversionuid
     */
    public static long getSerialisation() {
        return serialVersionUID;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

}
