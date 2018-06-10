package Source;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SqlConn {
    public static ArrayList<Person> getPersonList() {
        ArrayList<Person> persons = new ArrayList<Person>();
        try {
            Connection conn = DBCPUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "select  * from emp");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getInt("id"));
                person.setName(rs.getString("name"));
                person.setJob(rs.getString("job"));
                person.setHiredate(rs.getDate("hiredate"));
                person.setSalary(rs.getInt("sal"));
                person.setDept(rs.getString("dept"));
                person.setLocation(rs.getString("loc"));
                persons.add(person);
            }
            rs.close();
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persons;
    }

    public static boolean insertPerson(Person person) {
        try {
            Connection conn = DBCPUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO emp ( `name`, `job`, `hiredate`, `sal`, `dept`, `loc`)value (?,?,?,?,?,?)");
            ps = SqlUtils.finishSetPerson(ps, person);
            System.out.println("Insert   "+ps.toString());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean deletePersonById(int id) {
        try {
            Connection conn = DBCPUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement("delete from emp where id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Delete   "+ps.toString());
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean updatePerson(Person person) {
        try {
            Connection conn = DBCPUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement("update emp set" +
                    "`name`= ?,`job`=?,`hiredate`=?,`sal`=?,`dept`=?,`loc`=? " +
                    "where id=?");
            ps = SqlUtils.finishSetPerson(ps, person);
            ps.setObject(7, person.getId());
            ps.executeUpdate();
            System.out.println("Update   "+ps.toString());
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
