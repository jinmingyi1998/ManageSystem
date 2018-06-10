package Source;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class SqlUtils {

    public static PreparedStatement finishSetPerson(PreparedStatement ps, Person person) {
        ArrayList<Object> arrayList = person.getPersonAsArray();
        int tot = 1;
        try {
            ps.setString(1, person.getName());
            ps.setString(2, person.getJob());
            ps.setDate(3, new Date(person.getHiredate().getTime()));
            ps.setInt(4, person.getSalary());
            ps.setString(5, person.getDept());
            ps.setString(6, person.getLocation());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

}
