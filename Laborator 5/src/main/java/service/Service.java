package service;

import domain.Bug;
import domain.Entity;
import domain.Programmer;
import domain.Tester;
import repository.IBugRepository;
import repository.IProgrammerRepository;
import repository.ITesterRepository;

import java.util.Collection;

public class Service {
    private IProgrammerRepository programmerRepository;
    private ITesterRepository testerRepository;
    private IBugRepository bugRepository;

    public Service(IProgrammerRepository programmerRepository, ITesterRepository testerRepository, IBugRepository bugRepository) {
        this.programmerRepository = programmerRepository;
        this.testerRepository = testerRepository;
        this.bugRepository = bugRepository;
    }

    public Programmer findProgrammerByUsername(String username){
        return programmerRepository.findOneByUsername(username);
    }

    public Tester findTesterByUsername(String username){
        return testerRepository.findOneByUsername(username);
    }

    public Collection<Bug> getAllBugs(){
        return bugRepository.getAll();
    }

    public void deleteBug(int id){
        bugRepository.delete(id);
    }
    public void updateBug(Bug bug, int id){
        bugRepository.update(bug, id);
    }
    public void addBug(Bug bug) throws Exception {
        bugRepository.add(bug);
    }
    public void changePasswordProgrammer(int id, String password){
        Programmer programmer = programmerRepository.findById(id);
        programmer.setPassword(password);
        programmerRepository.update(programmer, id);
    }
    public void changePasswordTester(int id, String password){
        Tester tester = testerRepository.findById(id);
        tester.setPassword(password);
        testerRepository.update(tester, id);
    }
}
