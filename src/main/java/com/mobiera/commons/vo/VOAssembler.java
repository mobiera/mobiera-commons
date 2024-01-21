package com.mobiera.commons.vo;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;



public abstract class VOAssembler <E extends Object,V extends Serializable> {

	public void assembleVOToEntity(V vo, E entity) throws VOAssemblerException{
			try{
				BeanUtils.copyProperties(entity, vo);
			}
			catch(Exception ex){
				throw new VOAssemblerException(ex);
			}
	}

	public void assembleVOListToEntityList(List<V> voList, List<E> entityList) throws VOAssemblerException{
		if(voList.size() != entityList.size()) throw new VOAssemblerException("Different size list");
			for(int i=0; i<voList.size(); i++){
					assembleVOToEntity(voList.get(i), entityList.get(i));
			}
	}

	public void assembleEntityToVO(E entity, V vo) throws VOAssemblerException{
		try{
				BeanUtils.copyProperties(vo, entity);
		}
		catch(Exception ex){
			throw new VOAssemblerException(ex);
		}
	}

	public void assembleEntityListToVOList(List<E> entityList, List<V> voList) throws VOAssemblerException{
		if(voList.size() != entityList.size()) throw new VOAssemblerException("Different size list");
			for(int i=0; i<entityList.size(); i++){
				assembleEntityToVO(entityList.get(i), voList.get(i));
			}
	}

}