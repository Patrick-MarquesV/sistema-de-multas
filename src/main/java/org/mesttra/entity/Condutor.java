package org.mesttra.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Condutor {

    @Id
    private long nroCnh;

    @Column(nullable = false)
    private LocalDate dataEmissao;

    @Column(nullable = false)
    private String orgaoEmissor;

    private Integer pontuacao = 0;

    @OneToOne(cascade = CascadeType.REMOVE) //mappedBy = "condutor",
    @JoinColumn(name = "veiculo_placa_fk", referencedColumnName = "placa")
    private Veiculo veiculos;

    public long getNroCnh() {
        return nroCnh;
    }

    public void setNroCnh(long nroCnh) {
        this.nroCnh = nroCnh;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getOrgaoEmissor() {
        return orgaoEmissor;
    }

    public void setOrgaoEmissor(String orgaoEmissor) {
        this.orgaoEmissor = orgaoEmissor;
    }

    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Veiculo getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(Veiculo veiculos) {
        this.veiculos = veiculos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Condutor condutor = (Condutor) o;
        return Objects.equals(nroCnh, condutor.nroCnh) && Objects.equals(dataEmissao, condutor.dataEmissao) && Objects.equals(orgaoEmissor, condutor.orgaoEmissor) && Objects.equals(pontuacao, condutor.pontuacao) && Objects.equals(veiculos, condutor.veiculos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nroCnh, dataEmissao, orgaoEmissor, pontuacao, veiculos);
    }

    @Override
    public String toString() {
        return "Condutor{" +
                "nroCnh='" + nroCnh + '\'' +
                ", dataEmissao=" + dataEmissao +
                ", orgaoEmissor='" + orgaoEmissor + '\'' +
                ", pontuacao=" + pontuacao +
                ", veiculos=" + veiculos +
                '}';
    }
}
