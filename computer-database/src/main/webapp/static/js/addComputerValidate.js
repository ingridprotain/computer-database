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
	        		"regex": /^(0[1-9]|1\d|2\d|3[01])\/(0[1-9]|1[0-2])\/(19|20)\d{2}$/
	        	},
	        	discontinued: {
	        		"regex": /^(0[1-9]|1\d|2\d|3[01])\/(0[1-9]|1[0-2])\/(19|20)\d{2}$/
	        	}
	        },
	        
	        
	        // Specify the validation error messages
	        messages: {
	        	computerName: strings['form.check.name'],
	        	introduced: strings['form.check.date'],
	        	discontinued: strings['form.check.date']
	        },
	   
	        submitHandler: function(form) {
	            form.submit();
	        }
	    });

	  });