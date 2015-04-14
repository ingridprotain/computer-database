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
	        	name: {
	        		"required": true
	        	},
	        	introduced: {
	        		"regex": strings['form.regex']
	        	},
	        	discontinued: {
	        		"regex": strings['form.regex']
	        	}
	        },
	        
	        
	        // Specify the validation error messages
	        messages: {
	        	name: strings['form.check.name'],
	        	introduced: strings['form.check.date'],
	        	discontinued: strings['form.check.date']
	        },
	   
	        submitHandler: function(form) {
	            form.submit();
	        }
	    });

	  });