package com.dy.common;

public interface TransactionDelegate {

	public void doPostTransaction( int requestCode, Object result );
	
}
