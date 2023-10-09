package com.s8.web.carbon.web;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.s8.arch.silicon.SiliconEngine;
import com.s8.io.xml.annotations.XML_SetAttribute;
import com.s8.io.xml.annotations.XML_SetElement;
import com.s8.io.xml.annotations.XML_Type;
import com.s8.io.xml.codebase.XML_Codebase;
import com.s8.stack.arch.helium.http2.messages.HTTP2_Message;
import com.s8.web.carbon.build.CarbonBuildContext;
import com.s8.web.carbon.build.sources.WebModule;
import com.s8.web.carbon.build.sources.WebSources;

/**
 * 
 * @author pierreconvert
 *
 */
public class CarbonWebService {


	@XML_Type(name = "CarbonWebServiceConfiguration")
	public static class Config {

		public List<WebModule> modules;

		@XML_SetElement(tag = "module")
		public void addSources(WebModule module) {
			this.modules.add(module);
		}

		public List<WebSources> sources;

		@XML_SetElement(tag = "sources")
		public void addSources(WebSources sources) {
			this.sources.add(sources);
		}

		public boolean isVerbose;

		@XML_SetAttribute(name = "is-verbose")
		public void setVerbosity(boolean flag) {
			this.isVerbose = flag;
		}
		
		
		public String rootLocalPathname;
		
		@XML_SetElement(tag = "root-local-path")
		public void setRootLocalPath(String pathname) {
			this.rootLocalPathname = pathname;
		}
		

		/**
		 * 
		 */
		public Config() {
			super();
			modules = new ArrayList<WebModule>();
			sources = new ArrayList<>();
		}
	}


	public final static String ROOT_WEB_PATHNAME = "/";

	private final SiliconEngine app;

	private boolean isVerbose;

	private AssetContainerModule webModule;


	private AssetUpdateModule updateModule;


	/**
	 * 
	 * @param xml_Context
	 * @param app
	 * @param config
	 * @throws Exception
	 */
	public CarbonWebService(SiliconEngine app, XML_Codebase xml_Context, Config config) throws Exception {
		super();

		this.app = app;
		this.isVerbose = config.isVerbose;
		this.updateModule = new AssetUpdateModule(app, isVerbose);
		this.webModule = new AssetContainerModule(app, isVerbose);

		CarbonBuildContext context = new CarbonBuildContext(webModule, updateModule, true);
		
		
		String webPathname = ROOT_WEB_PATHNAME;
		Path localPath = Path.of(config.rootLocalPathname);
		
		// modules
		for(WebModule module : config.modules) { module.build(xml_Context, context, webPathname, localPath); }
		
		// sources
		for(WebSources sources : config.sources) { sources.build(context, webPathname, localPath); }
	}


	public void start() throws Exception {

		// web module
		webModule.start();

		// update module
		updateModule.start();
	}


	/**
	 * 
	 * 
	 * @param request
	 */
	public void serve(HTTP2_Message request) {
		if(isVerbose) {
			System.out.println("---> CARBON: "+request.path.pathname);
		}
		app.pushAsyncTask(new CarbonWebTask(webModule, request));
	}

	public void stop() {
		// TODO
	}
}
