package org.mesttra.service;

import jakarta.persistence.*;
import org.mesttra.dao.CondutorDao;
import org.mesttra.dao.MultaDao;
import org.mesttra.dao.VeiculoDao;
import org.mesttra.entity.Condutor;
import org.mesttra.entity.Multa;
import org.mesttra.entity.Veiculo;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Menu {

    public static final int exitMenuOption = 4;
    private static Scanner input = new Scanner(System.in);

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("mildevs");

    private static CondutorDao condutorDao = new CondutorDao(factory);
    private static VeiculoDao veiculoDao = new VeiculoDao(factory);
    private static MultaDao multaDao = new MultaDao(factory);

    public static int menuPrincipal() {
        System.out.println("==== Menu Principal ====");
        System.out.println();
        System.out.println("1 - Condutor");
        System.out.println("2 - Veiculo");
        System.out.println("3 - Multa");
        System.out.println(exitMenuOption + " - Sair");
        System.out.println();
        System.out.println("==========================");
        System.out.println();

        System.out.print("Digite a opção desejada: ");

        int entrada = entradaInteiro();

        opcaoMenuPrincipal(entrada);

        return entrada;

    }

    private static void opcaoMenuPrincipal(int entrada) {
        switch (entrada) {
            case 1 -> menuCondutor();
            case 2 -> menuVeiculo();
            case 3 -> menuMulta();
            case exitMenuOption -> System.out.println("Obrigado por utilizar nosso sistema!");
            default -> System.out.println("Opção inserida inválida!");
        }
        pressEnterToContinue();
        limpaConsole();
    }

    private static void menuCondutor() {
        System.out.println("==== Menu Condutor ====");
        System.out.println();
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Consultar");
        System.out.println("3 - Listar todos");
        System.out.println("4 - Remover");
        System.out.println("5 - Voltar");
        System.out.println();
        System.out.println("==========================");
        System.out.println();

        System.out.print("Digite a opção desejada: ");

        int entrada = entradaInteiro();

        opcaoMenuCondutor(entrada);

    }

    private static void opcaoMenuCondutor(int entrada) {
        switch (entrada) {
            case 1 -> cadastrarCondutor();
            case 2 -> consultarCondutor();
            case 3 -> listarCondutores();
            case 4 -> removerCondutor();
            case 5 -> System.out.println("Retornando para o menu principal");
            default -> System.out.println("Opção inserida inválida!");
        }

    }

    private static void cadastrarCondutor() {

        Condutor novoCondutor = new Condutor();

        System.out.println("==== Cadastrar Condutor ====");
        System.out.println("Digite os dados cadastrais abaixo:");
        System.out.print("Número da CNH: ");
        long nroCnh = entradaLong();

        Condutor condutor = condutorDao.findByNroCnh(nroCnh);

        if (condutor != null) {
            System.err.println("Condutor já cadastrado! CNH: " + nroCnh);
            return;
        }

        novoCondutor.setNroCnh(nroCnh);

        System.out.print("Data de emissão(yyyy-mm-dd): ");

        LocalDate dataEmissao;

        try {
            dataEmissao = LocalDate.parse(entradaString());
        } catch (DateTimeParseException e) {
            System.err.println("Data informada incorreta, usar o padrão yyyy-mm-dd");
            System.err.println("Condutor não cadastrado, retornando para o menu inicial!");
            return;
        }

        novoCondutor.setDataEmissao(dataEmissao);

        System.out.print("Orgão emissor: ");
        String orgaoEmissor = entradaString();
        novoCondutor.setOrgaoEmissor(orgaoEmissor);

        System.out.print("Deseja cadastrar um veiculo?(s/n): ");
        String opcao = entradaString();

        if (opcao.equalsIgnoreCase("s")) {
            System.out.print("Digite a placa do veiculo: ");
            String placa = entradaString();

            Veiculo veiculo;

            try {
                veiculo = veiculoDao.findByPlaca(placa)
                        .orElseThrow(RuntimeException::new);
            } catch (RuntimeException e) {
                System.err.println("Condutor não cadastrado, veiculo não encontrado! Placa: " + placa);
                return;
            }

            novoCondutor.setVeiculos(veiculo);

        }

        condutorDao.create(novoCondutor);

        System.out.println("Condutor Cadastrado!");

    }

    private static void consultarCondutor() {
        System.out.println("==== Consultar Condutor ====");
        System.out.print("Digite o número da CNH: ");
        long nroCnh = entradaLong();

        Condutor condutorConsultado = condutorDao.findByNroCnh(nroCnh);

        if (condutorConsultado != null) {
            System.out.println(condutorConsultado);
        } else {
            System.err.println("Condutor não encontrado! CNH: " + nroCnh);
        }

    }

    private static void listarCondutores() {
        System.out.println("==== Listar Condutores ====");
        List<Condutor> condutores = condutorDao.findAll();

        condutores.forEach(System.out::println);

    }

    private static void removerCondutor() {
        System.out.println("==== Remover Condutor ====");
        System.out.print("Digite o número da CNH: ");
        long nroCnh = entradaLong();

        boolean condutorExiste = condutorDao.delete(nroCnh);

        if (condutorExiste) {
            System.out.print("Condutor Removido!");
        } else {
            System.err.println("Condutor não existe! CNH: " + nroCnh);
        }
    }

    private static void menuVeiculo() {
        System.out.println("==== Menu Veiculo ====");
        System.out.println();
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Consultar");
        System.out.println("3 - Listar todos");
        System.out.println("4 - Remover");
        System.out.println("5 - Vender/Transferir");
        System.out.println("6 - Voltar");
        System.out.println();
        System.out.println("==========================");
        System.out.println();

        System.out.print("Digite a opção desejada: ");

        int entrada = entradaInteiro();

        opcaoMenuVeiculo(entrada);
    }

    private static void opcaoMenuVeiculo(int entrada) {
        switch (entrada) {
            case 1 -> cadastrarVeiculo();
            case 2 -> consultarVeiculo();
            case 3 -> listarVeiculos();
            case 4 -> removerVeiculo();
            case 5 -> transferirVeiculo();
            case 6 -> System.out.println("Retornando para o menu principal");
            default -> System.out.println("Opção inserida inválida!");
        }
    }

    private static void cadastrarVeiculo() {

        Veiculo novoVeiculo = new Veiculo();

        System.out.println("==== Cadastrar Veiculo ====");
        System.out.println("Digite os dados cadastrais abaixo:");
        System.out.print("Placa: ");
        String placa = entradaString();

        Optional<Veiculo> verificaVeiculo = veiculoDao.findByPlaca(placa);

        if (verificaVeiculo.isPresent()) {
            System.err.println("Veiculo já existe! Placa: " + placa);
            return;
        }

        novoVeiculo.setPlaca(placa);

        System.out.print("Ano: ");
        Integer ano = entradaInteiro();
        novoVeiculo.setAno(ano);

        System.out.print("Modelo: ");
        String modelo = entradaString();
        novoVeiculo.setModelo(modelo);

        System.out.print("Marca: ");
        String marca = entradaString();
        novoVeiculo.setMarca(marca);

        System.out.print("CNH do condutor: ");
        long nroCnh = entradaLong();
        Condutor condutor = condutorDao.findByNroCnh(nroCnh);

        if (condutor.getVeiculos() != null) {
            System.err.println("Condutor já possui veiculo cadastrado!");
            return;
        }


        novoVeiculo.setCondutor(condutor);

        if (condutor != null) {
            veiculoDao.create(novoVeiculo);
            condutorDao.inserirPlacaVeiculo(nroCnh, novoVeiculo);
            System.out.println("Veiculo Cadastrado!");
        } else {
            System.err.println("Não foi possivel cadastrar o veiculo, condutor inválido!");
        }

    }

    private static void consultarVeiculo() {
        System.out.println("==== Consultar Veiculo ====");
        System.out.println("Digite a placa: ");
        String placa = entradaString();

        Veiculo veiculoConsultado;

        try {
            veiculoConsultado = veiculoDao.findByPlaca(placa)
                    .orElseThrow(RuntimeException::new);
        } catch (RuntimeException e) {
            System.err.println("Veiculo não encontrado! Placa: " + placa);
            return;
        }

        System.out.println(veiculoConsultado);
    }

    private static void listarVeiculos() {
        System.out.println("==== Listar Veiculos ====");
        List<Veiculo> veiculos = veiculoDao.findAll();

        veiculos.forEach(System.out::println);
    }

    private static void removerVeiculo() {
        System.out.println("==== Remover Veiculo ====");
        System.out.print("Digite a placa: ");
        String placa = entradaString();

        try {
//            Veiculo veiculo = veiculoDao.findByPlaca(placa).get();
//            long nroCnh = veiculo.getCondutor().getNroCnh();
//            condutorDao.inserirPlacaVeiculo(nroCnh, null);
            veiculoDao.delete(placa);
        } catch (RuntimeException e){
            System.err.println("Veiculo não existe! Placa: " + placa);
            return;
        }

        System.out.println("Veiculo Removido!");

    }

    private static void transferirVeiculo() {
        System.out.println("==== Transferir Veiculo ====");
        System.out.print("Digite o número da CNH do proprietário: ");
        long nroCnhProprietario = entradaLong();

        System.out.print("Digite o número da CNH do comprador: ");
        long nroCnhComprador = entradaLong();

        String placa = condutorDao.tranferir(nroCnhProprietario, nroCnhComprador);

        if (placa == null) {
            System.err.println("Não foi possivel transferir, condutor informado não existe!");
            return;
        }

        Condutor comprador = condutorDao.findByNroCnh(nroCnhComprador);

        boolean transacaoConcluida = veiculoDao.atualizaDono(placa, comprador);

        if (!transacaoConcluida) {
            return;
        }

        System.out.println("Veiculo transferido: ");
        System.out.println(veiculoDao.findByPlaca(placa).get());

    }

    private static void menuMulta() {
        System.out.println("==== Menu Multa ====");
        System.out.println();
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Consultar por id");
        System.out.println("3 - Consultar por veiculo");
        System.out.println("4 - Listar todas");
        System.out.println("5 - Remover");
        System.out.println("6 - Voltar");
        System.out.println();
        System.out.println("==========================");
        System.out.println();

        System.out.print("Digite a opção desejada: ");

        int entrada = entradaInteiro();

        opcaoMenuMulta(entrada);
    }

    private static void opcaoMenuMulta(int entrada) {
        switch (entrada) {
            case 1 -> cadastrarMulta();
            case 2 -> consultarMultaPorId();
            case 3 -> consultarMultaPorVeiculo();
            case 4 -> listarMulta();
            case 5 -> removerMulta();
            case 6 -> System.out.println("Retornando para o menu principal");
            default -> System.out.println("Opção inserida inválida!");
        }
    }

    private static void consultarMultaPorVeiculo() {
        System.out.println("==== Consultar Multa ====");
        System.out.print("Digite a placa: ");
        String placa = entradaString();

        Veiculo veiculo;

        try {
            veiculo = veiculoDao.findByPlaca(placa)
                    .orElseThrow(RuntimeException::new);
        } catch (RuntimeException e) {
            System.err.println("Multa não cadastrada, veiculo não encontrado! Placa: " + placa);
            return;
        }

        List<Multa> multas = multaDao.findByVeiculo(veiculo);

        multas.forEach(System.out::println);
    }

    private static void cadastrarMulta() {
        Multa novaMulta = new Multa();

        System.out.println("==== Cadastrar Multa ====");
        System.out.println("Digite os dados cadastrais abaixo:");
        System.out.print("Valor: ");
        Double valor = entradaDouble();
        novaMulta.setValor(valor);

        System.out.print("pontuação: ");
        Integer pontuacao = entradaInteiro();
        novaMulta.setPontuacao(pontuacao);

        System.out.print("Placa do veiculo: ");
        String placa = entradaString();

        Veiculo veiculo;

        try {
            veiculo = veiculoDao.findByPlaca(placa)
                    .orElseThrow(RuntimeException::new);
        } catch (RuntimeException e) {
            System.err.println("Multa não cadastrada, veiculo não encontrado! Placa: " + placa);
            return;
        }

        novaMulta.setVeiculo(veiculo);
        long nroCnh = veiculo.getCondutor().getNroCnh();

        multaDao.create(novaMulta);
        condutorDao.atualizaPontuacao(nroCnh, pontuacao);
        veiculoDao.insereMulta(placa, novaMulta);
        System.out.println("Multa Cadastrada!");

    }

    private static void consultarMultaPorId() {
        System.out.println("==== Consultar Multa ====");
        System.out.print("Digite o id: ");
        int id = entradaInteiro();

        Multa multa = multaDao.findById(id);

        if (multa != null) {
            System.out.println(multa);
        } else {
            System.err.println("Multa não encontrada! Id: " + id);
        }

    }

    private static void listarMulta() {
        System.out.println("==== Listar Multas ====");
        List<Multa> multas = multaDao.findAll();

        multas.forEach(System.out::println);
    }

    private static void removerMulta() {
        System.out.println("==== Remover Multa ====");

        System.out.print("Digite o id: ");
        int id = entradaInteiro();

        boolean multaExiste = multaDao.delete(id);

        if (multaExiste) {
            System.out.println("Multa Removida!");
        } else {
            System.err.println("Multa não existe! Id: " + id);
        }
    }

    private static String entradaString() {

        String entrada;

        try {
            return entrada = input.nextLine();
        } catch (NullPointerException e) {
            System.err.println("Valor inserido não pode ser nulo!");
            e.printStackTrace();
        }

        return null;
    }

    private static double entradaDouble() {

        double entrada;

        try {
            entrada = input.nextDouble();
            input.nextLine();
            return entrada;
        } catch (InputMismatchException e) {
            System.err.println("O valor inserido deve ser um número!");
            input.nextLine();
        }

        return 0;
    }

    private static int entradaInteiro() {

        int entrada;

        try {
            entrada = input.nextInt();
            input.nextLine();
            return entrada;
        } catch (InputMismatchException e) {
            System.err.println("O valor inserido deve ser um número inteiro!");
            input.nextLine();
        }
        return 0;
    }

    private static long entradaLong() {

        long entrada;

        try {
            entrada = input.nextLong();
            input.nextLine();
            return entrada;
        } catch (InputMismatchException e) {
            System.err.println("O valor inserido deve ser um número inteiro!");
            input.nextLine();
        }
        return 0;
    }

    private static void pressEnterToContinue() {
        System.out.println("Pressione Enter duas vezes para continuar...");
        try {
            System.in.read();
            input.nextLine();
        } catch (Exception e) {
            System.err.println("Algo deu errado!");
        }
    }

    public static void limpaConsole() {//Limpa a tela no windows, no linux e no MacOS
        try {
            if (System.getProperty("os.name").contains("Windows")) //verifica se o SO é windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                System.out.print("\033\143"); //Limpa console em MacOS e Linux
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao limpar o console!");
        }
    }

}
