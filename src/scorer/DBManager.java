package scorer;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DBManager {
	private static final String PERSISTENCE_UNIT_NAME = "scorer";
	private static EntityManagerFactory factory;
	
	public static void insertScore (String userName, Score score){
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		
		//Find user
		User u = em.find(User.class, userName);
		
		//Create user if needed
		if (u == null){
			System.out.println("usuario creado");
			u = new User(userName);
			em.persist(u);
		}
		
		//Add score in a transaction
		em.getTransaction().begin();
		u.addScore(score);
		em.getTransaction().commit();
		em.close();		
		
		System.out.println("Puntuación insertada!");
	}
	
	public static List<User> getAllusers (){
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();		

		//Do the query
		Query q = em.createQuery("SELECT u FROM User u");
		List <User> users = q.getResultList();		
		
		return users;
	}

}
