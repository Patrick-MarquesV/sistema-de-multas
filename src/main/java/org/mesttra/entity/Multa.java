package org.mesttra.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Multa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private Integer pontuacao;

    @ManyToOne
    @JoinColumn(name = "veiculo_placa_fk")
    private Veiculo veiculo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Multa multa = (Multa) o;
        return Objects.equals(id, multa.id) && Objects.equals(valor, multa.valor) && Objects.equals(pontuacao, multa.pontuacao) && Objects.equals(veiculo, multa.veiculo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, valor, pontuacao, veiculo);
    }

    @Override
    public String toString() {
        return "Multa{" +
                "id=" + id +
                ", valor=" + valor +
                ", pontuacao=" + pontuacao +
                ", veiculo=" + veiculo.getPlaca() +
                '}';
    }
}
