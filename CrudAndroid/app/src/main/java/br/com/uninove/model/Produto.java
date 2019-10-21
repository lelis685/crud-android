package br.com.uninove.model;

import java.io.Serializable;

public class Produto  implements Serializable {

    private long id;
    private String nome;
    private double valor;
    private String categoria;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
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
