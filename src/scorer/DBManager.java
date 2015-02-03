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
		
		//Add score in a transaction
		em.getTransaction().begin();
		u.addScore(score);
		u.addGame(score.getGame());
		em.getTransaction().commit();
		em.close();		
	}
	
	/**
	 * Create a new user if the user {@code userName} doesn't exists.
	 * @param userName the name of the new user
	 * @param password the password of the new user
	 * @return if the user was created successfully
	 */
	public static boolean createUser (String userName, String password){
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		boolean ok = true;
		
		//Find user
		ScorerUser u = em.find(ScorerUser.class, userName);
		
		//Create it if it doesn't exists
		if (u == null){
			System.out.println("Creating " + userName);
			ScorerUser newUser = new ScorerUser(userName, password);
			em.getTransaction().begin();
			em.persist(newUser);		
			em.getTransaction().commit();
		}else{
			System.out.println(userName + " already exist");
			ok = false;
		}
		
		em.close();
		return ok;
	}
	
	/**
	 * Checks if a password is correct.
	 * @param userName the name of the user
	 * @param password the password to check if it belongs to the user
	 * @return a String with 3 possible values: "User doesn't exist", "Ok" or "Incorrect password"
	 */
	public static String validatePassword (String userName, String password){
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		String message;
		
		//Find user
		ScorerUser u = em.find(ScorerUser.class, userName);
		
		//If it doesn't exists return a message
		if (u == null){
			message = "User doesn't exist";
		}else{
			//Validate password
			if (u.getPassword().equals(password))
				message = "Ok";
			else
				message = "Incorrect password";
		}
		
		em.close();
		return message;
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
		
		em.close();
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
		
		em.close();
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
