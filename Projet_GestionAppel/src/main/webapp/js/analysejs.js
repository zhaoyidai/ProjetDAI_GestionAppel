/**
 * 
 */
	
function afficheCours ()
	{
	
	// Objet XMLHttpRequest.
	var xhr = new XMLHttpRequest();
	var e = document.getElementById("etudiants");
	
	var value = e.value;

	// Requête au serveur avec les paramètres éventuels.
	xhr.open("GET","CtrlAnalyse?idu="+value);

	// On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
	xhr.onload = function()
		{
		// Si la requête http s'est bien passée.
		if (xhr.status === 200)
			{
			// Elément html que l'on va mettre à jour.
			
			var jsontext=JSON.parse(xhr.responseText);
			
			var elt = document.getElementById("cours");
			elt.innerHTML="";
			for(i=0;i<jsontext.length;i++){
				
				elt.insertAdjacentHTML("beforeend","<input type=\"checkbox\" name=\"checkcours\" value="+jsontext[i]["idCours"]+">"+jsontext[i]["nomCours"]);
			}
			
			}
		};

	// Envoie de la requête.
	xhr.send();
	}

function afficheNb(){
	// Objet XMLHttpRequest.
	var xhr = new XMLHttpRequest();
	var e = document.getElementById("etudiants");
	var value = e.value;
	var ands="";
	var checks=document.getElementsByName("checkcours");
	for(i=0;i<checks.length;i++){
		if(checks[i].checked){
			ands+="&idc="+checks[i].value;
			
		}
	}
	
	// Requête au serveur avec les paramètres éventuels.
	xhr.open("GET","CtrlAssister?idu="+value+ands);

	// On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
	xhr.onload = function()
		{
		// Si la requête http s'est bien passée.
		if (xhr.status === 200)
			{
			// Elément html que l'on va mettre à jour.
			
			var jsontext=JSON.parse(xhr.responseText);
			var presences=jsontext["presences"].length;
			var retards=jsontext["retards"].length;
			var absences=jsontext["absences"].length;
			var taux=(absences/(absences+retards+presences))*100
			var elt = document.getElementById("table");
			elt.innerHTML="<tr><td>Numéro_étudiant</td><td>Nom</td><td>Prénom</td><td>Présence</td><td>Retard</td><td>Absent</td><td>Taux d'Absence</td></tr>";
			elt.insertAdjacentHTML("beforeend","<tr>"+
				"<td>"+jsontext["idU"]+"</td><td>"+jsontext["nomU"]+"</td><td>"+jsontext["prenomU"]+"</td><td>"+presences+"</td><td>"+retards+"</td><td>"+absences+"</td><td>"+taux.toFixed(2)+"%</td></tr>");
			
			
			}
		};

	// Envoie de la requête.
	xhr.send();
}
	
document.addEventListener("DOMContentLoaded", () => {

	document.getElementById("etudiants").addEventListener("change",afficheCours);
	
	document.getElementById("cours").addEventListener("change",afficheNb);
});
