package org.mesttra.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.mesttra.entity.Multa;
import org.mesttra.entity.Veiculo;

import java.util.List;

public class MultaDao {

    private EntityManager manager;

    public MultaDao(EntityManagerFactory factory) {
        this.manager = factory.createEntityManager();
    }

    //Criar
    public boolean create(Multa multa){
        manager.getTransaction().begin();
        manager.persist(multa);
        manager.getTransaction().commit();
        return true;
    }

    //Consultar
    public Multa findById(int id){
        return manager.find(Multa.class, id);
    }

    public List<Multa> findByVeiculo(Veiculo veiculo){
        Query query = manager.createQuery("SELECT m FROM Multa as m WHERE m.veiculo = :veiculo");
            query.setParameter("veiculo", veiculo);
        return query.getResultList();
    }

    //
    public List<Multa> findAll(){
        Query query = manager.createQuery("SELECT m FROM Multa as m");
        return query.getResultList();
    }

    //Remover
    public boolean delete(int id){
        Multa multaASerRemovida = findById(id);

        if(multaASerRemovida == null){
            return false;
        }

        manager.getTransaction().begin();
        manager.remove(multaASerRemovida);
        manager.getTransaction().commit();

        return true;
    }

}
