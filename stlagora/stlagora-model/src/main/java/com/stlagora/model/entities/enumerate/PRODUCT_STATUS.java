package com.stlagora.model.entities.enumerate;

public enum PRODUCT_STATUS {
	AVAILABLE,
	NOTAVAILABLE;
	
	@Override
	public String toString(){
		String name="";
		switch(ordinal()){
		case 0:
			name="Disponible";
			break;
		case 1:
			name="Indisponible";
			break;
		}
		return name;
	}
}
