/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.armando.prj_clinicahospitalarfx.model;

import java.util.Date;

/**
 *
 * @author Armando
 */
public class DadoPessoal {

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public ContatoTelEmail getContato() {
        return contato;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setContato(ContatoTelEmail contato) {
        this.contato = contato;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    //Construtor
    public DadoPessoal(String nomeCompleto, Date dataNascimento, Endereco endereco, ContatoTelEmail contato, Genero genero) {
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.contato = contato;
        this.genero = genero;
    }
    
    
    private String nomeCompleto;
    private Date dataNascimento;
    private Endereco endereco;
    private ContatoTelEmail contato;
    private Genero genero;
       
    
}
