package com.s8.api.objects.web.lambdas.objects;

import java.util.List;

import com.s8.api.objects.web.WebS8Object;



public interface ListLambda<T extends WebS8Object> {

	public void operate(List<T> arg);
}
