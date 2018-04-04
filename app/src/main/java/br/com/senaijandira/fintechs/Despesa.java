package br.com.senaijandira.fintechs;

import java.security.Identity;
import java.util.Date;

/**
 * Created by 17170118 on 14/03/2018.
 */

public class Despesa {

    private Integer id;
    private String descricao;
    private float valor;
    private Date data;
    private String conta;
    private String categoria;
    private float valor_geral;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public float getValor_geral() {
        return valor_geral;
    }

    public void setValor_geral(float valot_geral) {
        this.valor_geral = valot_geral;
    }
}
