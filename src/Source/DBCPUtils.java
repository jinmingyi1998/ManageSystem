package Source;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBCPUtils {
    //首先定义私有的datasource
    private static DataSource datasource;
    //把配置文件部分放在静态代码块中，调用时直接加载
    static{
        try {
            //加载文件
            InputStream inputStream=DBCPUtils.class.getClassLoader().getResourceAsStream("dbcp.properties");
            //实例化properties集合
            Properties prop=new Properties();
            prop.load(inputStream);
            //首先加载核心类
            datasource=BasicDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    //提供获得数据源
    public static DataSource getDataSource(){
        return datasource;
    }
    //提供获得连接
    public static Connection getConnection() throws SQLException {
        return datasource.getConnection();
    }
}
