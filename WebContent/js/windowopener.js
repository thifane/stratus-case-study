
function openwindow(frm) {
	window.open("./lookup?frmname="+frm,"_blank","height=400,width=400,status=yes,toolbar=no,menubar=no,location=no,resizable=no");
}

function changeparentFrmRegistration(o){
		
	//alert('registration');
	var pos = document.getElementById(o.id).href.indexOf("=");
	window.opener.document.getElementById('registrationfrm_peopleManager').value = document.getElementById(o.id).href.substring(pos+1);
	self.close();
}

function changeparentFrmAchvment(o){
	
	//alert('achievement');
	var pos = document.getElementById(o.id).href.indexOf("=");
	
	window.opener.document.getElementById('enterAchievement_approverEmployee').value = document.getElementById(o.id).href.substring(pos+1);
	window.close();
}

/*function getUrlVars() {
    var vars = {};
    alert(location.href);
    //var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi,    	
    function(m,key,value) {
      vars[key] = value;
    });
    return vars;
  }*/