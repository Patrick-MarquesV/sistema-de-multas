<h1 align="center">
  💻<br> Sistema de multas 1000Devs
</h1>

Exercício proposto para avaliação dos conhecimentos das bibliotecas do ORM Hibernate e JPA:

<h2> Proposta </h2>
            
            Modele e codifique um sistema que controla multas de trânsito. Nesse sistema estarão presentes as entidades: (mildevs-multas)
            
            Entidades:
            
            
            Condutor(nroCnh, dataEmissao, orgaoEmissor, pontuacao, Veiculo)
            
            Veiculo (placa, ano, modelo, marca, Condutor, List<Multa> multas))
            
            Multa(codigoMulta, valor, pontuacao, Veiculo)
            
            Requisitos:
            
            Relacione as entidades conforme especificado no problema, depois crie um DAO para cada uma delas com as funcionalidades básicas de consulta, listagem, inserção e remoção. Valide os relacionamentos criados pelo Hibernate.
            
            
            - é possível criar um condutor sem um veículo;
            
              - não é possível criar uma multa para um veículo inexistente;
            
              - não é possível criar uma um veículo sem um condutor associado;
            
              - crie a funcionalidade vendaVeiculo, que transfere um veículo de um condutor pro outrol
            
              - é possível listar multas por veículo;
            
              - crie um menu que tem 3 submenus (Condutor, Veículo, Multa) para controlar a manipulação de cada uma das entidades do seu sistema;
            
            
            Resposta em texto:
            
            Pesquise sobre as propriedades fetch, cascade e optional que podem estar presentes nas anotações de relacionamentos. Insira dentro do seu projeto um arquivo txt em que você escreve o seu entendimento sobre elas.

<h2> ✅ Tecnologias utilizadas </h2>

- Java;
- PostgreSQL;

<h2> 📃 Autor</h2>

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/Patrick-MarquesV">
        <img src="https://avatars.githubusercontent.com/u/80074786?v=4" width="100px;" alt="Foto do Patrick Marques no GitHub"/><br>
        <sub>
          <b>Patrick Marques</b>
        </sub>
      </a>
    </td>
  </tr>
</table>
