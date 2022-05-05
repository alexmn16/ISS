package repository;

import domain.Programmer;
import domain.Tester;

public interface ITesterRepository extends Repository<Tester, Integer>{
    Tester findOneByUsername(String username);
}
