import com.ultrapower.framework.configuration.util.YamlUtils;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileWriter;
import java.util.Properties;
import java.util.Set;

public class Yam2Sql {

    static String SQL_FORMAT = "insert into U_properties (ID, U_KEY, U_VALUE, APPLICATION, U_PROFILE, LABEL) values ('%s', '%s', '%s', '%s', 'default', 'master');";

    static final String path = "E:\\workspaces\\Ultra_IAM_5.0.2\\Ultra_IGA_Configuration\\src\\main\\resources\\";

    static final String[] files = new String[]{"datasource", "audit", "authn", "authorization", "baiduapi", "encrypt", "flow", "jk", "job", "kafka", "mail", "monitoring", "redis", "resource", "sms", "sso", "sysconfig", "user", "usercenter"};

    static final String NEW_SQL_FILE = "D:\\config.sql";

    public static void main(String[] args) throws Exception{
        int i = 1;
        FileWriter writer = new FileWriter(new File(NEW_SQL_FILE));
        for (String file : files) {
            Resource resource = new PathResource(path + file + ".yml");
            Properties properties = YamlUtils.yaml2Properties(resource);
            Set<Object> keySet = properties.keySet();
            for (Object obj : keySet) {
                String key = String.valueOf(obj);
                String value = String.valueOf(properties.getProperty(key));
                String sql = String.format(SQL_FORMAT, i, key, value, file);
                System.out.println(sql);
                writer.write(sql);
                writer.write("\n");
                i++;
            }
        }
        writer.flush();
        writer.close();

    }

}
