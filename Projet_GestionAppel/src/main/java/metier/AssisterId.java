package metier;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Clé composite ValideId.
 */
@Embeddable
public class AssisterId  implements Serializable{
	
	/**
	 * Propriétés.
	 */
	@Column(name = "CodeUsers")
	private int codeUsers;

	@Column(name = "CodeSeance")
	private int codeSeance;

	public AssisterId(int codeUsers, int codeSeance) {
		super();
		this.codeUsers = codeUsers;
		this.codeSeance = codeSeance;
	}

	public int getCodeUsers() {
		return codeUsers;
	}

	public void setCodeUsers(int codeUsers) {
		this.codeUsers = codeUsers;
	}

	public int getCodeSeance() {
		return codeSeance;
	}

	public void setCodeSeance(int codeSeance) {
		this.codeSeance = codeSeance;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codeSeance, codeUsers);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AssisterId other = (AssisterId) obj;
		return codeSeance == other.codeSeance && codeUsers == other.codeUsers;
	}

	@Override
	public String toString() {
		return "AssisterId [codeUsers=" + codeUsers + ", codeSeance=" + codeSeance + "]";
	}

	
	

}
