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
});

