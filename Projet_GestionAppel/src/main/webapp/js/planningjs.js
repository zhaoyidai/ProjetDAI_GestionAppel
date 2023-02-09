/**
 * 
 */

 function afficheEdt ()
	{
	
	// Objet XMLHttpRequest.
	var xhr = new XMLHttpRequest();
	var e = document.getElementById("semaine");
	
	var value = e.value;

	// Requête au serveur avec les paramètres éventuels.
	xhr.open("GET","CtrlPlanningJson?week="+value);

	// On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
	xhr.onload = function()
		{
		// Si la requête http s'est bien passée.
		if (xhr.status === 200)
			{
			// Elément html que l'on va mettre à jour.
			var elt = document.getElementById("edt");
			var jsontext=JSON.parse(xhr.responseText);
			
			/*for(i=0;i<jsontext.length;i++){
				elt.innerHTML+="<p>"+jsontext[i]["idSeance"]+"</p>";
				elt.insertAdjacentHTML("beforeend","<a href="+jsontext[i]["lien"]+">ficheappel</a>");
			}*/
			return jsontext;
			}
		};

	// Envoie de la requête.
	xhr.send();
	}
	
	
document.addEventListener("DOMContentLoaded", () => {

	document.getElementById("semaine").addEventListener("change",afficheEdt);
	

});
