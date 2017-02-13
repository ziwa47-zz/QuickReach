package tw.iii.qr.IndependentOrder.service;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.LocaleResolver;

import tw.iii.qr.IndependentOrder.model.repository.AbstractDAO;



public abstract class AbstractService<T> {
	protected abstract AbstractDAO<T> getDAO();
	protected Logger logger = Logger.getLogger(getClass());
	@Autowired protected MessageSource    messageSource;
//	@Autowired protected LocaleResolver   localeResolver;

	
	@Transactional
	public List<T> getList() throws Exception {
		return getDAO().get();
	}
	
	
	@Transactional
	public void saveOrUpdate(T t) throws Exception {
		getDAO().saveOrUpdate(t);
	}
	
	@Transactional
	public void persist(T t) throws Exception {
		getDAO().persist(t);
	}
	
	@Transactional
	public void update(T t) throws  Exception {
		getDAO().update(t);
	}
	
	@Transactional
	public void delete(T t) throws Exception {
		getDAO().delete(t);
	}
	
	
	
	
	
	@Transactional
	public T get(Serializable id) throws Exception {
		return getDAO().get(id);
	}
	
}
