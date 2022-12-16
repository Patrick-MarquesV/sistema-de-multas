package org.mesttra.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.mesttra.entity.Condutor;
import org.mesttra.entity.Veiculo;

import java.util.List;

public class CondutorDao {

    private EntityManager manager;

    public CondutorDao(EntityManagerFactory factory) {
        this.manager = factory.createEntityManager();
    }

    //Criar
    public boolean create(Condutor condutor){
        manager.getTransaction().begin();
        manager.persist(condutor);
        manager.getTransaction().commit();

        return true;
    }

    //Consultar
    public Condutor findByNroCnh(long nroCnh){
        return manager.find(Condutor.class, nroCnh);
    }

    //Listar
    public List<Condutor> findAll(){
        Query query = manager.createQuery("select c from Condutor as c");
        return query.getResultList();
    }

    //Remover
    public boolean delete(long nroCnh){
        Condutor condutorASerRemovido = findByNroCnh(nroCnh);

        if(condutorASerRemovido == null){
            return false;
        }

        manager.getTransaction().begin();
        manager.remove(condutorASerRemovido);
        manager.getTransaction().commit();

        return true;
    }

    public void inserirPlacaVeiculo(long nroCnh, Veiculo veiculo){

        Condutor condutorAAlterar = manager.find(Condutor.class, nroCnh);

        condutorAAlterar.setVeiculos(veiculo);

        manager.getTransaction().begin();
        manager.merge(condutorAAlterar);
        manager.getTransaction().commit();

    }

    public String tranferir(long nroCnhProprietario, long nroCnhComprador){

        Condutor dono = manager.find(Condutor.class, nroCnhProprietario);
        Condutor comprador = manager.find(Condutor.class, nroCnhComprador);

        if(dono == null || comprador ==null){
            return null;
        }

        Veiculo veiculo = dono.getVeiculos();

        dono.setVeiculos(null);
        comprador.setVeiculos(veiculo);

        manager.getTransaction().begin();
        manager.merge(dono);
        manager.merge(comprador);
        manager.getTransaction().commit();

        return veiculo.getPlaca();

    }

    public void atualizaPontuacao(long nroCnh, Integer pontuacao){

        Condutor condutorAAlterar = manager.find(Condutor.class, nroCnh);

        condutorAAlterar.setPontuacao(condutorAAlterar.getPontuacao() + pontuacao);

        manager.getTransaction().begin();
        manager.merge(condutorAAlterar);
        manager.getTransaction().commit();

    }


}
