package com.pc.wirecard.core;

import java.util.List;

/**
 * @author gino.q
 * @date June 1, 2020
 *
 */
public interface ITransform<F, T> {
	
	public List<F> transform(List<T> list);

}
