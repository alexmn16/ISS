package repository;



import domain.Programmer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;


public class ProgrammerRepository  implements IProgrammerRepository  {
    private JdbcUtils dbUtils;
    
    public ProgrammerRepository(Properties props) {
        dbUtils=new JdbcUtils(props);
    }


    @Override
    public void add(Programmer elem) {
        Connection connection= dbUtils.getConnection();
        try(PreparedStatement preparedStatement= connection.prepareStatement("insert into Programmers (first_name,last_name,username,password) values (?,?,?,?)")){
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
        try(PreparedStatement preparedStatement= connection.prepareStatement("delete from Programmers where id=?")){
            preparedStatement.setInt(1,id);
            int result= preparedStatement.executeUpdate();
        }
        catch(SQLException ex)
        {
            System.err.println("Error DB "+ ex);
        }
         
    }

    @Override
    public Programmer findById(Integer id) {
        Connection connection= dbUtils.getConnection();
        try(PreparedStatement preparedStatement= connection.prepareStatement("select * from Programmers where id=?")){
            preparedStatement.setInt(1,id);
            try(ResultSet resultSet= preparedStatement.executeQuery()){
                if(resultSet.next())
                {
                    int id1= resultSet.getInt("id");
                    String firstName= resultSet.getString("first_name");
                    String lastName= resultSet.getString("last_name");
                    String username= resultSet.getString("username");
                    String password= resultSet.getString("password");
                    Programmer programmer=new Programmer(firstName,lastName,username,password);
                    programmer.setId(id1);
                    return programmer;
                }
            }
        }catch (SQLException e)
        {
            System.err.println("Error DB "+e);
        }
        return null;
    }

    @Override
    public void update(Programmer elem, Integer integer) {
        Connection connection= dbUtils.getConnection();
        try(PreparedStatement preparedStatement= connection.prepareStatement("update Programmers  set first_name=?, last_name=?, username=?, password=? where id=?")){
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
    public Collection<Programmer> getAll() {
        Connection connection= dbUtils.getConnection();
        List<Programmer> programmers=new ArrayList<>();
        try(PreparedStatement preparedStatement= connection.prepareStatement("select * from Programmers")){
            try(ResultSet resultSet= preparedStatement.executeQuery()){
                while(resultSet.next())
                {
                    int id1= resultSet.getInt("id");
                    String firstName= resultSet.getString("first_name");
                    String lastName= resultSet.getString("last_name");
                    String username= resultSet.getString("username");
                    String password= resultSet.getString("password");
                    Programmer programmer=new Programmer(firstName,lastName,username,password);
                    programmer.setId(id1);
                    programmers.add(programmer);
                }
            }
        }catch (SQLException e)
        {
            System.err.println("Error DB "+e);
        }
        return programmers;
    }

    @Override
    public Programmer findOneByUsername(String username) {
        Connection connection= dbUtils.getConnection();
        try(PreparedStatement preparedStatement= connection.prepareStatement("select * from Programmers where username=?")){
            preparedStatement.setString(1,username);
            try(ResultSet resultSet= preparedStatement.executeQuery()){
                if(resultSet.next())
                {
                    int id1= resultSet.getInt("id");
                    String firstName= resultSet.getString("first_name");
                    String lastName= resultSet.getString("last_name");
                    String password= resultSet.getString("password");
                    Programmer programmer=new Programmer(firstName,lastName,username,password);
                    programmer.setId(id1);
                    return programmer;
                }
            }
        }catch (SQLException e)
        {
            System.err.println("Error DB "+e);
        }
        return null;
    }
}

