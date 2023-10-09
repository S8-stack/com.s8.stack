package com.s8.web.carbon.utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.s8.io.xml.codebase.XML_Codebase;
import com.s8.io.xml.handler.type.XML_TypeCompilationException;
import com.s8.web.carbon.build.sources.WebSources;

public class XSD_Generator {

	public static void main(String[] args) throws XML_TypeCompilationException, IOException {

		XML_Codebase lexicon = XML_Codebase.from(WebSources.class);
		Writer writer = new FileWriter(new File("schemas/schema.xsd"));
		lexicon.xsd_writeSchema(writer);
		writer.close();
	}

}
