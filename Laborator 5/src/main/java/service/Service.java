package service;

import domain.Entity;
import domain.Programmer;
import domain.Tester;
import repository.IProgrammerRepository;
import repository.ITesterRepository;

public class Service {
    private IProgrammerRepository programmerRepository;
    private ITesterRepository testerRepository;

    public Service(IProgrammerRepository programmerRepository, ITesterRepository testerRepository) {
        this.programmerRepository = programmerRepository;
        this.testerRepository = testerRepository;
    }

    public Programmer findProgrammerByUsername(String username){
        return programmerRepository.findOneByUsername(username);
    }

    public Tester findTesterByUsername(String username){
        return testerRepository.findOneByUsername(username);
    }

}
