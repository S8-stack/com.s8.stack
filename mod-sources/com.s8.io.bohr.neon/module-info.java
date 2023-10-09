/**
 * 
 */
/**
 * @author pierreconvert
 *
 */
module com.s8.io.bohr.neon {
	
	
	

	/* <neon> */
	
	exports com.s8.io.bohr.neon.core;
	
	exports com.s8.io.bohr.neon.fields;
	exports com.s8.io.bohr.neon.fields.primitives;
	exports com.s8.io.bohr.neon.fields.arrays;
	exports com.s8.io.bohr.neon.fields.objects;
	
	

	exports com.s8.io.bohr.neon.providers;
	
	
	exports com.s8.io.bohr.neon.methods;
	exports com.s8.io.bohr.neon.methods.primitives;
	exports com.s8.io.bohr.neon.methods.arrays;
	exports com.s8.io.bohr.neon.methods.objects;
	exports com.s8.io.bohr.neon.methods.zero;
	
	

	/* </neon> */
	
	requires transitive com.s8.api;
	requires transitive com.s8.io.bohr.atom;
	requires transitive com.s8.io.bytes;
	
}