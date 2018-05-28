package sample.DataBaseConnection;

import sample.DataBaseConnection.Configs;
import sample.DataBaseConnection.Const;
import sample.User;

import java.sql.*;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public  Connection getDbConnection()
            throws  ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://"+dbHost+":"+dbPort+"/"+dbName + "?verifyServerCertificate=false"+
                             "&useSSL=false"+ "&requireSSL=false"+ "&useLegacyDatetimeCode=false"+ "&amp"+ "&serverTimezone=UTC";
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser,dbPass);
        return dbConnection;
    }


    public void signUpUser(User user){
        String insert = "INSERT INTO "+ Const.USER_TABLE + "(" +
                Const.USERS_FIRSTNAME+","+Const.USERS_LASTNAME+","+Const.USERS_GENDER+","+Const.USERS_GROUPNAME+","+Const.USERS_EMAIL+","+Const.USERS_PHONE+","+Const.USERS_DOB+")"+
                "VALUES(?,?,?,?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1,user.getFirstname());
            prSt.setString(2,user.getLastname());
            prSt.setString(3,user.getGenger());
            prSt.setString(4,user.getGroupname());
            prSt.setString(5,user.getEmail());
            prSt.setString(6,user.getPhone());
            prSt.setString(7,user.getDob());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    }
