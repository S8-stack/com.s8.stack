package com.s8.web.carbon.build.pointers;

import java.io.IOException;
import java.nio.file.Path;

import com.s8.io.xml.annotations.XML_SetAttribute;
import com.s8.io.xml.annotations.XML_Type;
import com.s8.web.carbon.assets.CachePolicy;
import com.s8.web.carbon.assets.WebAsset;
import com.s8.web.carbon.build.CarbonBuildContext;
import com.s8.web.carbon.build.pointers.basics.BasicWebAssetPointer;
import com.s8.web.carbon.build.pointers.bundles.JS_BundleBuilder;
import com.s8.web.carbon.build.pointers.folder.Folder;


@XML_Type(name = "builder", sub= {
		BasicWebAssetPointer.class,
		JS_BundleBuilder.class,
		Folder.class
})
public abstract class WebAssetPointer {
	


	/**
	 * <h1>code: (0x5)</h1>
	 * <p>
	 * Indicates the size of the largest frame payload that the sender 
	 * is willing to receive, in octets. The initial value is 2^14 (16,384) 
	 * octets.
	 * </p>
	 */
	public final static int MAX_FRAME_SIZE = 16384;

	
	public CachePolicy cacheControl;

	@XML_SetAttribute(name = "cache")
	public void setCacheControl(CachePolicy cacheControl) {
		this.cacheControl = cacheControl;
	}
	

	/**
	 * 
	 */
	protected int fragmentLength;

	@XML_SetAttribute(name = "frag-length")
	public void setFragmentLength(int length) {
		this.fragmentLength = length;
	}


	/**
	 * 
	 * @param context
	 * @param webPathname
	 * @param path
	 * 
	 * @throws IOException
	 */
	public abstract WebAsset build(CarbonBuildContext context, 
			String contextWebPathname, 
			Path contextLocalPath) throws IOException;
}
