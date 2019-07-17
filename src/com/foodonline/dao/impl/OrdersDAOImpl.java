package com.foodonline.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import com.foodonline.dao.OrdersDAO;
import com.foodonline.entity.Orders;

public class OrdersDAOImpl implements OrdersDAO {

	SessionFactory sessionFactory;	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	

	//鍒犻櫎璁㈠崟瀵硅薄
	@Override
	public void deleteOrders(Orders orders) {
		Session session=sessionFactory.getCurrentSession();		
		session.delete(orders);
	}

	//鏍规嵁璁㈠崟缂栧彿鍔犺浇璁㈠崟瀵硅薄
	@Override
	public Orders getOrdersByOid(int oid) {
		Session session=sessionFactory.getCurrentSession();
		return (Orders)session.get(Orders.class, oid);
	}

	//鑾峰彇鎸囧畾鐢ㄦ埛鐨勮鍗曞垪琛�
	@Override
	public List getOrdersByUserId(int userId) {
		Session session=sessionFactory.getCurrentSession();
		Criteria c=session.createCriteria(Orders.class);
		c.add(Restrictions.eq("users.id", userId));		
		return c.list();
	}

	//鏇存柊璁㈠崟瀵硅薄
	@Override
	public void updateOrders(Orders orders) {
		Session session=sessionFactory.getCurrentSession();
		session.update(orders);
	}

	//鑾峰彇鎵�鏈夎鍗�
	@Override
	public List getAllOrders(int page) {		
		Session session=sessionFactory.getCurrentSession();
		Criteria c=session.createCriteria(Orders.class);
		c.setFirstResult(6*(page-1));
		c.setMaxResults(6);
		return c.list();
	}
	
	//缁熻鎵�鏈夎鍗曟�绘暟
	@Override
	public Integer getCountOfAllOrders() {
		Integer count=null;
		try{
		Session session=sessionFactory.getCurrentSession();
		String hql="select count(o) from Orders o";
		Query query=session.createQuery(hql);
		count= Integer.parseInt(query.uniqueResult().toString()) ;
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}
	
	
	//鑾峰彇婊¤冻鏉′欢銆佹寚瀹氶〉鏄剧ず鐨勮鍗曞垪琛�
	@Override
	public List getOrdersByCondition(Orders condition, int page) {
		Session session=sessionFactory.getCurrentSession();
		Criteria c=session.createCriteria(Orders.class);
		if(condition!=null){
			if((condition.getOid()!=null) && (condition.getOid()>0)){
				//鎸夎鍗曞彿杩涜绛涢��
				c.add(Restrictions.eq("oid", condition.getOid()));
			}
			if((condition.getOrderState()!=null) && !condition.getOrderState().equals("") && !condition.getOrderState().equals("鍏ㄩ儴")){
				//鎸夎鍗曠姸鎬佽繘琛岀瓫閫�
				c.add(Restrictions.eq("orderState", condition.getOrderState()));
			}
		}
		c.setFirstResult(6*(page-1));
		c.setMaxResults(6);
		return c.list();
	}
	
	@Override
	public Integer getCountOfOrders(Orders condition) {
		Session session=sessionFactory.getCurrentSession();
		Criteria c=session.createCriteria(Orders.class);
		if(condition!=null){
			if((condition.getOid()!=null) && (condition.getOid()>0)){
				//鎸夎鍗曞彿杩涜绛涢��
				c.add(Restrictions.eq("oid", condition.getOid()));
			}
			if((condition.getOrderState()!=null) && !condition.getOrderState().equals("") && !condition.getOrderState().equals("鍏ㄩ儴")){
				//鎸夎鍗曠姸鎬佽繘琛岀瓫閫�
				c.add(Restrictions.eq("orderState", condition.getOrderState()));
			}
		}
		return c.list().size();
	}	
	
}
