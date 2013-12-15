package com.stlagora.model.entities.enumerate;

public enum ROLE {
	ADMIN,
	MEMBER;
	
	@Override
	public String toString(){
		String name="";
		switch(ordinal()){
		case 0:
			name="admin";
			break;
		case 1:
			name="membre";
			break;
		}
		return name;
	}
}
