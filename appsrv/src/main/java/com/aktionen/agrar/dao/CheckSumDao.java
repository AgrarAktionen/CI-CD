package com.aktionen.agrar.dao;

import com.aktionen.agrar.model.CheckSum;
import com.aktionen.agrar.model.Item;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Dependent
@Named
public class CheckSumDao {
    @Inject
    EntityManager em;

    public boolean checkIfCheckSumExists(String checkSum){
        boolean checked;
        List<CheckSum> checkSumList = em.createQuery("select c from CheckSum c where c.checkSum = :check", CheckSum.class)
                .setParameter("check", checkSum)
                .getResultList();

        if(checkSumList.isEmpty()){
            checked = false;
        }else {
            checked = true;
        }

        return checked;
    }

    public Timestamp ifTrue(){
        Timestamp timestamp;

        CheckSum checkSum = em.createQuery("select c from CheckSum c where c.changed = :check", CheckSum.class)
                .setParameter("check", true)
                .getSingleResult();

        if(checkSum == null){
            timestamp = null;
        }else {
            timestamp = checkSum.getTimestamp();
        }

        return timestamp;
    }


    public byte[] insertCheckSum(CheckSum checkSum){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        checkSum.setTimestamp(timestamp);

        List<CheckSum> checkSumList = em.createQuery("select c from CheckSum c where c.checkSum = :check", CheckSum.class)
                .setParameter("check", checkSum.getCheckSum())
                .getResultList();

        List<CheckSum> existingCheckSumList = em.createQuery("select c from CheckSum c", CheckSum.class).getResultList();


        Set<CheckSum> checkSumSet = new HashSet<>(checkSumList);


        if(checkSumSet.isEmpty()) {
            em.persist(checkSum);
        }else {
            for (CheckSum check : checkSumSet) {
                if (check.getCheckSum().equals(checkSum.getCheckSum())) {
                    check.setTimestamp(checkSum.getTimestamp());
                    check.setChanged(false);
                    em.merge(check);
                    em.flush();
                    return check.getCsvFile();
                } else {
                    checkSum.setChanged(true);
                    checkSum.setCsvFile(checkSum.getCsvFile());
                    em.persist(checkSum);
                    em.flush();
                    return checkSum.getCsvFile();
                }
            }
        }
        //---------------------------------------//
        for(CheckSum existingOnes: existingCheckSumList){
            existingOnes.setChanged(false);
            em.merge(existingOnes);
            em.flush();
        }
        //---------------------------------------//
        checkSum.setChanged(true);
        checkSum.setCsvFile(checkSum.getCsvFile());
        em.persist(checkSum);
        em.flush();

        return  checkSum.getCsvFile();
    }

    public List<CheckSum> getAll() {
        return em.createQuery("select cs from CheckSum cs ", CheckSum.class).getResultList();
    }

    public CheckSum get(int id) {
        return em.find(CheckSum.class, id);

    }
    public CheckSum getFileById(int id) {
        return em.createQuery("select cs from CheckSum cs where cs.id = :id", CheckSum.class)
                .setParameter("id", id)
                .getSingleResult();

    }

    public void delete(CheckSum checkSum) {
        em.remove(checkSum);
    }

    /*
    public CheckSum insertCheckSum(CheckSum checkSum){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        checkSum.setTimestamp(timestamp);

        List<CheckSum> checkSumList = em.createQuery("select c from CheckSum c where c.checkSum = :check", CheckSum.class)
                .setParameter("check", checkSum.getCheckSum())
                .getResultList();
        if(checkSumList.isEmpty()) {
            em.persist(checkSum);
        }else {
            for (CheckSum check : checkSumList) {
                if (check.getCheckSum().equals(checkSum.getCheckSum())) {
                    check.setTimestamp(checkSum.getTimestamp());
                    check.setChanged(false);
                    em.merge(check);
                    em.flush();
                    return check;
                } else {
                    checkSum.setChanged(true);
                    em.persist(checkSum);
                    return checkSum;
                }
            }
        }
        em.flush();

        return checkSum;
    }

     */
}
