package scorer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
	@Id
	private String name;
	private List <Score> scores;
	
	public User (String name){
		this.name = name;
		scores = new ArrayList <Score> ();
	}
	
	public User (){
		
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Add a new score to the user
	 * @param s the score to add
	 */
	public void addScore (Score s){
		scores.add(s);
	}
}

