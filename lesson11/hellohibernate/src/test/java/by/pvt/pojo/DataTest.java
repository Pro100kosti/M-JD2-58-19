package by.pvt.pojo;

import by.pvt.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DataTest {

    @Test
    public void testSaveData() {
        Session session = HibernateUtil.getInstance().getSession();
        Transaction tx = null;
        try {
            //save

//            tx = session.beginTransaction();
//            Data data = createData(1);
//            session.saveOrUpdate(data);
//            tx.commit();
//            session.close();

            tx = session.beginTransaction();
            Person person = PersonTest.createTestData(1);
            session.save(person);
            Data data = createData(1);
            session.saveOrUpdate(data);
            data.setPersonId(person);
            session.saveOrUpdate(data);
            tx.commit();
            session.close();

            session = HibernateUtil.getInstance().getSession();
            tx = session.beginTransaction();
            List<Data> dataList = session.createQuery("from data").list();
            assertEquals(1, dataList.size());
            Data data1 = dataList.get(0);
            assertEquals(data, data1);
            tx.commit();
            session.close();

            //get
            session = HibernateUtil.getInstance().getSession();
            tx = session.beginTransaction();
            Data getData = session.get(Data.class, 1);
            assertEquals(data, getData);
            tx.commit();
            session.close();

            //update
            session = HibernateUtil.getInstance().getSession();
            tx = session.beginTransaction();
            data.setPhone("+37529*******");
            session.update(data);
            Data data3 = session.get(Data.class, 1);
            assertEquals("+37529*******", data3.getPhone());
            tx.commit();
            session.close();

            //delete
//            session = HibernateUtil.getInstance().getSession();
//            tx = session.beginTransaction();
//            session.delete(data2);
//            List<Data> deleteList = session.createQuery("from data").list();
//            assertEquals(1, deleteList.size());
//            tx.commit();

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    private static Data createData(int index) {
        Data data = new Data();
        data.setId(index);
        data.setPhone("+375297389118" + index);
        data.setCar("Audi" + index);
        data.setNumber("8734AB-5" + index);
        return data;
    }

}