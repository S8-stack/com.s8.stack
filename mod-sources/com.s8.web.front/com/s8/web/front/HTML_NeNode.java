package com.s8.web.front;

import com.s8.api.objects.web.WebS8Object;
import com.s8.api.objects.web.WebS8Session;

/**
 * 
 * ==> Generate a DOM node on front side, that can be retrieved by getEnvelope()
 * 
 * 
 * @author pierreconvert
 *
 */
public abstract class HTML_NeNode extends WebS8Object {

	public HTML_NeNode(WebS8Session session, String typeName) {
		super(session, typeName);
	}

}
