function myFunction(){
    alert("Button Pressed");
    }
function volLogin() {
}

function changeActiveMenu(menuname) {


	var myElement = document.getElementById('top_menu');
	

   var myMenuList = myElement.getElementsByTagName('li');
   
  
   for (i = 0; i < myMenuList.length; i++) {
   if ((myMenuList[i].id == menuname) && (myMenuList[i].id != 'menu_right'))
   {
   		myMenuList[i].classList.add('active');
   		myMenuList[i].classList.remove('not-active');
   		}
   else
   {
   		myMenuList[i].classList.remove('active');
   		myMenuList[i].classList.add('not-active');
   		}
   		}
}

function openTranslate(articleID,userID) {
    window.location.assign("/translate/article?articleid="+articleID+"&userid="+userID);
    
}

function openNav() {
    document.getElementById("menu-side-bar").style.width = "200px";
    document.getElementById("main-menu").style.marginLeft = "200px";
}
function closeNav() {
    document.getElementById("menu-side-bar").style.width = "0";
	document.getElementById("main-menu").style.marginLeft= "0";
}

function loginDisplay(username,loginerrormess) {
    if(username == "") {
  				
        document.getElementById("login-icon").style.visibility='visible';
        document.getElementById("login-icon").style.display='block';
        document.getElementById("logged-user-icon").style.visiblity='hidden';
        document.getElementById("logged-user-icon").style.display='none';

		if (loginerrormess == "Success") {
	        document.getElementById("auth-error").style.visibility='hidden';
			document.getElementById("auth-error").style.display='none';
	        }
		else {
			document.getElementById("auth-error").style.visibility='visible';
			document.getElementById("auth-error").style.display='block';
		}
		
    } else {

        document.getElementById("login-icon").style.visibility='hidden';
        document.getElementById("login-icon").style.display='none';
        document.getElementById("logged-user-icon").style.visiblity='visible';
        document.getElementById("logged-user-icon").style.display='block';
    }
}