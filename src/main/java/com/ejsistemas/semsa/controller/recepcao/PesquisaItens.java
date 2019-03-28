package com.ejsistemas.semsa.controller.recepcao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.ejsistemas.semsa.model.ItemReceituario;
import com.ejsistemas.semsa.repository.ItemReceituarioRepository;

@Named
@ViewScoped
public class PesquisaItens implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ItemReceituarioRepository itemReceituarioRepository;
	
	
	private static LazyDataModel<ItemReceituario> model;

	
	public PesquisaItens() {
	
		model = new LazyDataModel<ItemReceituario>() {
		
		private static final long serialVersionUID = 1L;
		
		public void setRowCount() {
			super.setRowCount(itemReceituarioRepository.getItemReceituarioTotalCount());
		}
		
		
		
		@Override
		public List<ItemReceituario> load(int first, int pageSize, String sortField, SortOrder sortOrder,
				Map<String, Object> filters) {
			setRowCount();
			
			List<ItemReceituario> list = itemReceituarioRepository.getItemReceituarioList(first, pageSize, filters);
			
			if (filters != null && filters.size() > 0) {
	            //otherwise it will still show all page links; pages at end will be empty
	            this.setRowCount(itemReceituarioRepository.getFilteredRowCount(filters));
	        }
	        return list;
					
		}
		
	};
	
	
	}
	
	public LazyDataModel<ItemReceituario> getModel() {
		return model;
	}
}
