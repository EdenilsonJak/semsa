PrimeFaces.locales['pt_BR'] = {
	messages : {
		'{org.hibernate.validator.constraints.NotBlank.message}' : '{0} não pode esta em branco'
	}
}

PrimeFaces.validator.NotBlank = {
	
	MESSAGE_ID : '{org.hibernate.validator.constraints.NotBlank.message}',
		
	validate : function(element, value) {
		if (value === null || value === undefined || value.trim() === '') {
			var msg = element.data('msg-msg-notblank');
			var label = element.data('p-label');
			var context = PrimeFaces.util.ValidationContext;
			var msgObj;
			
			if (!msg) {
				msgObj = context.getMessage('{org.hibernate.validator.constraints.NotBlank.message}', label);
			}
			else {
				var msgObj = {
					summary : msg,
					detail : msg
				}
			}
			
			throw msgObj;
		}
	}
};