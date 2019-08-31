package by.pvt.repository;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    public String getEmailByUserName(String userName){
        //session.createQuery();
        return "repository_messager@mail.ru";
    }

}
