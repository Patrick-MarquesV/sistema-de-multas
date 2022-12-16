package org.mesttra.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Veiculo {

    @Id
    private String placa;

    @Column(nullable = false)
    private Integer ano;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private String marca;

    @OneToOne(mappedBy = "veiculos", cascade = CascadeType.REMOVE)
//    @JoinColumn(name="condutor_nroCnh_fk", referencedColumnName = "nroCnh")
    private Condutor condutor;

    @OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL)
    private List<Multa> multas;

    public Veiculo() {
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Condutor getCondutor() {
        return condutor;
    }

    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
    }

    public List<Multa> getMultas() {
        return multas;
    }

    public void setMultas(List<Multa> multas) {
        this.multas = multas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veiculo veiculo = (Veiculo) o;
        return Objects.equals(placa, veiculo.placa) && Objects.equals(ano, veiculo.ano) && Objects.equals(modelo, veiculo.modelo) && Objects.equals(marca, veiculo.marca) && Objects.equals(condutor, veiculo.condutor) && Objects.equals(multas, veiculo.multas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placa, ano, modelo, marca, condutor, multas);
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "placa='" + placa + '\'' +
                ", ano=" + ano +
                ", modelo='" + modelo + '\'' +
                ", marca='" + marca + '\'' +
                ", condutor=" + condutor.getNroCnh() +
                ", multas=" + multas +
                '}';
    }
}
