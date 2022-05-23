package repository.bd;



import domain.Tester;
import repository.ITesterRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;


public class TesterRepository  implements ITesterRepository {
    private JdbcUtils dbUtils;

    public TesterRepository(Properties props) {
        dbUtils=new JdbcUtils(props);
    }


    @Override
    public void add(Tester elem) {
        Connection connection= dbUtils.getConnection();
        try(PreparedStatement preparedStatement= connection.prepareStatement("insert into Testers (first_name,last_name,username,password) values (?,?,?,?)")){
            preparedStatement.setString(1, elem.getFirstName());
            preparedStatement.setString(2, elem.getLastName());
            preparedStatement.setString(3, elem.getUsername());
            preparedStatement.setString(4, elem.getPassword());
            int result= preparedStatement.executeUpdate();
        }
        catch(SQLException ex)
        {
            System.err.println("Error DB "+ ex);

        }
    }

    @Override
    public void delete(Integer id) {
        Connection connection= dbUtils.getConnection();
        try(PreparedStatement preparedStatement= connection.prepareStatement("delete from Testers where id=?")){
            preparedStatement.setInt(1,id);
            int result= preparedStatement.executeUpdate();
        }
        catch(SQLException ex)
        {
            System.err.println("Error DB "+ ex);
        }

    }

    @Override
    public Tester findById(Integer id) {
        Connection connection= dbUtils.getConnection();
        try(PreparedStatement preparedStatement= connection.prepareStatement("select * from Testers where id=?")){
            preparedStatement.setInt(1,id);
            try(ResultSet resultSet= preparedStatement.executeQuery()){
                if(resultSet.next())
                {
                    int id1= resultSet.getInt("id");
                    String firstName= resultSet.getString("first_name");
                    String lastName= resultSet.getString("last_name");
                    String username= resultSet.getString("username");
                    String password= resultSet.getString("password");
                    Tester Tester=new Tester(firstName,lastName,username,password);
                    Tester.setId(id1);
                    return Tester;
                }
            }
        }catch (SQLException e)
        {
            System.err.println("Error DB "+e);
        }
        return null;
    }

    @Override
    public void update(Tester elem, Integer integer) {
        Connection connection= dbUtils.getConnection();
        try(PreparedStatement preparedStatement= connection.prepareStatement("update Testers  set first_name=?, last_name=?, username=?, password=? where id=?")){
            preparedStatement.setString(1, elem.getFirstName());
            preparedStatement.setString(2, elem.getLastName());
            preparedStatement.setString(3, elem.getUsername());
            preparedStatement.setString(4, elem.getPassword());
            preparedStatement.setInt(5,integer);
            int result= preparedStatement.executeUpdate();
        }
        catch(SQLException ex)
        {
            System.err.println("Error DB "+ ex);
        }
    }

    @Override
    public Collection<Tester> getAll() {
        Connection connection= dbUtils.getConnection();
        List<Tester> Testers=new ArrayList<>();
        try(PreparedStatement preparedStatement= connection.prepareStatement("select * from Testers")){
            try(ResultSet resultSet= preparedStatement.executeQuery()){
                while(resultSet.next())
                {
                    int id1= resultSet.getInt("id");
                    String firstName= resultSet.getString("first_name");
                    String lastName= resultSet.getString("last_name");
                    String username= resultSet.getString("username");
                    String password= resultSet.getString("password");
                    Tester Tester=new Tester(firstName,lastName,username,password);
                    Tester.setId(id1);
                    Testers.add(Tester);
                }
            }
        }catch (SQLException e)
        {
            System.err.println("Error DB "+e);
        }
        return Testers;
    }

    @Override
    public Tester findOneByUsername(String username) {
        Connection connection= dbUtils.getConnection();
        try(PreparedStatement preparedStatement= connection.prepareStatement("select * from Testers where username=?")){
            preparedStatement.setString(1,username);
            try(ResultSet resultSet= preparedStatement.executeQuery()){
                if(resultSet.next())
                {
                    int id1= resultSet.getInt("id");
                    String firstName= resultSet.getString("first_name");
                    String lastName= resultSet.getString("last_name");
                    String password= resultSet.getString("password");
                    Tester Tester=new Tester(firstName,lastName,username,password);
                    Tester.setId(id1);
                    return Tester;
                }
            }
        }catch (SQLException e)
        {
            System.err.println("Error DB "+e);
        }
        return null;
    }
}

