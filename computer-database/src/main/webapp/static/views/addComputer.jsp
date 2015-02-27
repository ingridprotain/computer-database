<!DOCTYPE html>
<%@taglib uri="/static/tag/linkTag.tld" prefix="l" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="static/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="static/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="static/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <l:link name="Application - Computer Database" target="dashboard" myClass="navbar-brand"/>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1>Add Computer</h1>
                    
                    <c:forEach items="${ errors }" var="error">
                    	${ error } <br />
                    </c:forEach>
                    
                    <form id="addComputer" action="addComputer" method="POST">
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <input type="text" class="form-control" id="computerName" name="computerName" placeholder="Computer name">
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <input type="date" class="form-control" id="introduced" name="introduced" placeholder="Introduced date">
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <input type="date" class="form-control" id="discontinued" name="discontinued" placeholder="Discontinued date">
                            </div>
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <select class="form-control" id="companyId" name="companyId" >
                                	<option value="0">--</option>
                                	<c:forEach items="${ companiesDTO }" var="cDTO">
                                    	<option value="${ cDTO.id }">${ cDTO.name }</option>
                                   	</c:forEach>
                                </select>
                            </div>                  
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="Add" class="btn btn-primary">
                            or
                            <a href="dashboard.html" class="btn btn-default">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
<script src="static/js/jquery.min.js"></script>
<script src="static/js/jquery.validate.min.js"></script>
<script>
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
	        	introduced: "Please enter a correct date to the format mm/dd/YYY",
	        	discontinued: "Please enter a correct date to the format mm/dd/YYY"
	        },
	   
	        submitHandler: function(form) {
	            form.submit();
	        }
	    });

	  });
</script>
</html>