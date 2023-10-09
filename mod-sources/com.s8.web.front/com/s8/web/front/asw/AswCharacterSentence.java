package com.s8.web.front.asw;

import com.s8.api.objects.web.WebS8Object;
import com.s8.api.objects.web.WebS8Session;

/**
 * 
 * @author pierreconvert
 *
 */
public class AswCharacterSentence extends WebS8Object {

	/**
	 * 
	 * @param branch
	 * @param text
	 * @param attitudeIndex
	 * @param pause
	 */
	public AswCharacterSentence(WebS8Session branch, String text, int attitudeIndex, long pause) {
		super(branch, "/s8-web-front/asw/AswCharacterSentence");
		vertex.fields().setStringUTF8Field("text", text);
		vertex.fields().setUInt8Field("attitudeIndex", attitudeIndex);
		vertex.fields().setUInt32Field("pause", pause);
	}

}
