package com.eshop.webapp.m.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.Properties;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;



public class PropertiesHandler extends PropertyPlaceholderConfigurer {

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess,Properties props) throws BeansException {
		try {
			String configPath = props.getProperty("configpath");
			this.copyProperties(props,configPath);
		} catch (Exception e) {

		}
		super.processProperties(beanFactoryToProcess, props);
	}

	private void copyProperties(Properties props,String configPath) throws IOException {

		File dir = new File(configPath);
		if (dir.exists() && dir.isDirectory()) {
			File[] files = dir.listFiles(filter(".*\\.properties"));
			for (File file : files) {
				System.out.println(file.getAbsolutePath() + ":");
				Properties fileProp = new Properties();
     			fileProp.load(new FileInputStream(file));
     			Set<Entry<Object, Object>> entrySetFile = fileProp.entrySet();
    			for (Entry<Object, Object> entryFile : entrySetFile) {
    				 String keyFile = entryFile.getKey().toString();
    				 props.setProperty(keyFile, entryFile.getValue().toString());
    				 System.out.println(String.format("  %-40s    =    %s", keyFile, entryFile.getValue().toString()));
    			}
 			}
		}

	}

	private FilenameFilter filter(final String regex) {
        return new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);

            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }

        };
   }
}