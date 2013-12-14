package com.stlagora.model.entities.enumerate;

public enum TITLE {
Mrs,Mr ;

@Override
public String toString(){
	String name="";
	switch(ordinal()){
	case 0:
		name="Mr";
		break;
	case 1:
		name="Mme";
		break;
	}
	return name;
}
}
