package com.mobiera.commons.tuple;

import java.io.Serializable;

public class Pair<L,R> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1232566050292067080L;
	private R r;
	private L l;
	
	public R getRight() {
		return r;
	}

	public void setRight(R r) {
		this.r = r;
	}

	public L getLeft() {
		return l;
	}

	public void setLeft(L l) {
		this.l = l;
	}

	public Pair(L left, R right) {
		this.r = right;
		this.l = left;
		
	}
	
	
	
}
