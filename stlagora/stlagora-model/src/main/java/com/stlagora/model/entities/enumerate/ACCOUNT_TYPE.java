package com.stlagora.model.entities.enumerate;

public enum ACCOUNT_TYPE {
	PRIVATE,
	PRO;
	
	@Override
	public String toString(){
		String name="";
		switch(ordinal()){
		case 0:
			name="Particulier";
			break;
		case 1:
			name="Professionnel";
			break;
		}
		return name;
	}
}