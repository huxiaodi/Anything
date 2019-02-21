package genrator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GenratorMain {

    public static void main(String[] args) throws Exception {
        GenratorMain g = new GenratorMain();
        g.gogogo();
    }


    public void gogogo() throws Exception {

        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        URL url = this.getClass().getResource("generatorConfig.xml");
        System.out.println(url.toString());
        File configFile = new File(url.getPath());
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }


}
