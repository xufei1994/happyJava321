package value;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ValueBean {
	private String name;
	private int age;
	private List<String> interest;
	private Set<String> city;
	private Map<String,Double> score;
	private Properties db;
	
	public Properties getDb() {
		return db;
	}

	public void setDb(Properties db) {
		this.db = db;
	}

	public Map<String, Double> getScore() {
		return score;
	}

	public void setScore(Map<String, Double> score) {
		this.score = score;
	}

	public Set<String> getCity() {
		return city;
	}

	public void setCity(Set<String> city) {
		this.city = city;
	}

	public List<String> getInterest() {
		return interest;
	}

	public void setInterest(List<String> interest) {
		this.interest = interest;
	}

	public ValueBean() {
		System.out.println("ValueBean()");
	}
	
	@Override
	public String toString() {
		return "ValueBean [name=" + name + ", age=" + age + ", interest=" + interest + ", city=" + city + ", score="
				+ score + ", db=" + db + "]";
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
