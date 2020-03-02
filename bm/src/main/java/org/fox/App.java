package org.fox;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.apache.velocity.tools.config.EasyFactoryConfiguration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;

public class App {
    public static void main(String[] args) throws IOException {

        EasyFactoryConfiguration config = new EasyFactoryConfiguration();

        VelocityEngine ve = new VelocityEngine();

        ve.setProperty(org.apache.velocity.runtime.RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        // 初始化
        ve.init();

        // 导入模板
        Template template = ve.getTemplate("vm/domain.java.vm");

        VelocityContext ctx = new VelocityContext();
        ctx.put("package", "org.fox");
        ctx.put("className", "Test");
        ctx.put("Object", "Value");
        StringWriter sw = new StringWriter();
        template.merge(ctx, sw);
        String r = sw.toString();
        System.out.println(r);
        File file = new File("bm/src/main/resources/TestDomain.java");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.append(r);
        fileWriter.flush();
        fileWriter.close();
    }
}
