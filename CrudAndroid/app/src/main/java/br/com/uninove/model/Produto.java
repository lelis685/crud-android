package br.com.uninove.model;

import java.io.Serializable;

public class Produto  implements Serializable {

    private Long id;
    private String nome;
    private Double valor;
    private String categoria;


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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "nome='" + nome + '\'' +
                ", valor=" + valor;
    }
}
