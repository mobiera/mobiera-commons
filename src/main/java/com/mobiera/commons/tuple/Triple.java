package com.mobiera.commons.tuple;

import java.io.Serializable;

public class Triple<L,M,R> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6613312251343682627L;
	private L l;
	private M m;
	private R r;
	
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

	public Triple(L left, M middle, R right) {
		this.r = right;
		this.m = middle;
		this.l = left;
		
	}

	public M getMiddle() {
		return m;
	}

	public void setMiddle(M m) {
		this.m = m;
	}
	
	
	
}
