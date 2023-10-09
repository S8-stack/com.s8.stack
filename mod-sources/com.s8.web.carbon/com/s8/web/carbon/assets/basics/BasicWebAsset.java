package com.s8.web.carbon.assets.basics;

import java.io.IOException;
import java.nio.file.Path;

import com.s8.arch.silicon.SiException;
import com.s8.io.bytes.linked.LinkedBytes;
import com.s8.stack.arch.helium.http2.HTTP2_Status;
import com.s8.web.carbon.assets.CachePolicy;
import com.s8.web.carbon.assets.Payload;
import com.s8.web.carbon.assets.WebAsset;
import com.s8.web.carbon.web.AssetContainerModule;
import com.s8.web.carbon.web.FileDiskTask;

/**
 * 
 * @author pierreconvert
 *
 */
public abstract class BasicWebAsset extends WebAsset {


	private int fragmentLength;

	private Path path;



	/**
	 * 
	 */
	public BasicWebAsset(AssetContainerModule module, 
			String webPathname, 
			Path path, 
			CachePolicy cachePolicy, 
			int fragmentLength) {
		super(module, webPathname, cachePolicy);
		this.path = path;
		this.fragmentLength = fragmentLength;
	}



	/*
	private boolean hasBeenModified() {
		lock.lock();
		File file = path.toFile();
		long currentTimestamp = file.lastModified();
		boolean hasBeenModified = currentTimestamp>timestamp;
		lock.unlock();
		return hasBeenModified;
	}
	 */



	@Override
	public void compute() {
		getAppEngine().pushAsyncTask(new LoadTask(path, fragmentLength));
	}






	public Path getPath() {
		return path;
	}


	private class LoadTask extends FileDiskTask {

		/**
		 * 
		 * @param path
		 * @param fragmentLength
		 * @throws IOException
		 */
		public LoadTask(Path path, int fragmentLength) {
			super(module, path, fragmentLength);
		}

		@Override
		public void onLoadedSuccessfully(LinkedBytes bytes) {
			switchToSuccessful(new Payload(bytes));
		}

		@Override
		public void onNotFound() {
			switchToError(new SiException(HTTP2_Status.NOT_FOUND.code, "failed to find resource"));
		}

		@Override
		public void onFailedLoad() {
			switchToError(new SiException(HTTP2_Status.BAD_REQUEST.code, "failed to find resource"));
		}

		@Override
		public String describe() {
			return "(Carbon) LOAD_WEB_ASSET task";
		}
	}	


	@Override
	public void reset() {

		/*
		lock.lock();
		clearCache();
		status = null;
		state = WebAssetState.NOT_INITIATED;

		lock.unlock();
		 */

		switchToNotInitiated();

		// notify watchers
		if(watchers!=null) {
			watchers.forEach(watcher -> watcher.reset());
		}
	}


	public abstract String getTypeName();

	@Override
	public String toString() {
		return getTypeName()+" "+getWebPathname()+" ("+path.toString()+")";
	}
}
