package com.s8.web.front.asw;

import com.s8.api.objects.web.WebS8Object;
import com.s8.api.objects.web.WebS8Session;


/**
 * 
 * @author pierreconvert
 *
 */
public class AswCharacterAttitude extends WebS8Object {

	public AswCharacterAttitude(WebS8Session branch, 
			double speechPitch, 
			double speechRate, 
			double speechVolume, 
			String faceImagePathname) {
		super(branch, "/s8-web-front/asw/AswCharacterAttitude");
		
		vertex.fields().setFloat32Field("speechPitch", (float) speechPitch);
		vertex.fields().setFloat32Field("speechRate", (float) speechRate);
		vertex.fields().setFloat32Field("speechVolume", (float) speechVolume);
		vertex.fields().setStringUTF8Field("faceImagePathname", faceImagePathname);
	}

}
