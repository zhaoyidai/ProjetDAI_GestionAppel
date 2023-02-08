/**
 * 
 */
function deactivateValider(){
	document.getElementById("btn_valider").disabled=true;
}

 
 
 document.addEventListener("DOMContentLoaded", () => {

	document.getElementById("table").addEventListener("change",deactivateValider);
	
	})