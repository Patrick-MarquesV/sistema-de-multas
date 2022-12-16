package org.mesttra.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.mesttra.entity.Condutor;
import org.mesttra.entity.Multa;
import org.mesttra.entity.Veiculo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VeiculoDao {

    private EntityManager manager;

    public VeiculoDao(EntityManagerFactory factory) {
        this.manager = factory.createEntityManager();
    }

    //Criar
    public boolean create(Veiculo veiculo){
        manager.getTransaction().begin();
        manager.persist(veiculo);
        manager.getTransaction().commit();

        return true;
    }

    //Consultar
    public Optional<Veiculo> findByPlaca(String placa) {
        return Optional.ofNullable(manager.find(Veiculo.class, placa));
    }
    //Listar
    public List<Veiculo> findAll(){
        Query query = manager.createQuery("SELECT v FROM Veiculo AS v");

        return query.getResultList();
    }

    //Remover
    public void delete(String placa){
        Veiculo veiculoASerRemovido = manager.find(Veiculo.class, placa);

        manager.getTransaction().begin();
        manager.remove(veiculoASerRemovido);
        manager.getTransaction().commit();
    }

    public boolean atualizaDono(String placa, Condutor comprador){
        Veiculo veiculo = manager.find(Veiculo.class, placa);

        if(veiculo == null){
            System.out.println("Veiculo não encontrado!");
            return false;
        }

        veiculo.setCondutor(comprador);

        manager.getTransaction().begin();
        manager.merge(veiculo);
        manager.getTransaction().commit();

        return true;

    }

    public boolean insereMulta(String placa, Multa novaMulta){
        Veiculo veiculo = manager.find(Veiculo.class, placa);

        if(veiculo == null){
            System.out.println("Veiculo não encontrado!");
            return false;
        }

        List<Multa> multas = veiculo.getMultas();

        if(multas==null){
            multas = new ArrayList<>();
        }
        multas.add(novaMulta);

        veiculo.setMultas(multas);

        manager.getTransaction().begin();
        manager.merge(veiculo);
        manager.getTransaction().commit();

        return true;

    }


}
