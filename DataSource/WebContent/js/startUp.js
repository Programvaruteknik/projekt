angular.element(document).ready(function() {

	window.alert = function(msg, header, type) {

		if(type === undefined)
		{
			type = "alert";
		}
		
		var div = $(
		"<div data-alert class='alert-box " + type + "'>" +
			"<a class='close'>&times;</a>" +
			"<h4>" + header + "</h4>" + msg +
		"</div>"
		);
		
		div.delay( 7000 ).slideUp( 1000 );
		div.appendTo("#alertBar")
		div.find(".close").on("click", function()
		{
			$(this).parent().remove();
		});
		
		return;
	};
	
});