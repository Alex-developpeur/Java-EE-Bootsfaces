console.log("tests");

$(function(){
	// Ajouter la class active au menu de navigation :
    var loc = location.pathname;
    var id = loc.split('/')[3];
    if(id === "index.xhtml") {
    	$('#index').addClass('active');
    } else {
    	$('#' + id).addClass('active');
    }
    
    
})

// Suppression de la class has-error:
function removeClassError() {
	$('.has-error').each(function(index, obj) {
		if(obj !== undefined) {
			obj.classList.remove("has-error");
		}
	});
}

//Verification de la validité des champs texte:
function validFieldSize(id, min, max) {
	var element = document.getElementById(id);
	if(element.value.length < min || element.value.length > max) {
		element.classList.remove("has-success");
	} else {
		element.classList.add("has-success");
	}
}

function validNom() {
	var nom = document.getElementById('input_formId:nom');
	if(nom.value.length < 3 || nom.value.length > 50) {
		nom.classList.remove("has-success");
	} else {
		nom.classList.add("has-success");
	}
}

function validEmail() {
	var email = document.getElementById('input_formId:email');
	var re = new RegExp('^\\S+@[\\w-.]+\\.\\w+$');
	if(re.test(email.value)) {
		email.classList.add("has-success");		
	} else {
		email.classList.remove("has-success");
	}
}

function validMdp() {
	var mdp = document.getElementById('input_formId:mdp');
	var confirmeMdp = document.getElementById('input_formId:confirmeMdp');

	if(mdp.value.length < 6 || mdp.value.length > 100) {
		mdp.classList.remove("has-success");
		confirmeMdp.classList.remove("has-success");
	} else {
		mdp.classList.add("has-success");
		if(confirmeMdp.value === mdp.value) {
			confirmeMdp.classList.add("has-success");
		} else {
			confirmeMdp.classList.remove("has-success");
		}
	}
}

function validSIRET() {
	var siret = document.getElementById('input_formId:siret');
	var re = new RegExp('^(\\d{3} {0,1}){4}\\d{2}$');
	if(re.test(siret.value)) {
		siret.classList.add("has-success");		
	} else {
		siret.classList.remove("has-success");
	}
}

function validNumeroTVA() {
	var tva = document.getElementById('input_formId:tva');
	var re = new RegExp('^FR\\d{11}$');
	if(re.test(tva.value)) {
		tva.classList.add("has-success");		
	} else {
		tva.classList.remove("has-success");
	}
}

function validCodePostal() {
	var codePostal = document.getElementById('input_formId:codePostal');
	var re = new RegExp('^\\d{2} {0,1}\\d{3}$');
	if(re.test(codePostal.value)) {
		codePostal.classList.add("has-success");		
	} else {
		codePostal.classList.remove("has-success");
	}
}

function validFaxTel(id) {
	var element = document.getElementById(id);
	var re = new RegExp('^(\\d{2} {0,1}){4}\\d{2}$');
	if(re.test(element.value)) {
		element.classList.add("has-success");		
	} else {
		element.classList.remove("has-success");
	}
}

function validProfil() {
	removeClassError();
	validNom();
	validEmail();
}

function validPassword() {
	removeClassError();
	validMdp();
}

function validUser() {
	validProfil();
	validMdp();
}

function validEntreprise() {
	removeClassError();
	validFieldSize('input_formId:raisonSociale', 3, 50);
	validSIRET();
	validNumeroTVA();
	validFieldSize('input_formId:rue', 3, 50);
	validCodePostal();
	validFieldSize('input_formId:ville', 1, 50);
	validFieldSize('input_formId:pays', 3, 50);
	validEmail();
	validFaxTel('input_formId:tel');
	validFaxTel('input_formId:fax');
}

function validParticulier() {
	removeClassError();
	validFieldSize('input_formId:nom', 3, 50);
	validFieldSize('input_formId:prenom', 3, 100);
	validFieldSize('input_formId:rue', 3, 50);
	validCodePostal();
	validFieldSize('input_formId:ville', 1, 50);
	validFieldSize('input_formId:pays', 3, 50);
	validEmail();
	validFaxTel('input_formId:tel');
	validFaxTel('input_formId:fax');
}
