package com.atguigu;

import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.net.URL;
import java.nio.file.Paths;


public class SwaggerStaticDocTest {

    /* 输出Markdown到单文件 */
    @Test
    public void generateMarkdownDocsToFile() throws Exception {

        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.MARKDOWN)
                .build();

        Swagger2MarkupConverter.from(new URL("http://127.0.0.1:8001/v2/api-docs"))
                .withConfig(config)
                .build()
                .toFile(Paths.get("src/docs/markdown/generated/all"));
    }
}