/**
 * 
 */
/**
 * @author pierreconvert
 *
 */
module com.s8.arch.silicon {
	
	exports com.s8.arch.silicon;
	exports com.s8.arch.silicon.async;
	exports com.s8.arch.silicon.watch;
	exports com.s8.arch.silicon.clock;
	
	
	requires transitive com.s8.api;
	requires transitive com.s8.io.xml;
}