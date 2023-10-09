package com.s8.web.carbon.assets.bundles;

import com.s8.arch.magnesium.handlers.h1.H1Handler;
import com.s8.arch.silicon.SiException;
import com.s8.io.bytes.linked.LinkedBytes;
import com.s8.stack.arch.helium.http2.HTTP2_Status;
import com.s8.web.carbon.assets.CachePolicy;
import com.s8.web.carbon.assets.Payload;
import com.s8.web.carbon.assets.WebAsset;
import com.s8.web.carbon.assets.WebAssetWatcher;
import com.s8.web.carbon.web.AssetContainerModule;


/**
 * 
 * @author pierreconvert
 *
 */
public abstract class BundleWebAsset extends WebAsset implements WebAssetWatcher {

	public abstract int getNbSources();

	public abstract WebAsset getSource(int index);


	private boolean[] isFragmentAvailable;

	protected LinkedBytes[] fragments;

	public BundleWebAsset(AssetContainerModule module, String webPathname, CachePolicy cacheControl) {
		super(module, webPathname, cacheControl);
	}



	public abstract LinkedBytes getBytes();



	protected boolean areFragmentsAvailable() {
		for(boolean flag : isFragmentAvailable) {
			if(!flag) {
				return false;
			}
		}
		return true;
	}




	private class FragmentRetrieval implements H1Handler.Callback<Payload> {

		private int index;

		public FragmentRetrieval(int index) {
			super();
			this.index = index;
		}

		@Override
		public void onSuccessful(Payload payload) {
			LinkedBytes bytes = payload.bytes;

			isFragmentAvailable[index] = true;
			fragments[index] = bytes.copy();
			if(areFragmentsAvailable()) {
				switchToSuccessful(new Payload(getBytes()));
			}
		}


		@Override
		public void onFailed(SiException error) {
			switchToError(new SiException(HTTP2_Status.Failed_Dependency.code, error.getMessage()));
		}
	}


	@Override
	public void compute() {

		int nSources = getNbSources();
		isFragmentAvailable = new boolean[nSources];
		fragments = new LinkedBytes[nSources];
		for(int i=0; i<nSources; i++) {
			isFragmentAvailable[i] = false;
		}

		for(int index=0; index<nSources; index++) {
			getSource(index).access(new FragmentRetrieval(index));
		}
	}




	@Override
	public void reset() {

		// notify watchers
		if(watchers!=null) {
			watchers.forEach(watcher -> watcher.reset());
		}

		switchToShutDown();
	}

}