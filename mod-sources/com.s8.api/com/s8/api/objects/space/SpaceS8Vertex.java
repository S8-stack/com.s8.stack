package com.s8.api.objects.space;

import com.s8.api.exceptions.S8IOException;

public interface SpaceS8Vertex {

	public void reportChange(String fieldName) throws S8IOException;

}
