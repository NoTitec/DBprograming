package persistence;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PooledDataSource {//connectionpool 관리 클래스
    private static BasicDataSource basicDS;//jvm static메모리에 올라감
    static {//컴파일 시점에 메모리에 로드
        try {
            basicDS = new BasicDataSource();//jdcp객체생성
            Properties properties = new Properties();
            //InputStream inputStream = new FileInputStream("src/main/resources/config/db.yml");//이식성문제발생
            Class<PooledDataSource> pooledDataSourceClass = PooledDataSource.class;
            InputStream inputStream = pooledDataSourceClass.getClassLoader().getResourceAsStream("config/db.yml");
            //어디에 jar파일 있던간에 실행가능
            properties.load(inputStream);// yml파일 파싱
            basicDS.setDriverClassName(properties.getProperty("DRIVER_CLASS")); //loads the jdbc driver
            basicDS.setUrl(properties.getProperty("DB_CONNECTION_URL"));
            basicDS.setUsername(properties.getProperty("DB_USER"));
            basicDS.setPassword(properties.getProperty("DB_PWD"));
            // Parameters for connection pooling
            basicDS.setInitialSize(10);
            basicDS.setMaxTotal(10);
            //그외 여러 set메소드있음
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static DataSource getDataSource() {
        return basicDS;
    }

}
