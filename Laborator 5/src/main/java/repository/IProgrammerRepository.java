package repository;

import domain.Programmer;

public interface IProgrammerRepository extends Repository<Programmer, Integer> {
    Programmer findOneByUsername(String username);
}
