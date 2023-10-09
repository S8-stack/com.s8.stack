package com.s8.web.carbon.web;

import com.s8.arch.magnesium.handlers.h1.H1Handler;
import com.s8.arch.silicon.SiException;
import com.s8.arch.silicon.async.AsyncSiTask;
import com.s8.arch.silicon.async.MthProfile;
import com.s8.io.bytes.linked.LinkedBytes;
import com.s8.stack.arch.helium.http2.HTTP2_Status;
import com.s8.stack.arch.helium.http2.headers.CacheControl;
import com.s8.stack.arch.helium.http2.headers.ContentLength;
import com.s8.stack.arch.helium.http2.headers.ContentType;
import com.s8.stack.arch.helium.http2.headers.Status;
import com.s8.stack.arch.helium.http2.messages.HTTP2_Message;
import com.s8.stack.arch.helium.mime.MIME_Type;
import com.s8.web.carbon.assets.Payload;
import com.s8.web.carbon.assets.WebAsset;


/**
 * 
 * @author pierreconvert
 *
 */
public class CarbonWebTask implements AsyncSiTask {

	
	private final AssetContainerModule module;
	
	HTTP2_Message request;


	/**
	 * 
	 * @param module
	 * @param request
	 */
	public CarbonWebTask(AssetContainerModule module, HTTP2_Message request) {
		super();
		this.module = module;
		this.request = request;
	}

	@Override
	public MthProfile profile() {
		return MthProfile.FX0;
	}
	
	@Override
	public void run() {

		// resolve
		String webPathname = request.path.pathname;

		// look-up
		WebAsset asset = module.getAsset(webPathname);

		if(asset!=null) {
			asset.access(new H1Handler.Callback<Payload>() {

				@Override
				public void onSuccessful(Payload payload) {
					sendBytes(asset, payload.bytes);
					/*
					if(asset.mime_getType()==MIME_Type.SVG) {
						System.out.println(new String(payload.bytes.bytes, StandardCharsets.UTF_8));
					}
					*/
				}

				@Override
				public void onFailed(SiException error) {
					//HTTP2_Status status, String message
					sendError(HTTP2_Status.byCode(error.code), error.getMessage());
				}

				
			});
		}
		else {
			sendError(HTTP2_Status.NOT_FOUND, "Failed to find resource");
		}
	}

	public void sendBytes(WebAsset webAsset, LinkedBytes bytes) {

		HTTP2_Message response = request.respond();
		

		
		response.status = new Status(HTTP2_Status.OK);

		// content-type
		MIME_Type mime_Type = webAsset.mime_getType();
		if(mime_Type!=null) {
			/*
				if(type.)) {
					contentType+="; charset="+type.getEncoding();	
				}
			 */
			response.contentType = new ContentType(mime_Type.template);
		}

		
		response.cacheControl = new CacheControl(webAsset.getCacheControl().value);

		// content-length
		response.contentLength = new ContentLength(Long.toString(bytes.getBytecount()));

		response.appendDataFragment(bytes);
		response.send();
	}


	public void sendError(HTTP2_Status status, String message) {

		byte[] bytes = message.getBytes();
		HTTP2_Message response = request.respond();

		// headers
		response.status = new Status(status);
		// MIME_Type.get("text").getTemplate() == "text"
		response.contentType = new ContentType(MIME_Type.TEXT);
		response.contentLength = new ContentLength(Integer.toString(bytes.length));

		response.appendDataFragment(new LinkedBytes(bytes));

		response.send();

	}
	
	
	@Override
	public String describe() {
		return "(Carbon) RESPOND_TO_REQUEST task";
	}	

}
