package com.mobiera.commons.vo;

public class VOAssemblerException extends RuntimeException {

private static final long serialVersionUID = 1L;

public VOAssemblerException (String message){
super(message);
}

public VOAssemblerException (Throwable cause){
super(cause);
}

public VOAssemblerException (String message, Throwable cause){
super(message, cause);
}

}