package tw.iii.qr.IndependentOrder.model.repository;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Table;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;

@SuppressWarnings("unchecked")

public abstract class AbstractDAO<T> {
	@Autowired protected ConversionService conversionService;
	@Autowired protected SessionFactory sessionFactory;
	protected Logger logger = Logger.getLogger(getClass());
	protected abstract Class<T> getEntityClass();
	

	
	
	public List<T> get() throws Exception {
		return sessionFactory.getCurrentSession().createCriteria(getEntityClass()).list();
	}
	public List<T> getWithOrderBy(String...orderBys) throws Exception {
		List<Order> orders = new ArrayList<Order>();
		if(orderBys!=null) {
			String[] temp;
			for(String orderBy:orderBys) {
				if(orderBy==null) continue;
				orderBy = orderBy.trim();
				if(orderBy.indexOf(' ')!=-1) {
					temp = orderBy.split(" ");
					if (temp.length==2) {
						if(temp[1].equalsIgnoreCase("ASC")) {
							orders.add(Order.asc(temp[0]));
						} else if(temp[1].equalsIgnoreCase("DESC")) {
							orders.add(Order.desc(temp[0]));
						} else {
							//Ignore
						}
					} else {
						//Ignore
					}
				} else {
					orders.add(Order.asc(orderBy));
				}
			}
		}
		return getWithOrderBy(orders.toArray(new Order[orders.size()]));
	}
	public List<T> getWithOrderBy(Order...orderBys) throws Exception {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(getEntityClass());
		if(orderBys!=null) {
			for(Order orderBy:orderBys) {
				if(orderBy==null) continue;
				criteria.addOrder(orderBy);
			}
		}
		return criteria.list();
	}
	public T get(Serializable id) throws Exception {
		if(id==null) return null;
		return (T)sessionFactory.getCurrentSession().get(getEntityClass(), id);
	}
	
	public void saveOrUpdate(T t) throws Exception {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}
	public void merge(T t) throws Exception {
		sessionFactory.getCurrentSession().merge(t);
	}
	public void flush(){
		sessionFactory.getCurrentSession().flush();
	}
	public void persist(T t) throws Exception {
		sessionFactory.getCurrentSession().persist(t);
	}
	public void update(T t) throws Exception {
		sessionFactory.getCurrentSession().update(t);
	}
	public void saveOrUpdateAll(Collection<T> objs) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		int counter = 0;
		for(T obj:objs) {
			session.saveOrUpdate(obj);
			++counter;
			if(counter%100==0) session.flush();
		}
	}
	public void deleteAll(Collection<T> objs) throws Exception {
		if(objs==null || objs.size()==0) return;
		
		Session session = sessionFactory.getCurrentSession();
		int counter = 0;
		for(T obj:objs) {
			session.delete(obj);
			++counter;
			if(counter%100==0) session.flush();
		}
	}
	
	public void delete(T t) throws Exception {
		if(t!=null) {
			sessionFactory.getCurrentSession().delete(t);
		}
	}
	
	
	
	/**使用SQL查詢，並將查詢結果封裝成entity
	 * version.13.01.29
	 * */
	public <E, V extends E> List<E> get(String sql,List<Object> params, Class<V> entityClass){
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(entityClass);
		if(params != null){
			for(int i = 0;i < params.size();i++){
				Object o = params.get(i);
				query.setParameter(i, o);
			}
		}
		return query.list();
	}
	
	/**使用SQL Function查詢，並將查詢結果封裝成entity
	 * version.13.01.31
	 * */
//	public <E, V extends E> List<E> getWithFunction(String functionName,List<Object> params, Class<V> entityClass){
	public <E> List<E> getWithFunction(String functionName,List<Object> params, Class<E> entityClass,String orderBy){
		return get(new StringBuilder("select * from ").append(functionName).append(" ").append(orderBy).toString(), params, entityClass);
	}
	
	public <E, V extends E> List<E> getWithFunction(String functionName,List<Object> params, Class<V> entityClass){
		return get(new StringBuilder("select * from ").append(functionName).append(" ").toString(), params, entityClass);
	}
	
	/**使用SQL查詢分頁資料<br>
	 * 修正BUG：筆數錯誤<br>
	 * By Siolk 2013/07/26<br>
	 * SQL關鍵字FROM、ORDER必須大寫
	 * */
	
	public int update(String sql,List<Object> param) throws Exception{
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		for(int i = 0;i < param.size();i++){
			query.setParameter(i, param.get(i));
		}
		return query.executeUpdate();
	}
	public int delete(String sql,List<Object> param) throws Exception{
		return update(sql, param);
	}
	
	
	
	public int deleteByExample(T t)throws Exception{
		return deleteByExample(t, false);
	}
	public int deleteByExample(T t,boolean isMySQL)throws Exception{
		StringBuilder sql = new StringBuilder();
		List<Object> vals = new ArrayList<Object>();
		Field[] fields = t.getClass().getDeclaredFields();
		sql.append("delete ");
		if(isMySQL){
			sql.append(" from ");
		}
		sql.append(getRealTableName(t.getClass()));
		int i = 0;
		for(Field f : fields){
			if(!f.getName().equals("serialVersionUID")){
				String name = getRealColumnName(t.getClass(), f.getName());
				if(name == null){name = getRealColumnNameByMethod(t.getClass(), f.getName());logger.info("name is null");}
				Object o = getValue(t, f.getName());
				if(name != null && o != null){
					if(i==0){sql.append(" where ");}else{sql.append(" and ");}
					sql.append(" ").append(name).append("=?");
					vals.add(o);
					++i;
				}else{
					logger.info("name=" + name + ",o=" + o);
				}
			}else{
				logger.info("This pojo has field that name is serialVersionUID.");
			}
		}
		
		return delete(sql.toString(), vals);  
	}
	
	
	/**取得實際欄位名稱
	 * 限屬性設定@Column時使用
	 * */
	@SuppressWarnings("hiding")
	public <T> String getRealColumnName(Class<T> clazz,String columnName){
		try {
			Field field = clazz.getDeclaredField(columnName);
			if(field.isAnnotationPresent(Column.class)){
				Column column = field.getAnnotation(Column.class);
				if(column != null && column.name() != null && column.name().trim().length() > 0){
					return column.name();
				}else{
					return columnName;
				}
			}
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	/**取得實際欄位名稱
	* 限方法設定@Column時使用
	 * */
	@SuppressWarnings("hiding")
	public <T> String getRealColumnNameByMethod(Class<T> clazz,String columnName){ 
		logger.info("columnName=" + columnName);
		try {
			logger.info("methodName="+ getMethodName(columnName, true));
			Method method = clazz.getDeclaredMethod(getMethodName(columnName, true));
			if(method.isAnnotationPresent(Column.class)){
				Column column = method.getAnnotation(Column.class);
				if(column != null && column.name() != null && column.name().trim().length() > 0){
					logger.info("column is " + column.name());
					return column.name();
				}else{
					logger.info("column is " + columnName);
					return columnName;
				}
			}else{
				logger.info("is not isAnnotationPresent.");
			}
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	/**取得實際表格名稱*/
	@SuppressWarnings("hiding")
	public <T> String getRealTableName(Class<T> clazz){
		try {
			if(clazz.isAnnotationPresent(Table.class)){
				Table table = clazz.getAnnotation(Table.class);
				if(table != null && table.name() != null && table.name().trim().length() > 0){
					return table.name();
				}else{
					return clazz.getSimpleName();
				}
			}
		} catch (Exception e) {}
		return null;
	}
	
	/**取得方法名稱
	 * @param fieldName 屬性名稱<br/>
	 * @param isGetter  是否為getter方法<br/>
	 * example1:getMethodName("user",false);return value is setUser;<br/>
	 * example2:getMethodName("user",true); return value is getUser;<br/>
	 * */
	public String getMethodName(String fieldName, boolean isGetter) {
		if(fieldName==null) return "";

		char[] str = new char[fieldName.length()+3];
		str[0] = isGetter?'g':'s';
		str[1] = 'e';
		str[2] = 't';
		fieldName.getChars(0, fieldName.length(), str, 3);
		str[3] = Character.toUpperCase(str[3]);
		
		return new String(str);
	}

	/**取得物件的屬性值
	 * @param t         物件實例<br/>
	 * @param fieldName 屬性名稱<br/>
	 * #須有對應的getter方法
	 * */
	@SuppressWarnings("hiding")
	public <T> Object getValue(T t,String fieldName)throws Exception{
		Method m = t.getClass().getDeclaredMethod(getMethodName(fieldName,true));
		return m.invoke(t);
	}
	
	/**查詢筆數
	 * hibernate4 Only
	 * */
	public int countBySQL(String sql ,List<Object> params){
		int result = 0;
		Session session = this.sessionFactory.getCurrentSession();
		sql = "select count(*) CNT from (" + sql + ") T";
		SQLQuery countQuery = session.createSQLQuery(sql.toString());
		countQuery.addScalar("CNT", IntegerType.INSTANCE);
		for(int i = 0;i < params.size();i++){
			countQuery.setParameter(i, params.get(i));
		}
		countQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<?> countResult = countQuery.list();
		if(countResult!=null && countResult.size()>0) {
			Object o = countResult.get(0);
			if(o instanceof Map){
				Map<String,?> m = (Map<String,?>)o;
				Number recordCount =(Number) m.get("CNT");
				result = recordCount.intValue();
			}
		}
		return result;
	}
	
	/**查詢筆數*/
	public int count(final DetachedCriteria detachedCriteria)throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = detachedCriteria.getExecutableCriteria(session);
		Object o = criteria.setProjection(Projections.rowCount()).uniqueResult();
		if(o instanceof Integer){
			return Integer.parseInt(o.toString());
		}else{
			return 0;
		}
	}
	/**查詢加總*/
	public <N>N sum(final DetachedCriteria detachedCriteria,final String field,final Class<N> clazz) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = detachedCriteria.getExecutableCriteria(session);
		Object o = criteria.setProjection(Projections.sum(field)).uniqueResult();
		N result = null;
		if(clazz.isInstance(o)){result = clazz.cast(o);}
        return result;
	} 
	/**查詢最大值*/
	public <N>N max(final DetachedCriteria detachedCriteria,final String field,final Class<N> clazz) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = detachedCriteria.getExecutableCriteria(session);
		Object o = criteria.setProjection(Projections.max(field)).uniqueResult();
		N result = null;
		if(clazz.isInstance(o)){result = clazz.cast(o);}
        return result;
	} 
	
	/**重設結束時間*/
	public Date resetEndDate(Date src){
		Calendar c = Calendar.getInstance();
		c.setTime(src);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTime();
	}
	
	/**重設開始時間*/
	public Date resetBeginDate(Date src){
		Calendar c = Calendar.getInstance();
		c.setTime(src);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	
	/**查詢最大值*/
	public <N>N avg(final DetachedCriteria detachedCriteria,final String field,final Class<N> clazz) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = detachedCriteria.getExecutableCriteria(session);
		Object o = criteria.setProjection(Projections.avg(field)).uniqueResult();
		N result = null;
		if(clazz.isInstance(o)){result = clazz.cast(o);}
        return result;
	}
	
	
}
