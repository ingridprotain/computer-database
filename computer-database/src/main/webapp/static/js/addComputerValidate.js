$(document).ready(function(){
		
		jQuery.validator.addMethod(
		  "regex",
		   function(value, element, regexp) {
		       if (regexp.constructor != RegExp)
		          regexp = new RegExp(regexp);
		       else if (regexp.global)
		          regexp.lastIndex = 0;
		          return this.optional(element) || regexp.test(value);
		   },"erreur expression reguliere"
		);
		
		$("#addComputer").validate({
	        rules: {
	        	computerName: {
	        		"required": true
	        	},
	        	introduced: {
	        		"regex": /^(0[1-9]|1[0-2])\/(0[1-9]|1\d|2\d|3[01])\/(19|20)\d{2}$/
	        	},
	        	discontinued: {
	        		"regex": /^(0[1-9]|1[0-2])\/(0[1-9]|1\d|2\d|3[01])\/(19|20)\d{2}$/
	        	}
	        },
	        
	        // Specify the validation error messages
	        messages: {
	        	computerName: "Please enter a computer name",
	        	introduced: "Please enter a correct date to the format mm/dd/YYYY",
	        	discontinued: "Please enter a correct date to the format mm/dd/YYYY"
	        },
	   
	        submitHandler: function(form) {
	            form.submit();
	        }
	    });

	  });