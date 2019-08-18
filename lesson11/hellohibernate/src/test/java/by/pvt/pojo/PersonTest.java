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
            Person person = createTestData(1);
            session.saveOrUpdate(person);
            Person person3 = createTestData(3);
            session.saveOrUpdate(person3);
            tx.commit();
            session.close();

            session = HibernateUtil.getInstance().getSession();
            tx = session.beginTransaction();
            List<Person> personList =
                    session.createQuery("from person").list();
            assertEquals(3, personList.size());
            Person p2 = personList.get(0);
            assertEquals(person, p2);
            tx.commit();
            session.close();

            //get
            session = HibernateUtil.getInstance().getSession();
            tx = session.beginTransaction();
            Person loadedPerson = session.get(Person.class, 1);
            assertEquals(person.getId(), loadedPerson.getId());
            assertEquals(person, loadedPerson);
            tx.commit();
            session.close();

            //update
            session = HibernateUtil.getInstance().getSession();
            tx = session.beginTransaction();
            person.setFirstName("UPDATE");
            session.update(person);
            Person p3 = session.get(Person.class, 1);
            assertEquals("UPDATE", p3.getFirstName());
            tx.commit();
            session.close();

            //delete
            session = HibernateUtil.getInstance().getSession();
            tx = session.beginTransaction();
            session.delete(person3);
            List<Person> list = session.createQuery("from person").list();
            assertEquals(2, list.size());
            tx.commit();

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    static Person createTestData(int index) {
        Person person = new Person();
        person.setId(index);
        person.setFirstName("TestUser" + index);
        person.setLastName("LastName" + index);
        person.setDateOfBirth(new Date());
        person.setGender('M');
        return person;
    }

}