$(document).ready(function(){
	// About to set listeners to all buttons with an id editbt
	 var buttons = document.getElementsByClassName('editbt');
	 var size = buttons.length;
	 var current_button=null;
	 while(size>0) {
		current_button = buttons[size-1];
		current_button.addEventListener('click', function () {
		location.href="/CouponsProject/controller/edit_coupon?c_id="+this.name;
		});
		size = size-1;
	 }
	 
	// About to set listeners to all buttons with an id removebt
	 var buttons = document.getElementsByClassName('removebt');
	 var size = buttons.length;
	 var current_button=null;
	 while(size>0) {
		current_button = buttons[size-1];
		current_button.addEventListener('click', function () {
		location.href="/CouponsProject/controller/deletecoupon?c_id="+this.name;
		});
		size = size-1;
	 }
	 
	 
	 
});

// Function to return if a number is an Integer
function isInt(value) {
	if (value % 1 === 0) {
		return true;
	}
		else {
			return false;
		}
}

//Function to return if a number is a String
function isString(value) {
	if (typeof value =='string') {
		return true;
	}
	else {
		return false;
	}
}

//Function to return if a number is a Date
// Format yyyy-mm-dd hh:hh
function isDate(value) {
	var str = value;
	//alert(value.substring(0,4));
	if (isInt(str.substring(0,4))) {
		if( (str.charAt(4)) == '-' ) {
			if( isInt(str.substring(5,6)) ) {
				if(str.charAt(7) == '-' ) {
					if(isInt(str.substring(8,9)) ) {
						if(isInt(str.substring(11,12)) ) {
							if(str.charAt(13) == ':' ) {
								if(isInt(str.substring(14,15)) ) {
									return true;
								}
							}
						}
					}
				}
			}
		}
	}
	else {
		return false;
	}
}




 // Validate AddCoupon Form Submit 
function addCouponValidateForm() {
	var id,name,des,date;
	id = document.forms["addForm"]["c_id"].value;
	name = document.forms["addForm"]["c_name"].value;
	des = document.forms["addForm"]["c_des"].value;
	date = document.forms["addForm"]["exp_date"].value;
	if ( isInt(id) ) {
		if (isString(name)) {
			if (isString(des)) {
				if(isDate(date)) {
					return true;
				}
			}
		}
	}
		return false;
	}


// Validate editCoupon Form Submit 
function editCouponValidateForm() {
	var a,b,c,d;
	
	
	
}