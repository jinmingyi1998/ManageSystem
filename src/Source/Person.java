package Source;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

public class Person implements Serializable {
    private int id;
    private String name;
    private String dept;
    private String job;
    private int salary;
    private Date hiredate;
    private String location;
    private ArrayList<Object> infoList;

    ArrayList<Object> getPersonAsArray() {
        infoList = new ArrayList<>();
        infoList.add(id);
        infoList.add(name);
        infoList.add(dept);
        infoList.add(job);
        infoList.add(salary);
        infoList.add(hiredate);
        infoList.add(location);
        return infoList;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDept() {
        return dept;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }
}
