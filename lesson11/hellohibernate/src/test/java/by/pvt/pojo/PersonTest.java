package by.pvt.pojo;

import by.pvt.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import static by.pvt.util.HibernateUtil.*;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class PersonTest {

    @Test
    public void testSavePerson() {
        Session session = getInstance().getSession();
        Transaction tx = null;
        try {
            //save
            tx = session.beginTransaction();
            Person p = createTestData();
            session.saveOrUpdate(p);
            tx.commit();
            session.close();

            session = HibernateUtil.getInstance().getSession();
            tx = session.beginTransaction();
            List<Person> personList =
                    session.createQuery("from person").list();
            assertEquals(1, personList.size());
            Person p2 = personList.get(0);
            assertEquals(p, p2);
            tx.commit();
            session.close();

            //get
            session = HibernateUtil.getInstance().getSession();
            tx = session.beginTransaction();
            Person loadedPerson = session.get(Person.class, 1);
            assertEquals(p.getId(), loadedPerson.getId());
            assertEquals(p, loadedPerson);
            tx.commit();
            session.close();

            //update
            session = HibernateUtil.getInstance().getSession();
            tx = session.beginTransaction();
            p.setFirstName("UPDATE");
            session.update(p);
            Person p3 = session.get(Person.class, 1);
            assertEquals("UPDATE", p3.getFirstName());
            tx.commit();
            session.close();

            //delete
            session = HibernateUtil.getInstance().getSession();
            tx = session.beginTransaction();
            session.delete(p);
            List<Person> list = session.createQuery("from person").list();
            assertEquals(0, list.size());
            tx.commit();

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    private static Person createTestData() {
        Person person = new Person();
        person.setId(1);
        person.setFirstName("TestUser");
        person.setLastName("LastName");
        person.setDateOfBirth(new Date());
        person.setGender('M');
        return person;
    }

}