package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;




public class BD {
	
	//Propriétés
	private static String URL= "jdbc:mysql://localhost:3307/db_21902975"; 
	private static String LOGIN= "21902975";
	private static String PWD= "00934A";
	
	private static Connection cx;
	
	
	
	
	//Methode de connextion a la bdd
	public static void connection() throws Exception{
		
		//Chargement du pilote
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException cnfe) {
			throw new Exception("Erreur BD.connexion() - Chargement du pilote - " + cnfe.getMessage());
		}
		try {
			cx=DriverManager.getConnection(URL,LOGIN,PWD);
		} catch (SQLException sqle) {
			throw new Exception ("Erreur BD.connexion() - Ouverture à la connexion - "+ sqle.getMessage());
		}
	}
	
	
	

	//Methode d'insertion du message d'or dans la bdd
	public static int enregistrer() throws Exception{
		
		//si la connexion a la bdd est pas encore faite , je me connecte sinon je continue
		if(cx == null) {
			BD.connection();
		}
		
		//Requete SQL d'enregistrement
		String sql="INSERT INTO message (pseudo, Texte) VALUES(?,?)";
		
		//Ouverture espace de requete
		int nb;
		try(PreparedStatement st=cx.prepareStatement(sql)){
			//Insertion des ? parametres dans la requete
			//st.setString(1,msg.getPseudo());
			//st.setString(2, msg.getMessage());
			
			nb =st.executeUpdate();
		}
		catch(SQLException sqle) {
			throw new Exception ("Erreur BD.enregistrer() - Problème à l'insertion - "+ sqle.getMessage());
		}
		return nb;
	}
	
	
	//Methode d'insertion du message d'or dans la bdd
	public static int modifier() throws Exception{
		
		//si la connexion a la bdd est pas encore faite , je me connecte sinon je continue
		if(cx == null) {
			BD.connection();
		}
		
		//Requete SQL d'enregistrement
		String sql="UPDATE message SET pseudo = ?, texte = ? WHERE numMsg = ?";
		
		//Ouverture espace de requete
		int nb;
		try(PreparedStatement st=cx.prepareStatement(sql)){
			//Insertion des ? parametres dans la requete
			//st.setString(1,msg.getPseudo());
			//st.setString(2, msg.getMessage());
			//st.setLong(3,msg.getId());
			
			nb =st.executeUpdate();
		}
		catch(SQLException sqle) {
			throw new Exception ("Erreur BD.enregistrer() - Problème à la modification - "+ sqle.getMessage());
		}
		return nb;
	}
	
	
	
	//Methode d'insertion du message d'or dans la bdd
		public static int supprimer(ArrayList<Integer>listeId) throws Exception{
			
			//si la connexion a la bdd est pas encore faite , je me connecte sinon je continue
			if(cx == null) {
				BD.connection();
			}
			
			//Requete SQL d'enregistrement
			String sql=" DELETE FROM message where numMsg= ? ";
			
			//Ouverture espace de requete
			int nb = 0;
			
			   try(PreparedStatement st = cx.prepareStatement(sql)){
				   for(int i=0;i<listeId.size();i++) {
			                	st.setInt(1,listeId.get(i).intValue());
			        			nb =st.executeUpdate();

				   }
		        }
		        catch(SQLException sqle){
		            throw new Exception("Exception bd.lireMessage() - Lecture des Messages - " + sqle.getMessage());
		        }
			   return nb;
		}
	
	
	

	
	
/**
 * 
 * ZoneTest en JAVA
 */
	public static void main(String[] args) {
		try {
			BD.connection();
			System.out.println("Chargement et connexion réussi");
			//MessageDor m = new MessageDor("Fin journée", "DFOJFZOEFJREFO");
			//BD.enregistrer(m);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	

	
	

}//fin classe