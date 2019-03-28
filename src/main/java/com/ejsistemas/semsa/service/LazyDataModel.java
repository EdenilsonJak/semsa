package com.ejsistemas.semsa.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;

import org.primefaces.model.SortOrder;

import com.ejsistemas.semsa.model.ItemReceituario;

@ApplicationScoped
public class LazyDataModel extends org.primefaces.model.LazyDataModel<ItemReceituario>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ItemReceituario> dataSource;
	
	public LazyDataModel(List<ItemReceituario> list) {
		this.dataSource = list;
	}
	

	@Override
	public List<ItemReceituario> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		List<ItemReceituario> data = new ArrayList<ItemReceituario>();
		 
        //filter
        for(ItemReceituario item : dataSource) {
            boolean match = true;
 
            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(item.getClass().getField(filterProperty).get(item));
 
                        if(filterValue == null || fieldValue.startsWith(filterValue.toString())) {
                            match = true;
                    }
                    else {
                            match = false;
                            break;
                        }
                    } catch(Exception e) {
                        match = false;
                    }
                }
            }
 
            if(match) {
                data.add(item);
            }
        }
 
        /*sort
        if(sortField != null) {
            Collections.sort(data, new LazySorter(sortField, sortOrder));
        }
 		*/
        
        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);
 
        //paginate
        if(dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        }
        else {
            return data;
        }
    }

}
