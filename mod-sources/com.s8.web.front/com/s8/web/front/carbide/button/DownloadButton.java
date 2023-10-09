package com.s8.web.front.carbide.button;

import java.nio.charset.StandardCharsets;

import com.s8.api.flow.delivery.S8WebResource;
import com.s8.api.flow.delivery.S8WebResourceGenerator;
import com.s8.api.objects.web.WebS8Session;
import com.s8.web.front.HTML_NeNode;
import com.s8.web.front.S8WebSize;
import com.s8.web.front.S8WebTheme;


/**
 * 
 * @author pierreconvert
 *
 */
public class DownloadButton extends HTML_NeNode {
	
	/**
	 * 
	 * @param branch
	 * @param label
	 * @param size
	 * @param style
	 * @return
	 */
	public static DownloadButton create(WebS8Session branch, String label, S8WebSize size, S8WebTheme theme) {
		DownloadButton button = new DownloadButton(branch);
		
		button.setLabel(label);
		button.setSize(size);
		button.setTheme(theme);
		
		return button;
	}
	
	public enum Style{
		DEFAULT(0x02), PRIMARY(0x03), OUTLINE(0x04), DANGER(0x05);
		public int code;
		private Style(int code) { this.code = code; }
	}
	
	
	/**
	 * 
	 * @param branch
	 */
	public DownloadButton(WebS8Session branch) {
		super(branch, "/s8-web-front/carbide/button/DownloadButton");
		
		/* to be overridden */
		vertex.providers().setRawProvider("get-file", () -> {
			String text = "hello world, this is a download";
			byte[] data = text.getBytes(StandardCharsets.UTF_8);
			return new S8WebResource("text", data);
		});
	}
	
	
	/**
	 * 
	 * @param text
	 */
	public void setLabel(String text) {
		vertex.fields().setStringUTF8Field("label", text);
	}
	
	
	public void setDownloadFilename(String text) {
		vertex.fields().setStringUTF8Field("downloadFilename", text);
	}
	
	
	

	/**
	 * 
	 * @param menus
	 */
	public void setSize(S8WebSize size) {
		vertex.fields().setUInt8Field("size", size.code);
	}
	
	
	/**
	 * 
	 * @param menus
	 */
	public void setTheme(S8WebTheme theme) {
		vertex.fields().setUInt8Field("theme", theme.code);
	}
	
	
	/**
	 * 
	 * @param state
	 */
	public void setEnabled(boolean state) {
		vertex.fields().setBool8Field("isEnabled", state);
	}
	
	
	
	/**
	 * 
	 * @param generator
	 */
	public void onFileRequested(S8WebResourceGenerator generator) {
		vertex.providers().setRawProvider("get-file", generator);
	}
	
}

