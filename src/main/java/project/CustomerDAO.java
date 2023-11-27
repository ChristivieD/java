package project;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO extends  Database{
    public static void main(String[] args) throws SQLException,ClassNotFoundException {
        getAll().forEach(System.out::println);
    }
    public static List<Customer> getAll(){
        List<Customer> customers = new ArrayList<>();
        try(Connection connection = getConnection()){
            if(connection != null){
                try(
                        CallableStatement statement = connection.prepareCall("{CALL sp_get_all_customers()}");
                        ResultSet resultSet = statement.executeQuery();
                ){
                    while(resultSet.next()){
                        int id = resultSet.getInt("CustomerID");
                        String firstName = resultSet.getString("FirstName");
                        String lastName = resultSet.getString("LastName");
                        String email = resultSet.getString("Email");
                        String phoneNumber = resultSet.getString("PhoneNumber");
                        char[] password = resultSet.getString("Password").toCharArray();
                        Customer customer = new Customer(id, firstName, lastName, email, phoneNumber, password);
                        customers.add(customer);
                    }
                }
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return customers;
    }
}
