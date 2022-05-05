import domain.Programmer;
import repository.IProgrammerRepository;
import repository.ITesterRepository;
import repository.ProgrammerRepository;
import repository.TesterRepository;
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

        IProgrammerRepository programmerRepository = new ProgrammerRepository(props);
        ITesterRepository testerRepository = new TesterRepository(props);
        service = new Service(programmerRepository, testerRepository);

        LoginWindow loginWindow = new LoginWindow();
        loginWindow.main(args);

    }
}