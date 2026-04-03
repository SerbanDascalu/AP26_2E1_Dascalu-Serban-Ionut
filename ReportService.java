package org.example;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportService
{
    public void generateReport(List<MovieReportRow> rows, String outputFile) throws Exception
    {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_34);
        cfg.setClassLoaderForTemplateLoading(getClass().getClassLoader(), "/");
        cfg.setDefaultEncoding("UTF-8");

        Template template = cfg.getTemplate("movie_report.ftl");

        Map<String, Object> data = new HashMap<>();
        data.put("movies", rows);

        try (FileWriter writer = new FileWriter(outputFile))
        {
            template.process(data, writer);
        }

        File file = new File(outputFile);
        if (Desktop.isDesktopSupported())
        {
            Desktop.getDesktop().open(file);
        }
    }
}