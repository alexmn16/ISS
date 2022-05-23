import repository.IBugRepository;
import repository.IProgrammerRepository;
import repository.ITesterRepository;
import repository.bd.ProgrammerRepository;
import repository.bd.TesterRepository;
import repository.hibernate.BugHbmRepository;
import repository.hibernate.ProgrammerHbmRepository;
import repository.hibernate.TesterHbmRepository;
import service.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static Service service;
    public static Service getService() {
        return service;
    }

    public static void main(String[] args){
        Properties props=new Properties();
        try {
            props.load(new FileReader("bd.config.properties"));
        } catch (IOException e) {
            System.out.println("Cannot find bd.config.properties "+e);
        }

        IProgrammerRepository programmerRepository = new ProgrammerHbmRepository();
        ITesterRepository testerRepository = new TesterHbmRepository();
        IBugRepository bugRepository = new BugHbmRepository();
        service = new Service(programmerRepository, testerRepository, bugRepository);

        LoginWindow loginWindow = new LoginWindow();
        loginWindow.main(args);

    }
}