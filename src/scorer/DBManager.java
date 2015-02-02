package scorer;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DBManager {
	private static final String PERSISTENCE_UNIT_NAME = "Scorer";
	private static EntityManagerFactory factory;
	
	/**
	 * Insert a score in the database. It creates the user if it doesn't exists.
	 * @param userName the name of the user
	 * @param score the score
	 */
	public static void insertScore (String userName, Score score){
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
				
		//Find user
		ScorerUser u = em.find(ScorerUser.class, userName);
		
		//Create user if needed
		if (u == null){
			u = new ScorerUser(userName);
			em.persist(u);
		}
		
		//Add score in a transaction
		em.getTransaction().begin();
		u.addScore(score);
		u.addGame(score.getGame());
		em.getTransaction().commit();
		em.close();		
	}
	
	/**
	 * Gets all users.
	 * @return a list with all the data of every user
	 */
	public static List<ScorerUser> getAllUsers (){
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();		

		//Do the query
		Query q = em.createQuery("SELECT u FROM ScorerUser u");
		return q.getResultList();		
	}
	
	/**
	 * Gets all users that play a game.
	 * @param game the game to find
	 * @return a list with all the users that play <code>game</code>
	 */
	public static List<ScorerUser> getUsersByGame (String game){
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();	
		
		//Get all users
		Query q = em.createQuery("SELECT u FROM ScorerUser u");
		List <ScorerUser> allUsers = q.getResultList();
		
		//Remove all users that doesn't play game
		for (Iterator <ScorerUser> iterator = allUsers.iterator(); iterator.hasNext();){
			ScorerUser su = iterator.next();
			
			if (!su.playsGame(game)){
				iterator.remove();
			}				
		}		
		return allUsers;	
	}
	
	/**
	 * Gets a user.
	 * @param userName the user name to find.
	 * @return
	 */
	public static ScorerUser getUserByName (String userName){
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();	
		
		return em.find(ScorerUser.class, userName);		
	}

}
