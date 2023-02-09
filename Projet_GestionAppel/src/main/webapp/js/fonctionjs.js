/**
 * 
 */
function deactivateValider(){
	document.getElementById("btn_valider").disabled=true;
}
function onlyStatus(){
	var value=this.textContent;
	console.log(this);
}
/*var checks=document.getElementsByTagName("tr");
 var tbl = document.getElementById("table");
 for(i=0;i<checks.length;i++){
	 checks[i].onclick = function(e)
{
	console.log(e.target.innerHTML);
  console.log(e.target.cellIndex);
  console.log(e.target.parentElement.rowIndex);
  console.log(e);
}
 }*/
 
 function onlyCheck(name,id){
	 /*console.log(name);
	 console.log(i);*/
	 var table = document.getElementById("table");
	 for (var i = 1, row; row = table.rows[i]; i++) {
		 /*console.log(i);*/
		 if(i==id){
			 /*console.log(table.rows[i].cells[5].firstChild.name);*/
		 	/*table.rows[i].cells[6].innerHTML="<input type=\"checkbox\" value=\"4\" name=\"retard\" onchange=\"onlyCheck('presence',"+id+") checked>";}
			*/
			/*console.log(table.rows[i].cells[6].firstChild.checked);*/
			
			/*table.rows[i].cells[6].innerHTML="<input type=\"checkbox\" value=\"4\" name=\"Retard\" onchange=\"onlyCheck(\"presence\","+id+") checked>";}
			*/
			
			
		
			if(table.rows[i].cells[6].firstChild.name==name){
				
				table.rows[i].cells[5].firstChild.checked=false;
				table.rows[i].cells[7].firstChild.checked=false;
			}
			else if(table.rows[i].cells[7].firstChild.name==name){
				
				table.rows[i].cells[6].firstChild.checked=false;
				table.rows[i].cells[5].firstChild.checked=false;
			}
			else if(table.rows[i].cells[5].firstChild.name==name){
				
				table.rows[i].cells[6].firstChild.checked=false;
				table.rows[i].cells[7].firstChild.checked=false;
			}
			}
		}
 	}

 
 document.addEventListener("DOMContentLoaded", () => {

	document.getElementById("table").addEventListener("change",deactivateValider);
	
	/*document.getElementById("table").addEventListener("change",onlyStatus);*/
	
	
	})