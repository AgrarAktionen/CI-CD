package com.aktionen.agrar.dao;

import com.aktionen.agrar.model.CheckSum;

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

    public byte[] insertCheckSum(CheckSum checkSum){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        checkSum.setTimestamp(timestamp);

        List<CheckSum> checkSumList = em.createQuery("select c from CheckSum c where c.checkSum = :check", CheckSum.class)
                .setParameter("check", checkSum.getCheckSum())
                .getResultList();


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
        checkSum.setChanged(true);
        checkSum.setCsvFile(checkSum.getCsvFile());
        em.persist(checkSum);
        em.flush();

        return  checkSum.getCsvFile();
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
