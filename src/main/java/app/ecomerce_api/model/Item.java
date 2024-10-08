package app.ecomerce_api.model;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonView;

import app.ecomerce_api.config.View;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity(name = "item_produto")
public class Item implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonView({View.CartView.class, View.UserWithCart.class,View.ItemView.class})
    private String nome;

    @JsonView({View.CartView.class, View.UserWithCart.class,View.ItemView.class})
    private String descricao;

    @JsonView({View.CartView.class, View.UserWithCart.class,View.ItemView.class})
    private Double preco;

    @JsonView({View.ItemView.class})
    private Integer quantidadeDisponivel;


   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(Integer quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }


}
